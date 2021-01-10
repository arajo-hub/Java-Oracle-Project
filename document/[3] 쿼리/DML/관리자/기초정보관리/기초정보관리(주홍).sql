

/*

기초정보관리

강의실, 교재, 과목, 과정 테이블의 조회, 등록, 수정, 삭제 기능

조회 -> select 
등록/수정/삭제 -> procedure 

*/


-- 강의실 관리
desc tbllectureroom;

-- a.조회
select 
    seq,
    seq || '강의실' as 강의실명,
    capacity || '명' as 정원
from tbllectureroom
order by seq;

-- b.등록
create or replace procedure procAddLectureRoom
(
    pcapacity number         --정원
)
is
begin
    insert into tblLectureRoom (seq, capacity) values (seqtblLectureRoom.nextVal, pcapacity);
    commit;
exception
    when others then
    rollback;
end;


-- c.수정
create or replace procedure procUpdateLectureRoom
(
    pseq number,        -- 강의실번호
    pcapacity number    -- 정원
)
is
begin
    update tblLectureRoom set capacity = pcapacity where seq = pseq;
    commit;
exception 
    when others then
    rollback;
end;

-- d.삭제
create or replace procedure procDeleteLectureRoom
(
    pseq number     -- 강의실 번호
)
is
begin
    delete from tblLectureRoom where seq = pseq;
    commit;
exception
    when others then
    rollback;
end;



-- 교재 관리 -------------------------------------------------------------------
desc tblBook;

-- a.조회
select * from tblBook
order by seq;

-- b.등록
create or replace procedure procAddBook
(
    --pseq number,             
    ptitle varchar2,        -- 책제목     
    ppublisher varchar2     -- 출판사
)
is
begin
    insert into tblBook (seq, title, publisher) values (seqtblBook.nextVal, ptitle, ppublisher);
    commit;
exception
    when others then
    rollback;
end;

-- c.수정
create or replace procedure procUpdateBook
(
    pseq number,            -- 책번호
    ptitle varchar2,        -- 책제목
    ppublisher varchar2     -- 출판사
)
is
begin
    update tblBook set title = ptitle, publisher = ppublisher where seq = pseq;
    commit;
exception 
    when others then
    rollback;
end;

-- d.삭제
create or replace procedure procDeleteBook
(
    pseq number     -- 책번호
)
is
begin
    delete from tblBook where seq = pseq;
    commit;
exception
    when others then
    rollback;
end;



-- 과목 관리 -------------------------------------------------------------------
select * from tblSubject;
desc tblSubject;

-- a.조회
select
    seq as No,
    subjectName as 과목명,
    division as 구분,
    period || '주' as 기간
from tblSubject
order by seq;

select s.seq as seq, subjectName, division, period, b.title as bookTitle from tblSubject s
    left outer join tblBook b
        on s.Bookseq = b.seq;
        
-- b.등록
create or replace procedure procAddSubject
(             
    psubjectname varchar2,      -- 과목명
    pdivision varchar2,         -- 구분(공통/추가)
    pperiod number,             -- 과목기간(주)
    pbookseq number             -- 사용교재(FK)
)
is
begin
    insert into tblSubject (seq, subjectname, division, period, bookseq) values (seqtblSubject.nextVal, psubjectname, pdivision, pperiod, pbookseq);
    commit;
exception
    when others then
    rollback;
end;

-- c.수정
create or replace procedure procUpdateSubject
(
    pseq number,                -- 과목번호
    psubjectname varchar2,      -- 과목명
    pdivision varchar2,         -- 구분(공통/추가)
    pperiod number,             -- 과목기간(주)
    pbookseq number             -- 사용교재(FK)
)
is
begin
    update tblSubject set subjectname = psubjectname, division = pdivision, period = pperiod, bookseq = pbookseq where seq = pseq;
    commit;
exception 
    when others then
    rollback;
end;

-- d.삭제
create or replace procedure procDeleteSubject
(
    pseq number     -- 과목번호
)
is
begin
    delete from tblSubject where seq = pseq;
    commit;
exception
    when others then
    rollback;
end;


-- 과정 관리 -------------------------------------------------------------------
desc tblCourse;

-- a.조회
select
    seq as No,
    name as 과정명,
    period || '주' as 기간
from tblCourse
order by seq;

-- b.등록
create or replace procedure procAddCourse
(             
    pname varchar2,         -- 과정명
    pperiod number          -- 과정기간(주)
)
is
begin
    insert into tblCourse (seq, name, period) values (seqtblCourse.nextVal, pname, pperiod); 
    commit;
exception
    when others then
    rollback;
end;

-- c.수정
create or replace procedure procUpdateCourse
(
    pseq number,            -- 과정번호
    pname varchar2,         -- 과정명
    pperiod number          -- 과정기간(주)
)
is
begin
    update tblCourse set name = pname, period = pperiod where seq = pseq;
    commit;
exception 
    when others then
    rollback;
end;

-- d.삭제
create or replace procedure procDeleteCourse
(
    pseq number     -- 과목번호
)
is
begin
    delete from tblCourse where seq = pseq;
    commit;
exception
    when others then
    rollback;
end;
        
        