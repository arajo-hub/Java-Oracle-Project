/*
===========================================================
<출결 관리 및 출결 조회>
  
  [출결 조회]
      [1. 과정 선택]   (과정 내 과목 목록 출력)
            [1. 과목 선택]   (과목 내 모든 교육생 출결 출력)
      [2. 특정 교육생 출결 검색]
            [1. 교육생 검색] (교육생 번호 입력)
      [3. 출결 현황 날짜별 조회]
            [1. 특정 일 입력] (모든 교육생 특정일 출결 출력)

===========================================================
*/


-- 출결 사용 뷰


-- 과정목록 출력 뷰
-- [개설과정번호] [과정명] [과정시작일] [과정종료일] [강의실번호]
create or replace view vwOpenCourse
as
select
    oc.seq as openCourSeq,
    c.name as courseName,
    oc.startDate as startDate,
    oc.endDate as endDate,
    oc.lectureRoomSeq as lectureRoomSeq
from tblOpenCourse oc 
    left outer join tblCourse c 
        on oc.courseseq = c.seq
order by openCourSeq;



-- 과정 목록 출력
select* from vwopencourse;



--------------------------------------------------------------------------------

-- 과목목록 출력 뷰
-- [개설과목번호] [과목명] [과목시작일] [과목종료일] [교사이름] [강의실번호]
create or replace view vwOpenSubject
as 
select
    os.seq as openSubSeq,
    s.subjectName as subjectName,
    os.startDate as startDate,
    os.endDate as endDate,
    t.name as teacherName,
    oc.lectureRoomSeq as lectureRoomSeq
from tblOpenSubject os 
    left outer join tblOpenCourse oc 
        on os.opencourseq = oc.seq
            inner join tblTeacher t
                on t.seq = os.teacherSeq
                    inner join tblSubject s
                        on s.seq = os.subjectSeq
order by openSubSeq;



-- 과목목록 출력
select * from vwOpenSubject where lectureRoomSeq = 1;



--------------------------------------------------------------------------------

-- 교육생 수강 과목에 따른, 출결 관련 뷰
-- [과목번호] [과목명] [교육생번호] [교육생이름] [날짜] [출결상태]
create or replace view vwSubjectStudent
as
select
    os.seq as openSubSeq,
    sub.subjectName as subjectName,
    s.seq as studentSeq,
    s.name as studentName,
    to_char(a.intime,'mm-dd') as inTime,
    tas.state as state
from tblattendance a
    left outer join tblAttendanceState tas
        on a.stateseq = tas.seq
            inner join tblRegistration r
                on a.regseq = r.seq
                    inner join tblStudent s
                        on r.studentseq = s.seq 
                            inner join tblOpenCourse oc
                                on r.opencourseq = oc.seq
                                    inner join tblOpenSubject os
                                        on os.openCourSeq = oc.seq
                                            inner join tblSubject sub
                                                on os.subjectSeq = sub.seq
order by studentSeq, inTime, openSubSeq;

select * from vwSubjectStudent;
select openSubSeq, subjectName, studentSeq, studentName, inTime, state  from vwSubjectStudent where openSubSeq = 25;

--------------------------------------------------------------------------------


-- 교육생의 출결상태 뷰
-- [교육생번호] [교육생이름] [날짜] [출결상태]
create or replace view vwStudentInSubject
as
select 
    s.seq as studentSeq,
    s.name as studentName,
    to_char(a.intime,'yyyy-mm-dd') as inDate,
    tas.state as state
from tblStudent s 
    left outer join tblRegistration r
        on s.seq = r.studentseq
            inner join tblAttendance a
                on a.regseq = r.seq
                    inner join tblAttendanceState tas
                        on a.stateseq = tas.seq
order by studentSeq, inDate;


select * from vwstudentinsubject;


