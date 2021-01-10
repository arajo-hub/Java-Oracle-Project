-- DML Teacher

-- 성적 입출력
-- 강의를 마친 과목에 대한 성적 처리를 위해서 성적 입출력

-- 과목 목록
-- [과목번호, 과정명, 과정기간(시작 끝), 강의실, 과목명, 과목기간(시작 끝), 교재명, 출결, 필기, 실기 배점, 성적 등록 여부]

-- 교육생
-- [(이름, 전화번호, 수료 또는 중도탈락) 및 성적이 출결, 필기, 실기 점수]
-- 중도탈락 날짜, 중도탈락 이후 날짜의 성적은 입력하지 않는다
--====================================================================================================================================
set serveroutput on;

rollback;
commit;



-- 성적 조회 목록 뷰
CREATE OR REPLACE VIEW vwInfoCourseDetail as 
SELECT DISTINCT
    tc.seq as teacherSeq,
    os.seq as openSubjectSeq,
    oc.seq as openCourseSeq,
    c.name as courseName,
    to_char(oc.startdate, 'yyyy-mm-dd') as courseStart,
    to_char(oc.enddate, 'yyyy-mm-dd') as courseEnd,
    lr.seq as classRoom,
    sub.subjectname as subjectName,
    to_char(os.startdate, 'yyyy-mm-dd') as subjectStart,
    to_char(os.enddate, 'yyyy-mm-dd') as subjectEnd,
    b.title as bookTitle,
    sa.handwritingallot as writingAllot,
    sa.practiceallot as practiceAllot,
    sa.attendanceallot as attendanceAllot,
    CASE
        WHEN
            handwritingScore IS NOT NULL AND
            practiceScore IS NOT NULL AND
            attendanceScore IS NOT NULL
        THEN '등록'
        ELSE '미등록'
    END AS registerGrade
FROM tblCourse C                        -- 과정기초테이블(과정명)
    INNER JOIN tblOpenCourse OC    -- 개설과정
        on c.seq = oc.courseseq
    INNER JOIN tblLectureRoom LR   -- 강의실
        on lr.seq = oc.lectureroomseq
    INNER JOIN tblOpenSubject OS   -- 개설과목 
        on os.opencourseq = oc.seq
    INNER JOIN tblSubject Sub      -- 과목기초정보(과목명)
        on os.subjectseq = sub.seq
    INNER JOIN tblBook B           -- 교재명
        on b.seq = sub.bookseq
    INNER JOIN tblScoreAllot SA    -- 배점
        on sa.opensubseq = os.seq
    INNER JOIN tblTeacher TC       -- 교사
        on tc.seq = os.teacherSeq
    INNER JOIN tblGradeInfo GI     -- 성적내역
        on gi.openSubSeq = os.seq
    INNER JOIN tblGrade G          -- 성적
        on gi.seq = g.gradeInfoSeq 
        order by os.seq;

    


-- 성적 과목목록 출력 프로시저    
CREATE OR REPLACE PROCEDURE proc_CourseGrade(
    ptseq IN number,            -- 교사번호
    pcursor OUT SYS_REFCURSOR   -- 해당 교사번호의 끝난 과목 출력 
)
IS
BEGIN
OPEN pcursor FOR
    SELECT
        *
    FROM vwInfoCourseDetail
        WHERE ptseq = teacherSeq AND SUBJECTEND < sysdate
        ORDER BY OPENSUBJECTSEQ asc;  
END proc_CourseGrade;



-- 학생목록 출력
DROP VIEW vw_StudentcoreDetail;
SELECT * FROM vw_StudentcoreDetail;
CREATE OR REPLACE VIEW vw_StudentcoreDetail AS
SELECT DISTINCT
    st.seq as studentSeq,
    st.name as studentName,
    st.tel as studentTel,
    CASE  
        when rs.regstate = 'P' then '수료'
        when rs.regstate = 'Y' then '진행중'
        when rs.regstate = 'G' then '중도포기'
    END as registState,
    failDate as stateFailDate,
    handwritingscore as writingScore,
    practicescore as practiceScore,
    attendancescore as attendanceScore,
    t.seq as teacherSeq,
    os.seq as openSubjectSeq,
    oc.seq as courseSeq
FROM tblStudent st                       -- 학생
    INNER JOIN tblRegistration RS       -- 수강
        on rs.studentSeq = st.seq
    INNER JOIN tblOpenCourse OC      -- 개설과정
        on oc.seq = rs.openCourSeq      
    INNER JOIN tblOpenSubject OS        -- 개설과목
        on os.opencourseq = oc.seq
    INNER JOIN tblTeacher t             -- 교사
        ON t.seq = os.teacherSeq
    INNER JOIN tblGradeInfo GI          -- 성적내역
        on gi.opensubseq = os.seq AND gi.regSeq = rs.seq
    INNER JOIN tblGrade G               -- 성적
        on g.gradeinfoseq = gi.seq;
    -- WHERE t.seq = 현재 로그인 된 교사 번호 변수
--    ORDER BY os.seq asc;              -- 특정 과목번호 필


