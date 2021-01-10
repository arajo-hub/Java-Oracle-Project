/*
===========================================================
 <교육생 관리>
   [1. 교육생 등록]
       [a. 교육생 정보 등록]
       
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




-- 1. 교육생 등록

-- 1.a. 교육생 기초정보 등록 + 수강정보 등록 프로시저 생성
create or replace procedure procAddStudent(

    pname varchar2,     --이름
    pidnum varchar2,    --주민번호뒷자리
    ptel varchar2,      --전화번호
    pemployment varchar2--취업여부


)
is
begin

    insert into tblStudent (seq, name, idnum, tel, employment) --교육생 정보넣기
        values (seqTblStudent.nextVal, pname, pidnum, ptel, pemployment);
    

        
end procAddStudent;


create or replace procedure procAddRegistration(
        popencourseq number -- 개설과정번호
)
is
begin
    insert into tblRegistration (seq, regDate, regState,completiondate, faildate, studentSeq, openCourSeq) --수강 정보 넣기
        values (seqTblRegistration.nextVal, sysdate, 'Y', null, null, (select max(seq) from tblStudent), popencourseq);
    
end procAddRegistration;

-- 프로시저 사용
begin
    procAddStudent('홍당무','1111111','010-1111-1111','Y',1);
    procAddRegistration(1);
end;


commit;
rollback;
select * from tblStudent where name = '이준오';
select * from tblRegistration where studentseq = 1008;
--------------------------------------------------------------------------------