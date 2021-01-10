-- 교사 ------------------------------------------------------------------------

-- 특정 교사 강의가능과목 출력 프로시저
create or replace procedure procPossibleSubjectList(
    pseq number,        -- 교사번호
    pcursor out sys_refcursor
)
is
begin
    open pcursor
        for select
                t.name as 교사,
                s.seq as No,                    -- 과목기초번호 출력
                s.subjectname as 강의가능과목   -- 과목명 출력 
            from tblpossiblesubject p
                inner join tblteacher t
                    on p.teacherseq = t.seq
                        inner join tblsubject s
                            on p.subjectseq = s.seq
            where t.seq = pseq                  -- 선택할 교사번호
            order by s.seq;

end;

select * from tblteacher;
select * from tblpossiblesubject;
set serveroutput on;




-- 특정 교사 담당과정 정보 출력 프로시저
create or replace procedure procShowTeacherDetail
(
    pseq number,        -- 조회할 교사번호
    pcursor out sys_refcursor
)
is
begin
    open pcursor
        for select 
            t.name as 교사명,
            c.name as 과정명,
            s.subjectname as 과목명,
            to_char(os.startdate, 'yyyy-mm-dd') as 시작일,
            to_char(os.enddate, 'yyyy-mm-dd') as 종료일,
            case
                when sysdate < os.startdate then '진행예정'  
                when os.startdate <= sysdate and sysdate <= os.enddate + 1 then '진행중'
                when os.enddate + 1 < sysdate then '진행완료'
            end as 진행상태,
            oc.lectureroomseq || '강의실' as 강의실,
            b.title as 교재
        from tblteacher t
            left outer join tblopensubject os
                on t.seq = os.teacherseq
                    left outer join tblsubject s
                        on os.subjectseq = s.seq
                            left outer join tblopencourse oc
                                on os.opencourseq = oc.seq
                                    left outer join tblcourse c
                                        on oc.courseseq = c.seq
                                            left outer join tblBook b
                                                on s.bookseq = b.seq

            where t.seq = pseq  -- 교사번호 입력
            order by os.startdate, os.enddate;

end;