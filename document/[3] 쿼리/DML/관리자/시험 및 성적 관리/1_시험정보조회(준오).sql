/*

=========================================================================
<시험 정보 조회>

    [1. 과정 내 개설 과목] (과정 내 과목 목록 출력)
         [1. 과목내 시험 정보] (시험정보 출력)

=========================================================================

*/


-- 1. 과정 내 개설과목 목록 출력



-- 1.1 전체 과정 목록 출력 뷰 사용 
-- '5_출결관리 및 출결조회' 에서 만든 뷰 사용

select * from vwopencourse;



--------------------------------------------------------------------------------
select * from vwOpenSubject;
-- 1.2 전체 과목 시험정보 뷰 생성
create or replace view vwExam
as
select 
    vos.openSubSeq as openSubSeq,
    vos.subjectName as subjectName,
    vos.teacherName as teacherName,
    ex.examDate as examDate,
    vos.lectureRoomSeq as lectureRoomSeq
from vwOpenSubject vos             -- 전체 과목목록 뷰
        left outer join tblExam ex          -- 시험정보 테이블
            on vos.opensubseq = ex.opensubseq;
            
            

select * from vwExam;    
--------------------------------------------------------------------------------


-- 1.3 선택 과정 내 시험 정보 출력 프로시저 생성
create or replace procedure procExamInfo(
    pseq number,
    pcursor out SYS_REFCURSOR
)
is
begin

    open pcursor for select * from vwExam where lectureRoomSeq = pseq;

end procExamInfo;




-- 프로시저 사용
-- 시험정보 출력
declare
   vcursor sys_refcursor;
   vrow vwExam%rowtype;
begin
    procExamInfo(1, vcursor);      -- 과정 내 과목의 시험정보 출력
    
    dbms_output.put_line('[개설과목번호] [과목명] [시험일]');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;

        dbms_output.put_line(vrow.openSubSeq || '  ' ||  vrow.subjectName || '  ' ||  vrow.examDate);
    
    end loop;
    
end;


--------------------------------------------------------------------------------
































