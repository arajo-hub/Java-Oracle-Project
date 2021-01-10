--1.강의스케줄 조회
create or replace procedure  procScheduleList(
    pseq in number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor for
        select 
                oc.seq as oc_seq,                                                       -- 과정번호
                os.seq as os_seq,                                                       -- 과목번호
                c.name as c_name,                                                       -- 과정명
                to_char(oc.startdate, 'yyyy-mm-dd') as oc_startdate,                    --과정시작일
                to_char(oc.enddate, 'yyyy-mm-dd') as oc_enddate,                        ---과정종료일
                case
                    when oc.startdate > sysdate then '강의 예정'
                    when oc.startdate < sysdate and  oc.enddate > sysdate then '강의 중'
                    when oc.enddate < sysdate  then '강의종료'
                end as state,                                                           --과정상태
                oc.lectureroomseq as oc_lectureroomseq,                                 --강의실
                s.subjectname as s_subjectname,                                         --과목명
                to_char(os.startdate,'yyyy-mm-dd') as os_startdate,                     --과목시작일
                to_char(os.enddate, 'yyyy-mm-dd') as os_enddate,                        --과목종료일
                b.title as b_title,                                                     --교재명
                a."count_stu_num" as stu_count--,                                         -- 교육생 등록 인원
                --t.seq as t_seq                                                          --교사번호
            from tblOpenSubject os --개설과목
                inner join tblOpenCourse oc --개설과정
                    on os.opencourseq = oc.seq 
                        inner join tblCourse c --과정기초정보
                            on oc.courseseq = c.seq
                                inner join tblSubject s --과목 기초정보
                                    on os.subjectseq = s.seq
                                        inner join tblBook b --교재
                                            on s.bookseq = b.seq
                                                inner join tblTeacher t
                                                    on os.teacherseq = t.seq
                                                        inner join (select opencourseq as "opencourseq", count(*) as "count_stu_num" from tblRegistration group by opencourseq order by opencourseq) a          
                                                            on a."opencourseq" = oc.seq
                        where t.seq = pseq
                            order by os.seq;
end procScheduleList;  





--2.특정 과목 학생 정보 조회
create or replace procedure procStudentInfo(
    pseq in number,
    pcursor out sys_refcursor
)
is
begin
    open pcursor for 
        select 
            os.seq as os_seq,                                   --개설 과목 번호
            s.name as s_name,                                   --교육생 이름
            s.tel as s_tel,  
            to_char(reg.regdate, 'yyyy-mm-dd') as reg_regdate,  --등록일
            case
                when reg.regstate = 'P' then '수료'
                when reg.regstate = 'Y' then '진행중'
                when reg.regstate = 'G' then '중도포기'
            end as stu_state,
            sub.subjectname as s_subjectname 
            
    from tblRegistration reg  --수강
        inner join tblopencourse oc --개설과정
            on reg.opencourseq = oc.seq 
                inner join tblOpenSubject os --개설과목
                    on os.opencourseq = oc.seq
                        inner join tblTeacher t --교사
                            on t.seq = os.teacherseq
                                inner join tblStudent s --교육생
                                    on s.seq = reg.studentseq
                                        inner join tblSubject sub --기초과목
                                             on sub.seq = os.subjectseq
                                                inner join tblCourse c --과정
                                                    on c.seq = oc.courseseq
            where os.seq = pseq; 
end procStudentInfo;