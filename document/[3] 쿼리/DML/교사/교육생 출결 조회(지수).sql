-- 교사 출결 관리 및 조회
-- 교사가 강의한 과정에 한해 모든 교육생의 출결 조회 기능
------------------------------------------------------------------------------------

CREATE OR REPLACE view vw_AttendanceOutput 
AS
select distinct
    oc.seq as courseSeq,
    c.name as courseName,
    oc.startdate as attendanceIn,
    oc.enddate as attendanceOut,
    t.seq as teacherSeq
from tblOpenCourse oc
    inner join tblCourse c
        on oc.courseseq = c.seq
    inner join tblOpenSubject os
        on os.opencourseq = oc.seq
    inner join tblTeacher t
        on t.seq = os.teacherseq
        order by oc.seq;



-- 출석 과정 정보
create or replace PROCEDURE proc_AttendanceOutput (
    ptseq IN NUMBER,
    pcursor OUT SYS_REFCURSOR
)
IS
BEGIN
open pcursor for
    select
        *
    from vw_AttendanceOutput
    WHERE teacherSeq = ptseq;
END proc_AttendanceOutput;

commit;

-- 교사 출결 관리 프로시저
-- 1. 특정과정(강의한 과정) 모든 교육생 출결
-- 2. 특정 과정 특정 교육생 전체 날짜
-- 3. 특정 과정 특정 교육생 특정 날짜

-- 교육생의 출결상태 뷰
-- [교육생번호] [교육생이름] [날짜] [출결상태]
create or replace view vwStudentInSubject
as
select distinct
    oc.seq as courseSeq,
    s.seq as studentSeq,
    s.name as studentName,
    intime as inDate,
    a.outtime as outDate,
    tas.state as state,
    t.seq as teacherSeq,
    c.name as courseName
from tblStudent s 
    left outer join tblRegistration r
        on s.seq = r.studentseq
            inner join tblAttendance a
                on a.regseq = r.seq
                    inner join tblAttendanceState tas
                        on a.stateseq = tas.seq
                            inner join tblOpenCourse oc
                                on oc.seq = r.opencourseq
                                    inner join tblCourse c
                                        on c.seq = oc.courseSeq
                                            inner join tblOpenSubject os
                                                on os.openCourSeq = oc.seq
                                                    inner join tblTeacher t
                                                        on t.seq = os.teacherSeq
order by studentSeq, inDate;



--1. 전체 교육생 전체 날짜 
create or replace procedure procAllDateAttendance(
    pocseq IN NUMBER,               -- 과정 번호 
    ptseq IN NUMBER,                -- 교사번호
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
IS
BEGIN
open pcursor for 
    select 
        * 
    from vwStudentInSubject
    WHERE teacherSeq = ptseq AND courseSeq = pocseq
    ORDER BY studentseq, indate;
END procAllDateAttendance;


--2. 특정 학생 조회 (전체 기간 특정 학생)
create or replace procedure procStudentDateAttendance(
    pocseq IN NUMBER,               -- 과정          
    ptseq IN NUMBER,                -- 교사
    pstseq in number,               -- 학생
    pcursor out SYS_REFCURSOR       
)
is
begin
open pcursor for
    select
        *
    from vwStudentInSubject
    WHERE teacherSeq = ptseq AND courseSeq = pocseq AND studentSeq = pstseq
    ORDER BY studentseq, indate;
end;

create or replace view vwStudentAttList
as
select distinct
    s.seq as studentSeq,
    s.name as studentName,
    c.name as courseName,
    oc.seq as courseSeq,
    t.seq as teacherSeq
from tblStudent s
    inner join tblregistration r
        on s.seq = r.studentseq
    inner join tblopencourse oc
        on r.opencourseq = oc.seq
    inner join tblopensubject os
        on oc.seq = os.opencourseq
    inner join tblTeacher t
        on t.seq = os.teacherseq
    inner join tblCourse c
        on c.seq = oc.courseseq
        order by s.seq;


-- 학생출결목록
create or replace procedure procStudentAttList(
    pocseq IN NUMBER,              
    ptseq IN NUMBER,
    pcursor out SYS_REFCURSOR  
)
is
begin
open pcursor for
    select
        *
    from vwStudentAttList
    where courseseq = pocseq and teacherseq = ptseq
    ORDER BY studentseq;
end procStudentAttList;


commit;


--3. 출결 현황 기간별 조회
-- 특정일 입력시 모든 교육생 특정일 출결 출력
create or replace procedure procSelDateAttendance(
    pocseq IN NUMBER,               -- 과정
    ptseq IN NUMBER,                -- 교사
    pdate in date,                  -- 특정일 입력받음
    pdateout in date,                  -- 퇴실시간일 
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
is
begin
open pcursor for 
    select 
        * 
    from vwStudentInSubject 
    where inDate > pdate and outDate < pdateout AND courseseq = pocseq and teacherseq = ptseq
    ORDER BY studentseq, indate;
end procSelDateAttendance;


declare
    vcursor sys_refcursor;
    vrow vwStudentInSubject%rowtype;
begin
    procSelDateAttendance(5, 5, '2020-12-21', vcursor);
    
    loop
        fetch vcursor into vrow;
        dbms_output.put_line(vrow.state);
        exit when vcursor%notfound;
    end loop;
end;



-- 4. 특정기간별 특정 학생 출결 조회
create or replace procedure procDateStuAttendance(
    pocseq IN NUMBER,               -- 과정
    ptseq IN NUMBER,                -- 교사
    pdate in date,                  -- 특정일 입력받음
    pdateout in date,                  -- 퇴실시간일 
    pstseq in number,               -- 학생
    pcursor out SYS_REFCURSOR       -- 커서를 반환값으로 사용
)
is
begin
open pcursor for 
    select 
        * 
    from vwStudentInSubject 
    where inDate > pdate and outDate < pdateout AND courseseq = pocseq and teacherseq = ptseq AND studentSeq = pstseq
    ORDER BY studentseq, indate;
end procDateStuAttendance;




commit;