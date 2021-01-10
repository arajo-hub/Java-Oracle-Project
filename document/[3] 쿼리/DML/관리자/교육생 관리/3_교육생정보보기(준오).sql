/*
===========================================================
 <교육생 관리>
   [1. 교육생 등록]
       [a. 교육생 기초정보 등록]
       [b. 교육생 수강정보 등록]
       
   [2. 교육생 정보 수정] 
       [a. 교육생 기초정보 수정]
       [b. 교육생 과정 수강, 수료 및 중도 탈락 처리]
       
   [3. 교육생 정보 보기]
       [a. 전체 교육생 정보 보기]
            [1. 특정 교육생 정보 보기]
       [b. 교육생 정보 검색 하기]
            [1. 교육생 번호로 검색]
            
   [4. 교육생 정보 삭제하기]
       [a. 교육생 번호로 검색]

===========================================================
*/

-- 3. 교육생 정보 보기

-- 3.a. 전체 교육생 정보 보기
-- [교육생 번호] [교육생 이름] [주민번호 뒷자리] [전화번호] [등록일] [수강(신청) 횟수]
-- 교육생 전체 조회 view 생성
create or replace view vwAllStudent
as
select 
    distinct s.seq as studentSeq,
    s.name as studentName,
    s.idnum as idNumber,
    s.tel as telNumber,
    case
        when r.regdate is not null then r.regdate       -- 수강에 등록되어있는 교육생 등록일 출력
        when r.regdate is null then sysdate             -- 수강엔 없는 교육생의 등록일은 등록당시의 sysdate(오늘)
    end as regDate,
    (select count(*) from tblRegistration where studentseq = s.seq) as count
from tblStudent s 
    left outer join tblRegistration r
        on s.seq = r.studentseq
order by s.seq;


-- view 사용
select * from vwAllStudent where studentseq = 1004;



--------------------------------------------------------------------------------

-- 3.a.1 특정 교육생 정보 보기
-- 교육생이 수강 신청한 또는 수강중인, 수강했던 개설 과정 정보 출력
-- [과정명] [과정시작일] [과정종료일] [강의실] [수료 및 중도탈락] [수료 및 중도탈락 날짜]
-- 특정 교육생 과정 정보보는 프로시저 생성


-- view 생성
create or replace view vwStudentOpenCourse
as
select  
    r.studentseq as studentSeq,
    c.name as name,
    oc.startdate as startDate,
    oc.enddate as endDate,
    oc.lectureroomseq as lectureRoomSeq,
    case 
        when r.regstate = 'P' then '수료'
        when r.regstate = 'Y' then '진행중'
        when r.regstate = 'G' then '중도포기'
    end as state,
    case
        when r.regstate = 'P' then r.completiondate
        when r.regstate = 'Y' then r.completiondate
        when r.regstate = 'G' then r.faildate
    end  as stateDate 
    
    from tblopencourse oc 
        inner join tblregistration r 
            on oc.seq = r.opencourseq
                inner join tblCourse c
                    on c.seq = oc.courseseq
    order by studentseq;
              
              

select * from vwStudentOpenCourse ;     


-- 특정 교육생 정보보기 프로시저 생성
create or replace PROCEDURE procStudentOpenCourse(
    pseq in number,                             -- 학생번호
    preturn out vwStudentOpenCourse%rowtype     -- vrow 중에서 학생번호가 일치하는 row만 저장할 곳
)
is
    cursor vcursor
    is
    select * from vwStudentOpenCourse;
    vrow vwStudentOpenCourse%rowtype;
begin
    open vcursor;
        loop
            fetch vcursor into vrow;
            exit when vcursor%notfound;
                        
            if vrow.studentseq = pseq   -- vrow의 학생번호와 pseq를 비교
                then preturn := vrow;   -- preturn에 vrow 저장
                            
            end if;
        end loop;
    close vcursor;
    
end procStudentOpenCourse;



-- 프로시저 사용
declare
    vresult vwStudentOpenCourse%rowtype;
begin
    procStudentOpencourse(1004,vresult);           -- 1번 교육생 정보
    dbms_output.put_line('[과정명] [과정시작일] [과정종료일] [강의실] [수료 및 중도탈락] [수료 및 중도탈락 날짜]');
    dbms_output.put_line(vresult.name || '  ' ||  vresult.startdate || '  ' ||  vresult.enddate || '  ' ||  vresult.lectureroomseq || '  ' || vresult.state || '  ' || vresult.statedate);
end;



--------------------------------------------------------------------------------


-- 3.b 교육생 정보 검색하기
-- [교육생번호],[교육생이름],[주민번호 뒷자리],[전화번호],[등록일] 을 통한 검색

-- 출력내용 : [교육생 번호] [교육생 이름] [주민번호 뒷자리] [전화번호] [등록일] [수강(신청) 횟수] 


-- 3.b.1 교육생 번호로 검색
create or replace procedure procSeqSearchStudent(
    pseq in number,
    presult out vwAllStudent%rowtype
)
is
    cursor vcursor
    is
    select * from vwAllStudent;
    vrow vwAllStudent%rowtype;
