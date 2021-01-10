/*

    교육생 요구사항 쿼리
    요구사항 4.
    본인의 출결을 입력한다.

*/

-- (1) 입실체크
-- * procInsertIntime
-- 입실체크하는 프로시저
-- 입력 : 교육생번호
-- 출력 : 실행결과(0 : 완료, 1 : 이미 입실체크되어있음, 2 : 입실체크가능한 시간이 아님.)
create or replace procedure procInsertInTime
(
    pseq number, -- 교육생번호
    presult out number
)
is

    pcount number; -- 이미 오늘 출결 데이터가 입력되어있는지 확인해줄 변수
    pisHoliday number;
    pstate tblattendancestate.seq%type; -- 시간에 따라 상태를 담아줄 변수
    pregseq tblattendance.regseq%type; -- 수강번호

begin

    -- 공휴일이라면 1이, 아니라면 0이 나온다.
    select count(*) into pisHoliday from tblHoliday where to_char(sysdate, 'yyyymmdd')=to_char(regdate,'yyyymmdd');
    
    -- 주말 혹은 공휴일인지 확인한다.
    if to_char(sysdate, 'd') not in (1, 7) and pisHoliday=0 then -- 주말도 아니고, 공휴일도 아닐 경우.
    
        -- 오늘 입력된 데이터가 있는지 확인한다.
        select count(a.intime)
            into pcount
        from tblAttendance a
            inner join tblAttendanceState ast
                on a.stateSeq=ast.seq
                    inner join tblregistration r
                        on r.seq=a.regSeq
                            inner join tblStudent s
                                on s.seq=r.studentseq
                                    inner join tblOpenCourse oc
                                        on r.openCourSeq=oc.seq
                                            where r.studentseq=pseq
                                                and to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss')
                                                    between to_char(oc.startdate, 'yyyy-mm-dd hh24:mi:ss')
                                                    and to_char(oc.enddate, 'yyyy-mm-dd hh24:mi:ss')
                                                and to_char(a.inTime, 'yyyymmdd')=to_char(sysdate, 'yyyymmdd');
        
        if pcount>0 then -- 데이터가 이미 있는데 또 입력을 하려고 하는 경우
            presult:=1;
        
        else -- 데이터가 없는 경우
            
            -- 시간에 따라 상태를 입력한다.
            -- 제약사항 : 1. 정상 / 2. 지각 / 3. 조퇴 / 4. 외출 / 5. 병가 / 6. 기타 : 6가지 경우만 입력 가능!
            
            -- 1. 9시부터 18시 사이에 입력하면 지각
            if to_char(sysdate, 'hh24:mi:ss')>'09:00:00' and to_char(sysdate, 'hh24:mi:ss')<'18:00:00' then
                -- 학생번호로 등록번호를 찾는다.
                select r.seq into pregseq from tblRegistration r, tblStudent st
                where r.studentseq=st.seq and st.seq=pseq;
                -- 입실체크한다. (2. 지각)
                insert into tblAttendance(seq, inTime, outTime, regSeq, stateSeq)
                    values(seqTblAttendance.nextVal, sysdate, null, pregseq, 2);
                presult:=0;
            
            -- 2. 8시 30분부터 9시 사이에 입력하면 기타. (6. 기타) -> 후에 퇴실체크시 정상으로 바꿈.)
            elsif to_char(sysdate, 'hh24:mi:ss')>='08:00:00' and to_char(sysdate, 'hh24:mi:ss')<='09:00:00' then
                 -- 학생번호로 등록번호를 찾는다.
                select r.seq into pregseq from tblRegistration r, tblStudent st
                where r.studentseq=st.seq and st.seq=pseq;
                -- 입실체크한다.
                insert into tblAttendance(seq, inTime, outTime, regSeq, stateSeq)
                    values(seqTblAttendance.nextVal, sysdate, null, pregseq, 6);
                presult:=0;
            else
                -- 입실체크 가능한 시간이 아닌 경우.
                presult:=2;
            
            end if;
    
        end if;
    else -- 주말, 공휴일일 경우
        presult:=2;
    end if;
