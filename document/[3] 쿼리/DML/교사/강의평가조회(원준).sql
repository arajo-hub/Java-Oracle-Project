--[5] 진행과정 평가 조회
create or replace procedure procCheckEvalCurrent
(
    pseq number, --교사번호
    pcursor out sys_refcursor
)
is
begin
    open pcursor for 
        select 
            os.seq as os_seq, --과목번호,
            s.subjectname as s_subjectname, --과목명,
            to_char(os.startdate, 'yyyy-mm-dd') as os_startdate,--과목시작일,
            to_char(os.enddate,'yyyy-mm-dd') as os_enddate, -- 과목종료일,
            case
                when os.startdate > sysdate then '과목 시작 예정'
                when os.startdate < sysdate and  os.enddate > sysdate then '과목 강의 중'
                when os.enddate < sysdate  then '과목종료'
            end as sub_state, --과목상태, 
            c.name as c_name, --과정명,
            to_char(oc.startdate, 'yyyy-mm-dd') as oc_startdate , --과정시작일,
            to_char(oc.enddate, 'yyyy-mm-dd') as oc_enddate, --과정종료일,
            a."count_stu_num" as stu_count, --"교육생 등록 인원",
            case
                when oc.startdate > sysdate then '강의 예정'
                when oc.startdate < sysdate and  oc.enddate > sysdate then '강의 중'
                when oc.enddate < sysdate  then '강의종료'
            end as course_state, --과정상태, 
            t.name as t_name, --교사이름, 
            (select round(sum(le.totalscore) / count(le.studentseq), 2)  from tblLectureEvaluation le inner join tblOpenSubject osub on le.opensubseq = osub.seq inner join tblTeacher t on osub.teacherseq = t.seq where osub.opencourseq = os.opencourseq and osub.subjectseq = os.subjectseq) as avg_score --평균점수    
        
        from tblOpenSubject os --개설과목
            inner join tblOpenCourse oc --개설과정
                on os.opencourseq = oc.seq 
                    inner join tblCourse c --과정기초정보
                        on oc.courseseq = c.seq
                            inner join tblSubject s --과목 기초정보
                                on os.subjectseq = s.seq
                                    inner join (select opencourseq as "opencourseq", count(*) as "count_stu_num" from tblRegistration group by opencourseq order by opencourseq) a          
                                        on a."opencourseq" = oc.seq
                                            inner join tblTeacher t
                                                on os.teacherseq = t.seq
                                                    
                where oc.enddate > sysdate and os.enddate < sysdate and t.seq = pseq
                    order by os.seq;
end procCheckEvalCurrent;



--[6] 과거 평과 조회(완)
create or replace procedure procCheckEvalEnd
(
    pseq number, --교사번호
    pcursor out sys_refcursor
)
is
begin
    open pcursor for 
        select 
           	os.seq as os_seq, --과목번호,
            s.subjectname as s_subjectname, --과목명,
            to_char(os.startdate, 'yyyy-mm-dd') as os_startdate,--과목시작일,
            to_char(os.enddate,'yyyy-mm-dd') as os_enddate, -- 과목종료일,
            case
                when os.startdate > sysdate then '과목 시작 예정'
                when os.startdate < sysdate and  os.enddate > sysdate then '과목 강의 중'
                when os.enddate < sysdate  then '과목종료'
            end as sub_state, --과목상태, 
            c.name as c_name, --과정명,
            to_char(oc.startdate, 'yyyy-mm-dd') as oc_startdate , --과정시작일,
            to_char(oc.enddate, 'yyyy-mm-dd') as oc_enddate, --과정종료일,
            a."count_stu_num" as stu_count, --"교육생 등록 인원",
            case
                when oc.startdate > sysdate then '강의 예정'
                when oc.startdate < sysdate and  oc.enddate > sysdate then '강의 중'
                when oc.enddate < sysdate  then '강의종료'
            end as course_state, --과정상태, 
            t.name as t_name, --교사이름, 
            (select round(sum(le.totalscore) / count(le.studentseq), 2)  from tblLectureEvaluation le inner join tblOpenSubject osub on le.opensubseq = osub.seq inner join tblTeacher t on osub.teacherseq = t.seq where osub.opencourseq = os.opencourseq and osub.subjectseq = os.subjectseq) as avg_score --평균점수    
        
        from tblOpenSubject os --개설과목
            inner join tblOpenCourse oc --개설과정
                on os.opencourseq = oc.seq 
                    inner join tblCourse c --과정기초정보
                        on oc.courseseq = c.seq
                            inner join tblSubject s --과목 기초정보
                                on os.subjectseq = s.seq
                                    inner join (select opencourseq as "opencourseq", count(*) as "count_stu_num" from tblRegistration group by opencourseq order by opencourseq) a          
                                        on a."opencourseq" = oc.seq
                                            inner join tblTeacher t
                                                on os.teacherseq = t.seq
                                                    
                where oc.enddate < sysdate and t.seq = pseq
                    order by os.seq;
end procCheckEvalEnd;


