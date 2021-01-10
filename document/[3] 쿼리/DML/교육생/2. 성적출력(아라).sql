/*

    교육생 요구사항 쿼리
    요구사항 2.
    성적 정보는 과목별로 목록 형태로 출력된다.

*/

-- * vwShowGradeList
-- 정보를 받아줄 vrow의 컬럼들을 모아놓은 뷰
create or replace view vwShowGradeList
as
select
        os.seq as seq,                            -- 개설과목번호
        sb.subjectname as subjectname,            -- 과목명
        os.startdate as startdate,                -- 과목시작일
        os.enddate as enddate,                    -- 과목종료일
        b.title as title,                         -- 교재명
        t.name as teachername,                    -- 교사명
        sa.handwritingAllot as handwritingallot,  -- 필기배점
        sa.practiceAllot as practiceallot,        -- 실기배점
        sa.attendanceAllot as attendanceallot,    -- 출결배점
        g.handwritingscore as handwritingscore,   -- 필기점수
        g.practicescore as practicescore,         -- 실기점수
        g.attendancescore as attendancescore,     -- 출결점수
        e.examdate as examdate                    -- 시험일
    from tblGrade g
        inner join tblGradeInfo gi
            on g.gradeinfoseq=gi.seq
                left outer join tblOpenSubject os
                    on gi.opensubseq=os.seq
                        inner join tblSubject sb
                            on os.subjectseq=sb.seq
                                inner join tblRegistration r
                                    on r.seq=gi.regseq
                                        inner join tblStudent s
                                            on s.seq=r.studentseq
                                                inner join tblBook b
                                                    on b.seq=sb.bookSeq
                                                        inner join tblScoreallot sa
                                                            on sa.opensubseq=os.seq
                                                                inner join tblTeacher t
                                                                    on os.teacherseq=t.seq
                                                                        inner join tblExam e
                                                                            on os.seq=e.opensubseq;




-- * procShowGradeList
-- 학생이 수강한 과목별 성적을 목록 형태로 출력하는 프로시저
-- 입력 : 학생명
-- 출력 : 과목번호, 과목명, 과목시작일, 과목종료일, 교재명, 교사명, 필기배점, 실기배점, 출결배점, 필기점수, 실기점수, 출결점수, 시험날짜
                                                                                        
create or replace procedure procShowGradeList(
    -- in
    pseq in tblStudent.seq%type,
    -- out
    pcursor out sys_refcursor
)
is
begin
    open pcursor
        for select
            os.seq,
            sb.subjectname,
            os.startdate,
            os.enddate,
            b.title,
            t.name,
            sa.handwritingAllot,
            sa.practiceAllot,
            sa.attendanceAllot,
            g.handwritingscore,
            g.practicescore,
            g.attendancescore,
            e.examdate
    from tblGrade g
        inner join tblGradeInfo gi
            on g.gradeinfoseq=gi.seq
                left outer join tblOpenSubject os
                    on gi.opensubseq=os.seq
                        inner join tblSubject sb
                            on os.subjectseq=sb.seq
                                inner join tblRegistration r
                                    on r.seq=gi.regseq
                                        inner join tblStudent s
                                            on s.seq=r.studentseq
                                                inner join tblBook b
                                                    on b.seq=sb.bookSeq
                                                        inner join tblScoreallot sa
                                                            on sa.opensubseq=os.seq
                                                                inner join tblTeacher t
                                                                    on os.teacherseq=t.seq
                                                                        inner join tblExam e
                                                                            on os.seq=e.opensubseq
                                                                                where s.seq=pseq
                                                                                    and sysdate>os.enddate
                                                                                        order by os.startdate;
end;



/*

-- procShowGradeList 테스트

declare
    vcursor sys_refcursor;
    vrow vwShowGradeList%rowtype;
begin
    procShowGradeList(117, vcursor);
    dbms_output.put_line('과목번호'||' '||'과목명'||' '||'시작일'||' '||'종료일'||' '||
                        '교재명'||' '||'교사명'||' '||'필기배점'||' '||'실기배점'||' '||
                        '출결배점'||' '||'필기점수'||' '||'실기점수'||' '||'출결점수'||' '||
                        '시험일');
    loop
        fetch vcursor into vrow;
        exit when vcursor%notfound;
        dbms_output.put_line(vrow.seq||' '||vrow.subjectname||' '||vrow.startdate||' '||
                            vrow.enddate||' '||vrow.title||' '||vrow.teachername||' '||
                            vrow.handwritingallot||' '||vrow.practiceallot||' '||
                            vrow.attendanceallot||' '||vrow.handwritingscore||' '||
                            vrow.practicescore||' '||vrow.attendancescore||' '||
                            vrow.examdate);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('성적정보가 없습니다.');
end;

*/