end;




/*

-- 프로시저 테스트

declare
    vresult number;
begin
    procInsertInTime(140, vresult);
    
    if vresult=0 then
        dbms_output.put_line('입실체크 완료했습니다.');
        dbms_output.put_line('퇴실체크까지 정상적으로 이루어져야');
        dbms_output.put_line('정상출결로 인정됩니다.');
    elsif vresult=1 then
        dbms_output.put_line('[!!!]');
        dbms_output.put_line('이미 입실체크되어 있습니다.');
    elsif vresult=2 then
        dbms_output.put_line('[!!!]');
        dbms_output.put_line('입실체크 가능한 시간이 아닙니다.');
        dbms_output.put_line('입실체크 가능한 시간 : 오전 8시 30분 ~ 17시 59분');
    else
        dbms_output.put_line('[!!!]');
        dbms_output.put_line('입실체크에 실패했습니다.');
        dbms_output.put_line('관리자에게 문의해주세요.');
    end if;
end;

*/




-- (2) 퇴실체크
-- * procInsertOutTime
-- 퇴실체크하는 프로시저
-- 입력 : 교육생번호, 설정하고자 하는 상태(1. 정상, 2. 지각, 3. 조퇴, 4. 외출, 5. 병가, 6. 기타)
-- 출력 : 실행결과(0 : 완료, 1 : 이미 퇴실체크되어있음, 2 : 퇴실체크가능한 시간이 아님.)

create or replace procedure procInsertOutTime
(
    pseq number, -- 교육생번호
    pstate tblattendancestate.seq%type, -- 설정하고자 하는 상태
    presult out number
)
is
    pcount number; -- 이미 오늘 출결 데이터가 입력되어 있는지 확인해줄 변수
    pregseq tblattendance.regseq%type;
    pisHoliday number;
