/*
================================================================================

[교육생 성적 정보 조회]

      [1. 개설 과목별 조회] (과목 별 성적 정보 출력)
      
      [2. 교육생 개인별 조회](전체 교육생 목록출력)
              [1. 교육생 번호 입력] (해당 교육생성적 출력)
                           
                           
================================================================================
*/

-- 1. 개설 과목별 조회


-- 1.1 과정 목록 출력
-- [개설과정번호] [과정명] [과정시작일] [과정종료일] [강의실번호]
-- '5_출결관리 및 출결조회.sql' 에서 만든 뷰 사용

select * from vwOpenCourse;

--------------------------------------------------------------------------------

-- 1.2 개설 과목 별 교육생 성적정보 출력
-- 1.2.1 개설 과목 별 모든 교육생 성적정보 출력 '뷰' 생성
-- [개설과정번호] [개설과정명] [과정시작일] [과정종료일] [강의실번호] [개설과목명] [교사명] [교재명] [교육생이름] [주민번호뒷자리] [필기점수] [실기점수]

-- 뷰 생성
create or replace view vwOpenSubGrade
as
select 
    c.name as courseName,
    oc.startDate as courseStartDate,
    oc.endDate as courseEndDate,
    oc.lectureRoomSeq as lectureRoomSeq,
    sub.subjectName as subjectName,
    t.name as teacherName,
    b.title as bookTitle,
    s.name as studentName,
    s.idnum as idNum,
    g.handwritingscore as handwritingScore,
    g.practicescore as practiceScore,
    s.seq as studentSeq,
    os.startdate as subjectStartDate,
    os.enddate as subjectEndDate,
    os.seq as subjectSeq
from tblstudent s 
    inner join tblRegistration r 
        on r.studentSeq = s.seq
            inner join tblOpenCourse oc
                on r.openCourSeq = oc.seq
                    inner join tblOpenSubject os
                        on os. opencourSeq = oc.seq
                            inner join tblGradeInfo gi
                                on gi.openSubSeq = os.seq
                                    inner join tblGrade g
                                        on g.gradeInfoSeq = gi.seq
                                            inner join tblsubject sub
                                                on os.subjectSeq = sub.seq
                                                    inner join tblBook b
                                                        on sub.bookSeq = b.seq
                                                            inner join tblCourse c
                                                                on oc.courseSeq = c.seq
                                                                    inner join tblTeacher t
                                                                        on os.teacherSeq = t.seq
                                                                            
where s.seq = gi.regseq
order by studentSeq, os.seq asc;



-- 뷰 출력
select * from vwOpenSubGrade;
                                        

-- 1.2.1 뷰를 이용하여 입력한 '특정 개설 과정'의 '교육생 성적'을 '출력'하는 프로시저 생성
-- [개설과정명] [개설과목명] [과목시작일] [과목종료일] [강의실번호] [교사명] [교육생이름] [주민번호뒷자리] [필기점수] [실기점수] [교재명]
create or replace procedure procCourScore(
    pseq number,
    pcursor out SYS_REFCURSOR
)
is
begin

    open pcursor for select * from vwOpenSubGrade where lectureRoomSeq = pseq;

end procCourScore;





-- 프로시저 실행

declare
   vcursor sys_refcursor;
   vrow vwOpenSubGrade%rowtype;
begin
    procCourScore(5, vcursor);      -- opencursor
    
    dbms_output.put_line('[개설과정명] [과정시작일] [과정종료일] [강의실번호] [개설과목명] [교사명] [교재명] [교육생이름] [주민번호뒷자리] [필기점수] [실기점수]');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;

        dbms_output.put_line(vrow.courseName || '  ' ||  vrow.courseStartDate || '  ' ||  vrow.courseEndDate || '  ' || vrow.lectureRoomSeq || '  ' || vrow.subjectName || '  ' || vrow.teacherName || '  ' || vrow.bookTitle || '  ' || vrow.studentName || '  ' || vrow.idNum || '  ' || vrow.handwritingScore || '  ' || vrow.practiceScore);
    
    end loop;
    
end;



--------------------------------------------------------------------------------


-- 2. 교육생 개인별 조회
-- 위의 1.2.1 에서 생성한 뷰를 참조
--  - 교육생 기본정보 출력 : [교육생이름] [주민번호뒷자리] [개설과정명] [과정시작일] [과정종료일] [강의실번호] [개설과목명] [과목시작일] [과목종료일] [교사명] [필기점수] [실기점수]

create or replace procedure procStudentScore(
    pseq number,
    pcursor out SYS_REFCURSOR
)
is
begin

    open pcursor for select * from vwOpenSubGrade where studentSeq = pseq;

end procStudentScore;





-- 프로시저 실행

declare
   vcursor sys_refcursor;
   vrow vwOpenSubGrade%rowtype;
begin
    procStudentScore(117, vcursor);      -- opencursor
    
    dbms_output.put_line('[교육생이름] [주민번호뒷자리] [개설과정명] [과정시작일] [과정종료일] [강의실번호] [개설과목명] [과목시작일] [과목종료일] [교사명] [필기점수] [실기점수]');    
    
    loop 
        
        fetch vcursor into vrow;
        
        exit when vcursor%notfound;

        dbms_output.put_line(vrow.studentSeq || '  ' ||  vrow.idNum || '  ' ||  vrow.courseName || '  ' || vrow.courseStartDate || '  ' || vrow.courseEndDate || '  ' || 
        vrow.lectureRoomSeq || '  ' || vrow.subjectName || '  ' || vrow.subjectStartDate || '  ' || vrow.subjectEndDate || '  ' || vrow.teacherName || '  ' || vrow.handwritingScore || '  ' || vrow.practiceScore);
    
    end loop;
    
end;

--------------------------------------------------------------------------------

