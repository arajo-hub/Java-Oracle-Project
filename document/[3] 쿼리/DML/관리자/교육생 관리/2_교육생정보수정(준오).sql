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


-- 2. 교육생 정보 수정

-- 2.a. 교육생 기초정보 수정
create or replace procedure procUpdateStudent(
    pseq number,        --교육생번호
    pname varchar2,     --이름
    pidnum varchar2,    --주민번호뒷자리
    ptel varchar2,      --전화번호
    pemployment varchar2--취업여부
    
)
is
begin
    update tblStudent set name = pname, idnum = pidnum, tel = ptel, employment = pemployment where seq = pseq;
end procUpdateStudent;

create or replace procedure procUpRegStudent(
    popencourseq number,
    pseq number
)
is
begin
    update tblRegistration set opencourseq = popencourseq where studentseq = pseq;
end procUpRegStudent;


-- 프로시저 사용
begin
    procUpdateStudent(1007,'이준','1111111','010-1234-5678','Y');
    procUpRegStudent(2,1007);
end;

commit;
select * from tblStudent where seq = 1007;
select * from tblregistration where studentseq = 1007;
update tblregistration set regstate = 'Y' where studentseq = 1007;
rollback;

-- 2.b 교육생 과정 수강, 수료 및 중도 탈락 처리
create or replace procedure procUpdateRegistration(
    pstudentseq number,
    pregstate varchar2,
    pdate date
)
is
begin
    
    if pregstate = 'P'
        then update tblRegistration set regState = pregstate , completiondate = pdate where studentSeq = pstudentseq;
    elsif pregstate = 'G'
        then update tblRegistration set regState = pregstate , faildate = pdate where studentSeq = pstudentseq;
    elsif pregstate = 'Y'
        then update tblregistration set regState = pregstate where studentSeq = pstudentseq;
    end if;
    
end procUpdateRegistration;



-- 프로시저 사용
begin
    procUpdateRegistration(1,'Y', '2020-12-11');
end;


select * from tblRegistration;

rollback;
--------------------------------------------------------------------------------