begin
    
    -- 공휴일이라면 1이, 아니라면 0이 나온다.
    select count(*) into pisHoliday from tblHoliday where to_char(sysdate, 'yyyymmdd')=to_char(regdate,'yyyymmdd');
    
    -- 주말 혹은 공휴일인지 확인한다.
    if to_char(sysdate, 'd') not in (1, 7) and pisHoliday=0 then -- 주말도 아니고, 공휴일도 아닐 경우.
        
        -- 먼저 오늘 입력된 데이터가 있는지 확인한다.
        select count(a.outtime)
            into pcount
        from tblAttendance a
            inner join tblAttendanceState ast
                on a.stateSeq=ast.seq
                    inner join tblregistration r
                        on r.seq=a.regSeq
                            inner join tblStudent s
                                on s.seq=r.studentseq
                                    inner join tblOpenCourse oc
                                        on r.openCourSeq=oc.seq
                                            where r.seq=pseq
                                                and to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss')
                                                    between to_char(oc.startdate, 'yyyy-mm-dd hh24:mi:ss')
                                                    and to_char(oc.enddate, 'yyyy-mm-dd hh24:mi:ss')
                                                and to_char(a.inTime, 'yyyymmdd')=to_char(sysdate, 'yyyymmdd');
        
        if pcount>0 then -- 데이터가 이미 있는데 또 입력을 하려고 하는 경우
            
            presult:=1;
        
        else -- 데이터가 없는 경우
            
            -- 시간에 따라 상태를 입력한다.
            -- 제약사항 : 1. 정상 / 2. 지각 -> 6시 ~ 6시 30분까지 퇴실체크하는 경우.
            if to_char(sysdate, 'hh24:mi:ss')>'17:59:59' and to_char(sysdate, 'hh24:mi:ss')<='18:30:00' then
                
                if pstate is null then -- 지각의 경우는 입실체크는 늦었어도 퇴실체크는 제시간에 수행한 것이므로 상태를 바꿔줄 필요가 없다.
                
                    -- 학생번호로 등록번호를 찾는다.
                    select r.seq into pregseq from tblRegistration r, tblStudent st
                    where r.studentseq=st.seq and st.seq=pseq;
                
                    update tblAttendance set outTime=sysdate where regSeq=pregseq and to_char(inTime, 'yyyymmdd')=to_char(sysdate, 'yyyymmdd') and outtime is null;
                    presult:=0;
                
                elsif pstate=1 then -- 정상의 경우, 입실체크시 기타로 입력되어있고, 퇴실체크까지 제시간에 수행해야 정상으로 바뀌는 것이므로 상태를 '기타' -> '정상'으로 바꿔줘야 한다.
                    
                    -- 학생번호로 등록번호를 찾는다.
                    select r.seq into pregseq from tblRegistration r, tblStudent st
                    where r.studentseq=st.seq and st.seq=pseq;
                    
                    update tblAttendance set outTime=sysdate, stateseq=1 where regSeq=pregseq and to_char(inTime, 'yyyymmdd')=to_char(sysdate, 'yyyymmdd') and outtime is null;
                    presult:=0;
                
                else -- 제시간에 퇴실체크를 하지만 정상이나 지각이 아닌 다른 상태를 선택한 경우는 메시지를 띄운다.
                    presult:=3; -- 상태를 잘못 입력한 경우.
                
                end if;
            
            -- 제약사항 : 3. 조퇴 / 4. 외출 / 5. 병가 / 6. 기타 -> 6시 이전에 퇴실체크하는 경우.
            elsif to_char(sysdate, 'hh24:mi:ss')>'08:59:59' and to_char(sysdate, 'hh24:mi:ss')<'18:00:00' then
                
                if pstate in (3, 4, 5, 6) then -- 6시 이전에 퇴실체크를 하는데 상태가 '조퇴, 외출, 병가, 기타' 중 하나라면 그 상태로 퇴실체크를 한다.
                
                    -- 학생번호로 등록번호를 찾는다.
                    select r.seq into pregseq from tblRegistration r, tblStudent st
                    where r.studentseq=st.seq and st.seq=pseq;
                
                    update tblAttendance set outTime=sysdate, stateseq=pstate where regSeq=pregseq and to_char(intime, 'yyyymmdd')=to_char(sysdate, 'yyyymmdd') and outtime is null;
                    presult:=0;
                
                else -- 6시 이전에 퇴실체크를 하는데 '정상', '지각'인 경우는 메시지를 띄운다.
                    presult:=3; -- 상태를 잘못 입력한 경우.
                
                end if;
            
            -- 제약사항 : 퇴실체크 가능한 시간이 아닐 경우.
            else
                -- 가능한 시간이 아니라는 메시지를 띄운다.
                presult:=2;
            end if;
        end if;
    else -- 주말 혹은 공휴일인 경우
        presult:=2;
    end if;
end;




/*

-- 프로시저 테스트

declare
    vresult number;
begin
    procInsertOutTime(140, 1, vresult);
    
    if vresult=0 then
        dbms_output.put_line('퇴실체크 완료했습니다.');
        dbms_output.put_line('정상출결 처리됐습니다.');
    elsif vresult=1 then
        dbms_output.put_line('[!!!]');
        dbms_output.put_line('이미 퇴실체크되어 있습니다.');
    elsif vresult=2 then
        dbms_output.put_line('[!!!]');
        dbms_output.put_line('퇴실체크 가능한 시간이 아닙니다.');
        dbms_output.put_line('퇴실체크 가능한 시간 : 오전 9시 ~ 오후 6시 30분');
    else
        dbms_output.put_line('[!!!]');
        dbms_output.put_line('퇴실체크에 실패했습니다.');
        dbms_output.put_line('관리자에게 문의해주세요.');
    end if;
end;

*/