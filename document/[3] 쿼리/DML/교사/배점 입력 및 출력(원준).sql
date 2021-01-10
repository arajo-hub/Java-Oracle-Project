--3.배점입력(출결,필기,실기) + 시험날짜 입력
create or replace procedure procAddScoreAllot
(
    psub number,        --과목번호
    popenCourse number, --—개설과정 번호
    phand number,      -- —필기배점
    ppractice number,   --—실기배점
    pattendance number, --—출결배점
    pexamdate date      --—시험날짜
)
is
begin
    insert into tblScoreAllot values (seqTblScoreAllot.nextVal, phand, ppractice, pattendance, psub); --— 필기배점 + 실기배점 + 출결배점 입력
    /*
    update tblExam — 시험날짜 입력
        set  examdate = pexamdate
            where seq = (select seq from tblopenSubject where openCourSeq = popenCourse  and subjectSeq = psub);
            */
end procAddScoreAllot;



--4.배점 출력
create or replace procedure procCheckScoreAllot
(
    pseq number, --개설과정 번호. 개설과정번호 입력하면 [12개의 과목+배점] 출력
    pcursor out sys_refcursor
)
is
begin
    open pcursor for 
        select 
            os.seq as os_seq, --과목번호
            c.name as c_name, --과정명,
            to_char(oc.startdate, 'yyyy-mm-dd') as oc_startdate,--과정시작일,
            to_char(oc.enddate, 'yyyy-mm-dd') as oc_enddate, --과정종료일,
            oc.lectureroomseq as oc_lectureroomseq, --강의실,
            s.subjectname as s_subjectname, --과목명,
            to_char(os.startdate, 'yyyy-mm-dd') as os_startdate,--과목시작일,
            to_char(os.enddate, 'yyyy-mm-dd') as os_enddate,--과목종료일,
            b.title as b_title, --교재명,
            sa.attendanceallot as sa_attendanceallot, --출결배점,
            sa.handwritingallot as sa_handwritingallot, --필기배점,
            sa.practiceallot as sa_practiceallot --실기배점
                
        from tblOpenSubject os --개설과목
            inner join tblOpenCourse oc --개설과정
                on os.opencourseq = oc.seq 
                    inner join tblCourse c --과정기초정보
                        on oc.courseseq = c.seq
                            inner join tblSubject s --과목 기초정보
                                on os.subjectseq = s.seq
                                    inner join tblBook b --교재
                                        on s.bookseq = b.seq
                                            inner join tblScoreAllot sa --배점
                                                on sa.opensubseq = os.seq
            where oc.seq = pseq; 
end procCheckScoreAllot;