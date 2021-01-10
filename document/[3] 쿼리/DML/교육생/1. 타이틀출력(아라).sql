/*

    교육생 요구사항 쿼리
    
    요구사항 1.
    교육생이 로그인에 성공하면 교육생 개인의 정보와
    교육생이 수강한 과정명, 과정시작일, 과정종료일, 강의실이 타이틀로 출력된다.

*/

-- * procShowTitle
-- 현재 교육생이 수강중인 과정명, 과정시작일, 과정종료일, 강의실을 찾아 타이틀로 출력하는 프로시저
-- 입력 : 교육생번호
-- 출력 : 교육생명, 과정명, 과정시작일, 과정종료일, 강의실
create or replace procedure procShowTitle(
    pseq in tblStudent.seq%type,                 -- 교육생번호
    pName out tblStudent.name%type,              -- 교육생명
    pCourse out tblcourse.name%type,             -- 과정명
    pStartDate out tblopencourse.startdate%type, -- 과정시작일
    pEndDate out tblopencourse.enddate%type,     -- 과정종료일
    pLectureRoom out tbllectureroom.seq%type     -- 강의실
)
is
begin
    select
        s.name,
        c.name,
        oc.startdate,
        oc.enddate,
        lr.seq
    into
        pName,
        pCourse,
        pStartDate,
        pEndDate,
        pLectureRoom
    from tblStudent s
        left outer join tblRegistration r
            on s.seq=r.studentseq
                left outer join tblOpenCourse oc
                    on r.opencourseq=oc.seq
                        left outer join tblCourse c
                            on oc.courseseq=c.seq
                                left outer join tblLectureRoom lr
                                    on oc.lectureRoomSeq=lr.seq
                                        where s.seq=pseq;
end;




/*
-- procShowTitle 테스트

declare
    vCourse tblcourse.name%type;
    vStartDate tblopencourse.startdate%type;
    vEndDate tblopencourse.enddate%type;
    vLectureRoom tbllectureroom.seq%type;
    vResult number;
begin
    procShowTitle(200, vCourse, vStartDate, vEndDate, vLectureRoom, vResult);
    dbms_output.put_line('과정명 : '||vCourse);
    dbms_output.put_line('과정기간 : '||to_char(vStartDate, 'yyyy-mm-dd')||' ~ '||to_char(venddate, 'yyyy-mm-dd'));
    dbms_output.put_line('강의실번호 : '||vlectureroom);
exception -- 현재 수강중인 과정이 없을 경우의 예외처리
    when no_data_found then
        dbms_output.put_line('현재 수강중인 과정이 없습니다.');
end;

*/