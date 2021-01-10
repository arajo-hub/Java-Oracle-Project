-- 쌍용1조. 오라클 프로젝트
-- DDL 통합본

/*
================================================================================
    
    @ 기초정보
    1. 관리자 테이블
    2. 교육생 테이블
    3. 교사 테이블
    4. 과정기초정보 테이블
    5. 교재 테이블
    6. 과목기초정보 테이블
    7. 강의실 테이블
    8. 강의가능과목 테이블

================================================================================
*/

-- 관리자 테이블
create table tblAdmin
(
    seq number primary key, -- 관리자번호(PK)
    name varchar2(30) not null, -- 이름
    idNum varchar2(7) not null, -- 주민번호뒷자리
    tel varchar2(13) not null -- 전화번호(하이픈포함)
);

-- 관리자 테이블 시퀀스 생성
create sequence seqTblAdmin;

-- 교육생 테이블(아라, 준오)
create table tblStudent
(
    seq number primary key, -- 교육생번호(PK)
    name varchar2(30) not null, -- 이름
    idNum varchar2(7) not null, -- 주민번호뒷자리
    tel varchar2(13) not null, -- 전화번호(하이픈포함)
    employment varchar2(1) default 'N'
        check (employment in ('Y', 'N'))-- 취업여부
        -- 제약조건 : 취업여부는 'Y'나 'N'으로만 입력한다.
);

-- 교육생 테이블 시퀀스 생성
create sequence seqTblStudent;




-- 교사 테이블(원준, 주홍, 준오)
create table tblTeacher
(
    seq number primary key,         --교사번호(PK)
    name varchar2(30) not null,     --교사명
    idNum varchar2(7) not null,     --주민번호 뒷자리
    tel varchar2(13) not null       --전화번호(하이픈포함)
);

-- 교사 테이블 시퀀스 생성
create sequence seqTblTeacher;




-- 과정기초정보 테이블(진영, 주홍, 준오)
CREATE TABLE tblCourse (
    	
	seq NUMBER primary key, -- 과정기초정보번호
	name VARCHAR2(100) NOT NULL, -- 과정명
	period NUMBER NOT NULL -- 수업일수		 
);

-- 과정기초정보 테이블 시퀀스 생성
create sequence seqTblCourse;




-- 교재 테이블(주홍, 준오)
create table tblBook
(
    seq number primary key,         -- 교재번호(PK)
    title varchar2(100) not null,             -- 교재명
    publisher varchar2(30) not null          -- 출판사
    
);

-- 교재 테이블 시퀀스 생성
create sequence seqTblBook;




-- 과목기초정보 테이블(진영, 주홍, 준오)
CREATE TABLE tblSubject (
	
	seq NUMBER primary key, -- 과목 기초 정보 번호(PK)
	subjectName VARCHAR2(60) NOT NULL, -- 과목명
	division VARCHAR2(6) NOT NULL
        check (division in ('공통', '추가')), -- 과목 구분
        -- 제약조건 : 과목은 공통과목이나 추가과목으로 구분된다.
	period NUMBER NOT NULL, -- 수업 일수
	bookSeq NUMBER not null references tblBook(seq) -- 교재번호(FK)
);

-- 과목기초정보 테이블 시퀀스 생성
create sequence seqTblSubject;




-- 강의실 테이블(주홍, 준오)
create table tblLectureRoom
( 
    seq number primary key,    -- 강의실번호(PK)
    capacity number not null            -- 수용가능인원
);

-- 강의실 테이블 시퀀스 생성
create sequence seqTblLectureRoom;




-- 강의가능과목테이블(주홍)
create table tblPossibleSubject
(
    teacherSeq number not null references tblTeacher(seq), --교사번호(FK)
    subjectSeq number not null references tblSubject(seq),  --과목번호(FK)
    
    constraint tblPossibleSubject_t_s_pk primary key(teacherSeq, subjectSeq)
);

-- 강의가능과목 테이블 시퀀스 생성
--create sequence seqTblPossibleSubject;





/*
================================================================================
    
    @ 과정&과목 개설, 수강
    1. 개설과정 테이블
    2. 개설과목 테이블
    3. 수강 테이블
    4. 출결상태 테이블
    5. 출결 테이블

================================================================================
*/