-- 성적 학생목록 출력 프로시저
CREATE OR REPLACE PROCEDURE proc_StudentscoreDetail
(
    ptseq IN NUMBER,        -- 선생님 번호
    posseq IN NUMBER,       -- 과목번호
    pcursor OUT sys_refcursor
)
IS
BEGIN
OPEN pcursor FOR
    SELECT 
       *
    FROM
        vw_StudentcoreDetail
        WHERE 
            teacherSeq = ptseq AND openSubjectSeq = posseq
            order by studentSeq;
END proc_StudentscoreDetail;


commit;



-- 
--create or replace view vwStudentScoreUpdate as
--select 
--    s.seq as studentSeq,
--    s.name as studentName,
--    os.seq as subjectSeq,
--    sub.subjectname as subjectName,
--    t.seq as teacherSeq,
--    oc.seq as courseSeq,
--    c.name as courseName,
--    g.handwritingscore as writingScore,
--    g.practicescore as practiceScore,
--    g.attendancescore as attendanceScore
--from tblgrade g                 -- 성적
--    inner join tblgradeinfo gi  -- 성적내역
--    on g.gradeinfoseq = gi.seq
--    inner join tblregistration r    -- 수강
--    on gi.regseq = r.seq
--    inner join tblstudent s     -- 학생
--    on r.studentseq = s.seq
--    INNER JOIN tblOpenSubject OS    --개강과목
--    on os.seq = gi.opensubseq
--    inner join tblsubject sub   -- 기초과목
--    on sub.seq = os.subjectseq
--    inner join tblteacher t     --교사
--    on t.seq = os.teacherseq
--    inner join tblOpenCourse oc     --개강 과정
--    on oc.seq = os.opencourseq
--    inner join tblcourse c          --기초과정
--    on c.seq = oc.courseseq
--    order by s.seq;



-- 성적 업데이트 프로시저
CREATE OR REPLACE PROCEDURE proc_ScoreUpdate (
    pattendance IN NUMBER,
    pwriting IN NUMBER,
    ppractical IN NUMBER,
    ptseq IN NUMBER,            -- 교사
    pstseq IN NUMBER,           -- 학생
    posseq IN NUMBER          -- 과목
)
IS
    pnum tblStudent.seq%type;
BEGIN
    select g.seq into pnum from tblGrade g
    inner join tblGradeInfo gi
        on gi.seq = g.gradeinfoseq
            inner join tblopensubject os
                on os.seq = gi.opensubseq
                    inner join tblTeacher t
                        on t.seq = os.teacherseq
                    where gi.opensubseq = posseq and gi.regseq = pstseq and t.seq = ptseq;   
    UPDATE tblGrade 
    SET
        handWritingScore = pwriting,
        practiceScore = ppractical,
        attendanceScore = pattendance
    WHERE
        seq = pnum;                        
END proc_ScoreUpdate;




-- 필기 점수 삭제 
create or replace procedure proc_DeleteHandWritingScore(
    ptseq IN NUMBER,            -- 교사
    pstseq IN NUMBER,           -- 학생
    posseq IN NUMBER          -- 과목       
)
is
    pnum tblStudent.seq%type;
begin
select g.seq into pnum from tblGrade g
    inner join tblGradeInfo gi
        on gi.seq = g.gradeinfoseq
            inner join tblopensubject os
                on os.seq = gi.opensubseq
                    inner join tblTeacher t
                        on t.seq = os.teacherseq
                    where gi.opensubseq = posseq and gi.regseq = pstseq and t.seq = ptseq; 
    UPDATE tblGrade
    set
        handwritingScore = null
    where
        seq = pnum;
end proc_DeleteHandWritingScore;


-- 실기 점수 삭제
create or replace procedure proc_DeletePracticalScore(
    ptseq IN NUMBER,            -- 교사
    pstseq IN NUMBER,           -- 학생
    posseq IN NUMBER          -- 과목         
)
is
    pnum tblStudent.seq%type;
begin
select g.seq into pnum from tblGrade g
    inner join tblGradeInfo gi
        on gi.seq = g.gradeinfoseq
            inner join tblopensubject os
                on os.seq = gi.opensubseq
                    inner join tblTeacher t
                        on t.seq = os.teacherseq
                    where gi.opensubseq = posseq and gi.regseq = pstseq and t.seq = ptseq; 
    UPDATE tblGrade
    set
        practiceScore = null
    where
        seq = pnum; 
end proc_DeletePracticalScore;


-- 출결 점수 삭제
create or replace procedure proc_DeleteAttendScore(
    ptseq IN NUMBER,            -- 교사
    pstseq IN NUMBER,           -- 학생
    posseq IN NUMBER          -- 과목         
)
is
    pnum tblStudent.seq%type;
begin
select g.seq into pnum from tblGrade g
    inner join tblGradeInfo gi
        on gi.seq = g.gradeinfoseq
            inner join tblopensubject os
                on os.seq = gi.opensubseq
                    inner join tblTeacher t
                        on t.seq = os.teacherseq
                    where gi.opensubseq = posseq and gi.regseq = pstseq and t.seq = ptseq; 
    UPDATE tblGrade
    set
        attendanceScore = null
    where
        seq = pnum; 
end proc_DeleteAttendScore;