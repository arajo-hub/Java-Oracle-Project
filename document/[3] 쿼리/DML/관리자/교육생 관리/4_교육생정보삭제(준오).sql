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

-- 4. 교육생 삭제
delete from tbllectureevaluation where studentSeq = pseq;       -- 강의평가테이블에서 해당 교육생 삭제
delete from tblRegistration where studentSeq = pseq;            -- 수강테이블에서 해당 교육생 삭제
delete from tblStudent where seq = pseq;                        -- 학생테이블에서 해당 교육생 삭제

select * from tbllectureevaluation where studentSeq = 1;
select * from tblRegistration where studentSeq = 1;
select * from tblStudent where seq = 1;


--------------------------------------------------------------------------------