-- 개설과정 테이블(진영, 준오, 지수)
CREATE TABLE tblOpenCourse (
	
	seq NUMBER primary key, -- 개설과정번호(PK)
	startDate DATE NOT NULL, -- 과정시작일
	endDate DATE NOT NULL, -- 과정종료일
	lectureRoomSeq NUMBER NOT NULL references tblLectureRoom(seq) on delete cascade, -- 강의실번호
	courseSeq NUMBER NOT NULL  references tblCourse(seq) on delete cascade-- 과정기초정보번호(FK)
);

-- 개설과정 테이블 시퀀스 생성
create sequence seqTblOpenCourse;




-- 개설과목 테이블(원준, 준오, 지수)
create table tblOpenSubject
(
    seq number primary key,     --개설과목번호(PK)
    startDate date not null,    --과목 시작일
    endDate date not null,      --과목 종료일
    openCourSeq number not null references tblOpenCourse(seq) on delete cascade,  --개설과정번호(FK)
    subjectSeq  number not null references tblSubject(seq) on delete cascade,     --과목기초정보번호(FK)
    teacherSeq number not null references tblTeacher(seq) on delete cascade       --교사번호(FK)
);

-- 개설과목 테이블 시퀀스 생성
create sequence seqTblOpenSubject;




-- 수강 테이블(아라, 준오, 지수)
create table tblRegistration
(
    seq number primary key, -- 수강번호(PK)
    regDate date not null, -- 등록일
    regState varchar2(1) not null
        check(regState in ('P', 'Y', 'G')), -- 참여상태
        -- 제약사항 : 'P'(수료), 'Y'(진행중), 'G'(중도포기) 3가지만 입력 가능!
    completionDate date default null, -- 수료일
    failDate date default null, -- 중도탈락일
    studentSeq number not null references tblStudent(seq) on delete cascade, -- 교육생번호(FK)
    openCourSeq number not null references tblOpenCourse(seq) on delete cascade -- 개설과정번호(FK)
);

-- 수강 테이블 시퀀스 생성
create sequence seqTblRegistration;




-- 출결상태 테이블(아라, 준오, 지수)
create table tblAttendanceState
(
    seq number primary key, -- 상태번호(PK)
    state varchar2(6) not null
        check(state in ('정상', '지각', '조퇴', '외출', '병가', '기타')) -- 상태
        -- 제약사항 : '정상', '지각', '조퇴', '외출', '병가', '기타' 6가지 경우만 입력 가능!
);

-- 출결상태 테이블 시퀀스 생성
create sequence seqTblAttendanceState;




-- 출결 테이블(아라, 준오, 지수)
create table tblAttendance
(
    seq number primary key, -- 출결번호(PK)
    inTime date not null, -- 입실시간
    outTime date null, -- 퇴실시간
    regSeq number not null references tblRegistration(seq) on delete cascade, -- 수강번호(FK)
    stateSeq number not null references tblAttendanceState(seq) -- 출결상태번호(FK)
);

-- 출결 테이블 시퀀스 생성
create sequence seqTblAttendance;




/*
================================================================================
    
    @ 배점 입력, 시험, 강의평가
    1. 배점 테이블
    2. 시험 테이블
    3. 강의평가 테이블
    4. 성적내역 테이블
    5. 성적 테이블
    

================================================================================
*/

-- 배점 테이블(원준)
create table tblScoreAllot
(
    seq number primary key,                          -- 배점번호(PK)
    handwritingAllot number default null,            -- 필기배점
    practiceAllot number default null,               -- 실기배점
    attendanceAllot number default null check (attendanceAllot between 20 and 100), -- 출결배점
    openSubSeq number not null references tblOpenSubject(seq) on delete cascade        --개설과목번호(FK)
);

-- 배점 테이블 시퀀스 생성
create sequence seqTblScoreAllot;





-- 시험 테이블(원준)
create table tblExam
(
    seq number primary key, --시험번호(PK)
    examDate date not null, -- 시험일
    openSubSeq number not null references tblOpenSubject(seq) on delete cascade --개설과목번호(FK)
);

-- 시험 테이블 시퀀스 생성
create sequence seqTblExam;