--강의를 마친 과목 출력1 <- 배점 입력 전에 출력해줄 쿼리
create or replace procedure EndSubList
(
    pseq number, --교사번호
    pcursor out sys_refcursor
)
is
begin
    open pcursor for 
            select
                os.seq as os_seq, --과목번호,
                s.subjectname as s_subjectname, --과목명,
                to_char(os.startdate, 'yyyy-mm-dd') as os_startdate,--과목시작일,
                to_char(os.enddate,'yyyy-mm-dd') as os_enddate, -- 과목종료일,
                case
                    when os.startdate > sysdate then '과목 시작 예정'
                    when os.startdate < sysdate and  os.enddate > sysdate then '과목 강의 중'
                    when os.enddate < sysdate  then '과목종료'
                end as sub_state, --과목상태, 
                oc.seq as oc_seq,
                c.name as c_name, --과정명,
                to_char(oc.startdate, 'yyyy-mm-dd') as oc_startdate , --과정시작일,
                to_char(oc.enddate, 'yyyy-mm-dd') as oc_enddate, --과정종료일,
                a."count_stu_num" as stu_count, --"교육생 등록 인원",
                case
                    when oc.startdate > sysdate then '강의 예정'
                    when oc.startdate < sysdate and  oc.enddate > sysdate then '강의 중'
                    when oc.enddate < sysdate  then '강의종료'
                end as course_state, --과정상태, 
                t.name as t_name --교사이름, 
                --(select round(sum(le.totalscore) / count(le.studentseq), 2)  from tblLectureEvaluation le inner join tblOpenSubject osub on le.opensubseq = osub.seq inner join tblTeacher t on osub.teacherseq = t.seq where osub.opencourseq = os.opencourseq and osub.subjectseq = os.subjectseq) as avg_score --평균점수    
                    
            from tblOpenSubject os --개설과목
                inner join tblOpenCourse oc --개설과정
                    on os.opencourseq = oc.seq 
                        inner join tblCourse c --과정기초정보
                            on oc.courseseq = c.seq
                                inner join tblSubject s --과목 기초정보
                                    on os.subjectseq = s.seq
                                        inner join (select opencourseq as "opencourseq", count(*) as "count_stu_num" from tblRegistration group by opencourseq order by opencourseq) a          
                                            on a."opencourseq" = oc.seq
                                                inner join tblTeacher t
                                                    on os.teacherseq = t.seq
                                                        
                    where os.enddate < sysdate and t.seq = pseq --pseq로 교사번호 받아오기 
                        order by os.seq; 
end;
        


--강의를 마친 과목을 출력2
create or replace procedure EndSubListForAllot
(
    pseq number, --교사번호
    pcursor out sys_refcursor
)
is
begin
    open pcursor for 
            select
                oc.seq as oc_seq, --개설 과정 번호
                os.seq as os_seq, --과목번호,
                s.subjectname as s_subjectname, --과목명,
                to_char(os.startdate, 'yyyy-mm-dd') as os_startdate,--과목시작일,
                to_char(os.enddate,'yyyy-mm-dd') as os_enddate, -- 과목종료일,
                case
                    when os.startdate > sysdate then '과목 시작 예정'
                    when os.startdate < sysdate and  os.enddate > sysdate then '과목 강의 중'
                    when os.enddate < sysdate  then '과목종료'
                end as sub_state, --과목상태, 
                
                c.name as c_name, --과정명,
                to_char(oc.startdate, 'yyyy-mm-dd') as oc_startdate , --과정시작일,
                to_char(oc.enddate, 'yyyy-mm-dd') as oc_enddate, --과정종료일,
                a."count_stu_num" as stu_count, --"교육생 등록 인원",
                case
                    when oc.startdate > sysdate then '강의 예정'
                    when oc.startdate < sysdate and  oc.enddate > sysdate then '강의 중'
                    when oc.enddate < sysdate  then '강의종료'
                end as course_state --과정상태, 
                --t.name as t_name --교사이름, 
                --(select round(sum(le.totalscore) / count(le.studentseq), 2)  from tblLectureEvaluation le inner join tblOpenSubject osub on le.opensubseq = osub.seq inner join tblTeacher t on osub.teacherseq = t.seq where osub.opencourseq = os.opencourseq and osub.subjectseq = os.subjectseq) as avg_score --평균점수    
                    
            from tblOpenSubject os --개설과목
                inner join tblOpenCourse oc --개설과정
                    on os.opencourseq = oc.seq 
                        inner join tblCourse c --과정기초정보
                            on oc.courseseq = c.seq
                                inner join tblSubject s --과목 기초정보
                                    on os.subjectseq = s.seq
                                        inner join (select opencourseq as "opencourseq", count(*) as "count_stu_num" from tblRegistration group by opencourseq order by opencourseq) a          
                                            on a."opencourseq" = oc.seq
                                                inner join tblTeacher t
                                                    on os.teacherseq = t.seq
                                                        
                    where os.enddate < sysdate and t.seq =pseq --pseq로 교사번호 받아오기 
                        order by os.seq; 
end;