begin
    open vcursor;
        loop
            
            fetch vcursor into vrow;
            exit when vcursor%notfound;
            
            if vrow.studentSeq = pseq          -- 교육생 번호가 일치할 때
                then presult := vrow;
            end if;
            
        end loop;
    close vcursor;
    
end procSeqSearchStudent;


-- 프로시저 실행

declare
    vresult vwAllStudent%rowtype;
begin
    procSeqSearchStudent(1,vresult);
    dbms_output.put_line(' [교육생 번호] [교육생 이름] [주민번호 뒷자리] [전화번호] [등록일] [수강(신청) 횟수] ');
    dbms_output.put_line(vresult.studentSeq || '  ' ||  vresult.studentName || '  ' ||  vresult.idNumber || '  ' ||  vresult.telNumber || '  ' || vresult.regdate || '  ' || vresult.count);
end;


--------------------------------------------------------------------------------
-- 3.b.2 교육생 이름으로 검색
create or replace procedure procNameSearchStudent(
    pname in vwAllStudent.studentName%type,
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
is
begin
    
    open pcursor for select * from vwAllStudent where studentName = pname;
    
    
end procNameSearchStudent;

    
-- 프로시저 실행


declare
    vcursor sys_refcursor;
    vrow vwAllStudent%rowtype;
begin
    procNameSearchStudent('백라로', vcursor);      -- opencursor
    
    dbms_output.put_line(' [교육생 번호] [교육생 이름] [주민번호 뒷자리] [전화번호] [등록일] [수강(신청) 횟수] ');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;
        

        dbms_output.put_line(vrow.studentseq || '  ' ||  vrow.studentname || '  ' ||  vrow.idnumber || '  ' ||  vrow.telnumber || '  ' || vrow.regdate || '  ' || vrow.count);
    
    end loop;
    
end;





--------------------------------------------------------------------------------

-- 3.b.3 주민번호 뒷자리로 검색

create or replace procedure procIDNumSearchStudent(
    pidnum in vwAllStudent.idnumber%type,
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
is
begin
    
    open pcursor for select * from vwAllStudent where idnumber = pidnum;
    
    
end procIDNumSearchStudent;




-- 프로시저 실행


declare
    vcursor sys_refcursor;
    vrow vwAllStudent%rowtype;
begin
    procIDNumSearchStudent('1306091', vcursor);      -- opencursor
    
    dbms_output.put_line(' [교육생 번호] [교육생 이름] [주민번호 뒷자리] [전화번호] [등록일] [수강(신청) 횟수] ');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;
        

        dbms_output.put_line(vrow.studentSeq || '  ' ||  vrow.studentName || '  ' ||  vrow.idNumber || '  ' ||  vrow.telNumber || '  ' || vrow.regdate || '  ' || vrow.count);
    
    end loop;
    
end;


--------------------------------------------------------------------------------

-- 3.b.4 전화번호로 검색

create or replace procedure procTelSearchStudent(
    ptel in vwAllStudent.telNumber%type,
    presult out vwAllStudent%rowtype
)
is
    cursor vcursor
    is
    select * from vwAllStudent;
    vrow vwAllStudent%rowtype;
begin
    open vcursor;
        loop
            
            fetch vcursor into vrow;
            exit when vcursor%notfound;
            
            if vrow.telNumber = ptel        -- 교육생 전화번호가 일치할 때
                then presult := vrow;
            end if;
            
        end loop;
    close vcursor;
    
end procTelSearchStudent;



-- 프로시저 실행

declare
    vresult vwAllStudent%rowtype;
begin
    procTelSearchStudent('010-8305-8844',vresult);
    dbms_output.put_line(' [교육생 번호] [교육생 이름] [주민번호 뒷자리] [전화번호] [등록일] [수강(신청) 횟수] ');
    dbms_output.put_line(vresult.studentSeq || '  ' ||  vresult.studentName || '  ' ||  vresult.idNumber || '  ' ||  vresult.telNumber || '  ' || vresult.regdate || '  ' || vresult.count);
end;




--------------------------------------------------------------------------------

-- 3.b.5 등록일로 검색

create or replace procedure procRegDateSearchStudent(
    pregdate in vwAllStudent.studentName%type,
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
is
begin
    
    open pcursor for select * from vwAllStudent where regdate = pregdate;
    
    
end procRegDateSearchStudent;




-- 프로시저 실행

declare
    vcursor sys_refcursor;
    vrow vwAllStudent%rowtype;
begin
    procRegDateSearchStudent('2020-07-01', vcursor);      -- opencursor
    
    dbms_output.put_line(' [교육생 번호] [교육생 이름] [주민번호 뒷자리] [전화번호] [등록일] [수강(신청) 횟수] ');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;
        

        dbms_output.put_line(vrow.studentSeq || '  ' ||  vrow.studentName || '  ' ||  vrow.idNumber || '  ' ||  vrow.telNumber || '  ' || vrow.regdate || '  ' || vrow.count);
    
    end loop;
    
end;