--------------------------------------------------------------------------------
-- 1. 과정 선택
-- 과정 목록에서 과정번호 선택.
-- 과정선택시 과목목록 출력
create or replace procedure procSelOpenCourse(
    pseq in number,                 -- 과정 번호 선택
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
is
begin
    
    
    
    open pcursor for select * from vwOpenSubject where lectureRoomSeq = pseq;

end procSelOpenCourse;



-- 프로시저 실행

declare
    vcursor sys_refcursor;
    vrow vwOpenSubject%rowtype;
begin
    procSelOpenCourse(1, vcursor);      -- 1번 과정 과목목록 출력 프로시저 사용
    
    dbms_output.put_line('[개설과목번호] [과목명] [과목시작일] [과목종료일] [교사이름] [강의실번호]');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;

        dbms_output.put_line(vrow.openSubSeq || '  ' ||  vrow.subjectName || '  ' ||  vrow.startDate || '  ' ||  vrow.endDate || '  ' || vrow.teacherName || '  ' || vrow.lectureRoomSeq);
    
    end loop;
    
end;




--------------------------------------------------------------------------------

-- 1.1 과목선택
-- 과목 선택시 과목내 교육생 출결 정보 출력
-- [과목명] [교육생번호] [교육생이름] [날짜] [출결상태]
create or replace procedure procSelObjectAttendance(
    pseq number,                    -- 과목번호 입력받음
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
is
begin
    
    open pcursor for select openSubSeq, subjectName, studentSeq, studentName, inTime, state from vwSubjectStudent where openSubSeq = pseq;

end procSelObjectAttendance;

commit;
set serveroutput on;
-- 프로시저 실행

declare
    vcursor sys_refcursor;
    vrow vwSubjectStudent%rowtype;
begin
    procSelObjectAttendance(60, vcursor);      -- 60번 과목 선택 시 해당 교육생 출결 목록 출력
    
    dbms_output.put_line('[과목명] [교육생번호] [교육생이름] [날짜] [출결상태]');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;

        dbms_output.put_line(vrow.openSubSeq || '  ' ||  vrow.studentSeq || '  ' ||  vrow.studentName || '  ' ||  vrow.inTime || '  ' || vrow.state);
    
    end loop;
    
end;

    

--------------------------------------------------------------------------------


-- 2. 특정 교육생 출결검색
-- 교육생 번호 입력 시 교육생 출결 정보 출력
create or replace procedure procSelSeqAttendance(
    pseq in number,
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
is
begin
    
    open pcursor for select * from vwStudentInSubject where studentSeq = pseq;

end procSelSeqAttendance;



  


-- 프로시저 실행
declare
    vcursor sys_refcursor;
    vrow vwStudentInSubject%rowtype;
begin
    procSelSeqAttendance(117, vcursor);      -- 117번 교육생의 출결정보를 출력
    
    dbms_output.put_line('[교육생번호] [교육생이름] [날짜] [출결상태]');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;

        dbms_output.put_line(vrow.studentSeq || '  ' ||  vrow.studentName || '  ' ||  vrow.inDate || '  ' ||  vrow.state);
    
    end loop;
    
end;




--------------------------------------------------------------------------------


--3. 출결 현황 기간별 조회
-- 특정일 입력시 모든 교육생 특정일 출결 출력

create or replace procedure procSelDateAttendance(
    pdate in date,                  -- 특정일 입력받음
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
is
begin
    
    open pcursor for select * from vwStudentInSubject where inDate = pdate;

end procSelDateAttendance;






-- 프로시저 실행
declare
    vcursor sys_refcursor;
    vrow vwStudentInSubject%rowtype;
begin
    procSelDateAttendance('2020-12-01', vcursor);      -- 2020-12-01의 출석 정보 출력
    
    dbms_output.put_line('[교육생번호] [교육생이름] [날짜] [출결상태]');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;

        dbms_output.put_line(vrow.studentSeq || '  ' ||  vrow.studentName || '  ' ||  vrow.inDate || '  ' ||  vrow.state);
    
    end loop;
    
end;

--------------------------------------------------------------------------------