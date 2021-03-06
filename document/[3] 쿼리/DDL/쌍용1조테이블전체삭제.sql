-- 쌍용1조. 오라클 프로젝트
-- 테이블 전체삭제

/*
================================================================================
    
    @ 구인정보
    1. 공고기간 테이블
    2. 구인정보 테이블
    3. 공고회사 테이블
    4. 업종 테이블

================================================================================
*/

-- 공고기간 테이블(아라, 주홍)
drop table tbljobNoticePeriod;

-- 구인정보 테이블(아라, 주홍)
drop table tblJobInfo;

-- 공고회사 테이블(아라, 주홍)
drop table tblCompany;

-- 업종 테이블(아라, 주홍)
drop table tblIndustry;

/*
================================================================================
    
    @ 배점 입력, 시험, 강의평가
    1. 성적 테이블
    2. 성적내역 테이블
    3. 강의평가 테이블
    4. 시험 테이블
    5. 배점 테이블

================================================================================
*/

-- 성적 테이블(아라, 준오, 지수)
drop table tblGrade;

-- 성적내역 테이블(아라, 준오, 지수)
drop table tblGradeInfo;

-- 강의평가 테이블(원준)
drop table tblLectureEvaluation;

-- 시험 테이블(원준)
drop table tblExam;

-- 배점 테이블(원준)
drop table tblScoreAllot;

/*
================================================================================
    
    @ 과정&과목 개설, 수강
    1. 출결 테이블
    2. 출결상태 테이블
    3. 수강 테이블
    4. 개설과목 테이블
    5. 개설과정 테이블

================================================================================
*/

-- 출결 테이블(아라, 준오, 지수)
drop table tblAttendance;

-- 출결상태 테이블(아라, 준오, 지수)
drop table tblAttendanceState;

-- 수강 테이블(아라, 준오, 지수)
drop table tblRegistration;

-- 개설과목 테이블(원준, 준오, 지수)
drop table tblOpenSubject;

-- 개설과정 테이블(진영, 준오, 지수)
drop table tblOpenCourse;

/*
================================================================================
    
    @ 기초정보
    1. 강의가능과목 테이블
    2. 강의실 테이블
    3. 과목기초정보 테이블
    4. 교재 테이블
    5. 과정기초정보 테이블
    6. 교사 테이블
    7. 교육생 테이블
    8. 관리자 테이블
    
================================================================================
*/

-- 강의가능과목테이블(주홍)
drop table tblPossibleSubject;

-- 강의실 테이블(주홍, 준오)
drop table tblLectureRoom;

-- 과목기초정보 테이블(진영, 주홍, 준오)
drop table tblSubject;

-- 교재 테이블(주홍, 준오)
drop table tblBook;

-- 과정기초정보 테이블(진영, 주홍, 준오)
drop table tblCourse;

-- 교사 테이블(원준, 주홍, 준오)
drop table tblTeacher;

-- 교육생 테이블(아라, 준오)
drop table tblStudent;

-- 관리자 테이블
drop table tblAdmin;