/*

    교육생 요구사항 쿼리
    요구사항 3.
    본인의 출결 현황을 기간별(전체, 월, 일)조회할 수 있어야 한다.

*/

-- * vwDate
-- 전체 날짜를 담고 있는 뷰
-- 시작은 과정시작월부터.
-- 끝은 과정종료월까지.
create or replace view vwDate
as
select to_date('2020-01-01', 'yyyy-mm-dd')+level-1 as regdate from dual
    connect by level <=(to_date('2999-12-31', 'yyyy-mm-dd')-to_date('2020-01-01', 'yyyy-mm-dd')+1);

select * from vwDate;

-- * vwShowAttendance
-- 정보를 받아줄 vrow의 컬럼들을 모아놓은 뷰
create or replace view vwShowAttendance
as
select
        a.inTime as intime,
        a.outTime as outtime,
        ast.state as state
    from tblAttendance a
        inner join tblAttendanceState ast
            on a.stateSeq=ast.seq
                inner join tblregistration r
                    on r.seq=a.regSeq
                        inner join tblOpenCourse oc
                            on r.openCourSeq=oc.seq;



-- (1) 전체 조회
create or replace procedure procShowAttendanceAll(
    pseq tblStudent.seq%type,
    pcursor out sys_refcursor
)
is
begin
    open pcursor
        for select distinct
            a.inTime,
            a.outTime,
            ast.state
    from tblAttendance a
        inner join tblAttendanceState ast
            on a.stateSeq=ast.seq
                inner join tblregistration r
                    on r.seq=a.regSeq
                        inner join tblStudent s
                            on s.seq=r.studentseq
                                inner join tblOpenCourse oc
                                    on r.openCourSeq=oc.seq
                                        where s.seq=pseq
                                            and a.inTime<=sysdate
                                            order by a.intime desc;
end;




/*

-- procShowAttendanceAll 테스트

declare
    vcursor sys_refcursor;
    vrow vwShowAttendance%rowtype;
begin
    procShowAttendanceAll(90, vcursor);
    dbms_output.put_line('날짜'||' '||'입실시간'||' '||'퇴실시간'||' '||'출결상태');
    loop
        fetch vcursor into vrow;
        exit when vcursor%notfound;
        dbms_output.put_line(to_char(vrow.intime,'mm-dd')||' '||
                            to_char(vrow.intime, 'hh24:mi:ss')||' '||
                            to_char(vrow.outtime, 'hh24:mi:ss')||' '||
                            vrow.state);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('출결정보가 없습니다.');
end;

*/




-- (2) 월별 조회 : 원하는 월 입력하면 조회
create or replace procedure procShowAttendanceMonth(
    pseq tblStudent.seq%type,
    pyear varchar2, -- 원하는 년도 입력
    pmonth varchar2, -- 원하는 월 입력
    pcursor out sys_refcursor
)
is
begin
    open pcursor
        for select
            a.inTime,
            a.outTime,
            ast.state
        from tblAttendance a
            inner join tblAttendanceState ast
                on a.stateSeq=ast.seq
                    inner join tblregistration r
                        on r.seq=a.regSeq
                            inner join tblStudent s
                                on s.seq=r.studentseq
                                    inner join tblOpenCourse oc
                                        on r.openCourSeq=oc.seq
                                            where s.seq=pseq
                                                and to_char(a.inTime, 'yyyy')=pyear
                                                and to_char(a.inTime, 'mm')=pmonth
                                            and a.inTime<=sysdate
                                            order by a.intime desc;
end;




/*

-- procShowAttendanceMonth 테스트

declare
    vcursor sys_refcursor;
    vrow vwShowAttendance%rowtype;
begin
    procShowAttendanceMonth(140, '2020', '12', vcursor);
    dbms_output.put_line('날짜'||' '||'입실시간'||' '||'퇴실시간'||' '||'출결상태');
    loop
        fetch vcursor into vrow;
        exit when vcursor%notfound;
        dbms_output.put_line(to_char(vrow.intime,'mm-dd')||' '||
                            to_char(vrow.intime, 'hh24:mi:ss')||' '||
                            to_char(vrow.outtime, 'hh24:mi:ss')||' '||
                            vrow.state);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('출결정보가 없습니다.');
end;

*/




-- (3) 일별 조회 : 원하는 날짜 입력하면 조회
create or replace procedure procShowAttendanceDate(
    pseq tblStudent.seq%type,
    pyear varchar2,
    pmonth varchar2,
    pdate varchar2,
    pcursor out sys_refcursor
)
is
begin
    open pcursor
        for select distinct
            a.inTime,
            a.outTime,
            ast.state
        from tblAttendance a
            inner join tblAttendanceState ast
                on a.stateSeq=ast.seq
                    inner join tblregistration r
                        on r.seq=a.regSeq
                            inner join tblStudent s
                                on s.seq=r.studentseq
                                    inner join tblOpenCourse oc
                                        on r.openCourSeq=oc.seq
                                            where s.seq=pseq
                                                and to_char(a.inTime, 'yyyymmdd')=pyear||pmonth||pdate
                                                and a.inTime<=sysdate
                                            order by a.inTime desc;
end;





/*

-- procShowAttendanceMonth 테스트

declare
    vcursor sys_refcursor;
    vrow vwShowAttendance%rowtype;
begin
    procShowAttendanceDate(140, '2020', '12', '21', vcursor);
    dbms_output.put_line('날짜'||' '||'입실시간'||' '||'퇴실시간'||' '||'출결상태');
    loop
        fetch vcursor into vrow;
        exit when vcursor%notfound;
        dbms_output.put_line(to_char(vrow.intime,'mm-dd')||' '||
                            to_char(vrow.intime, 'hh24:mi:ss')||' '||
                            to_char(vrow.outtime, 'hh24:mi:ss')||' '||
                            vrow.state);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('출결정보가 없습니다.');
end;

*/