-- 강의평가 테이블(원준)
create table tblLectureEvaluation
(
    seq number primary key, -- 강의평가번호(PK)
    preparationScore number not null
        check(preparationScore between 0 and 5), -- 교사의 수업준비점수
        -- 제약사항 : 5점 만점
    understandScore number not null
        check(understandScore between 0 and 5), -- 교사의 내용전달점수
        -- 제약사항 : 5점 만점
    usefulScore number not null
        check(usefulScore between 0 and 5), -- 수업의 유익도점수
        -- 제약사항 : 5점 만점
    totalScore number not null, -- 강의평점 = 수업준비점수 + 내용전달점수 + 유익도점수
    openSubSeq number not null references tblOpenSubject(seq) on delete cascade, -- 개설과목번호(FK)
    studentSeq number not null references tblStudent(seq) -- 교육생번호(FK)
);

-- 강의평가 테이블 시퀀스 생성
create sequence seqTblLectureEvaluation;




-- 성적내역 테이블(아라, 준오, 지수)
create table tblGradeInfo
(
    seq number primary key, -- 성적내역번호(PK)
    openSubSeq not null references tblOpenSubject(seq) on delete cascade, -- 개설과목번호(FK)
    regSeq references tblRegistration(seq) -- 수강번호(FK)
);

-- 성적내역 테이블 시퀀스 생성
create sequence seqTblGradeInfo;




-- 성적 테이블(아라, 준오, 지수)
create table tblGrade
(
    seq number primary key, -- 성적번호(PK)
    handwritingScore number -- 필기성적
        check(handwritingScore>=0),
        -- 제약사항 : 0보다는 커야함!
    practiceScore number, -- 실기성적
        check(practiceScore>=0),
            -- 제약사항 : 0보다는 커야함!
    attendanceScore number, -- 출결성적
        check(attendanceScore>=0),
            -- 제약사항 : 0보다는 커야함!
    gradeInfoSeq number not null references tblGradeInfo(seq) on delete cascade -- 성적내역번호(FK)
);

-- 성적 테이블 시퀀스 생성
create sequence seqTblGrade;




/*
================================================================================
    
    @ 구인정보
    1. 업종 테이블
    2. 공고회사 테이블
    3. 구인정보 테이블
    4. 공고기간 테이블
    

================================================================================
*/

-- 업종 테이블(아라, 주홍)
create table tblIndustry
(
    seq number primary key, -- 업종번호(PK)
    name varchar2(60) not null -- 업종명
);

-- 업종 테이블 시퀀스 생성
create sequence seqTblIndustry;



-- 공고회사 테이블(아라, 주홍)
create table tblCompany
(
    seq number primary key, -- 공고회사번호(PK)
    name varchar2(60) not null, -- 회사명
    ceo varchar2(30) not null, -- 대표자명
    establishDate date not null, -- 설립일
    employeeCount number not null, -- 사원수
    tel varchar2(13) not null, -- 연락처(하이픈포함)
    address varchar2(100) not null, -- 주소
    sales number not null, -- 매출액
    industrySeq number not null references tblIndustry(seq) -- 업종번호(FK)
);

-- 공고회사 테이블 시퀀스 생성
create sequence seqTblCompany;




-- 구인정보 테이블(아라, 주홍)
create table tblJobInfo
(
    seq number primary key, -- 구인정보번호(PK)
    jobDivision varchar2(30) not null, -- 모집직군
    jobType varchar2(30) not null, -- 근무형태
    annualIncome number not null, -- 제시연봉
    recruitStep varchar2(300) not null, -- 채용단계
    detail varchar2(1000) not null, -- 공고내용
    companySeq number not null references tblCompany(seq)-- 공고회사번호(FK)
);

-- 구인정보 테이블 시퀀스 생성
create sequence seqTblJobInfo;




-- 공고기간 테이블(아라, 주홍)
create table tbljobNoticePeriod
(
    seq number primary key,        -- 공고기간번호(PK)
    startDate date not null,                -- 공고시작일
    endDate date null,                      -- 공고종료일
    jobInfoSeq number not null references tblJobInfo(seq) -- 구인정보번호(FK)
);

-- 공고회사 테이블 시퀀스 생성
create sequence seqTbljobNoticePeriod;