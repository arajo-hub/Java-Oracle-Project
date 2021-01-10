/*

    교육생 요구사항 쿼리
    
    요구사항 8.
    출결상태별 횟수를 조회할 수 있어야 한다.

*/
select * from tblAttendance where regseq=97;

create or replace procedure procShowStateCount
(
    pseq tblStudent.seq%type,
    pnormal out number, -- 정상 횟수
    plate out number, -- 지각 횟수
    pearly out number, -- 조퇴 횟수
    pgoout out number, -- 외출 횟수
    psick out number, -- 병가 횟수
    petc out number -- 기타 횟수
)
is
begin
    -- 정상 횟수
    select count(distinct a.intime) into pnormal from tblAttendance a
    inner join tblRegistration r
        on a.regseq=r.seq
            inner join tblStudent s
                on s.seq=r.studentseq
                    inner join tblOpenCourse oc
                        on r.opencourseq=oc.seq
                            where s.seq=pseq
                                and a.stateseq=1
                                and to_char(a.inTime, 'yyyymmdd') between to_char(oc.startdate, 'yyyymmdd')
                                    and to_char(oc.enddate, 'yyyymmdd')
                                and a.inTime<=sysdate;
                                        
    -- 지각 횟수
    select count(distinct a.intime) into plate from tblAttendance a
    inner join tblRegistration r
        on a.regseq=r.seq
            inner join tblStudent s
                on s.seq=r.studentseq
                    inner join tblOpenCourse oc
                        on r.opencourseq=oc.seq
                            where s.seq=pseq
                                and a.stateseq=2
                                and to_char(a.inTime, 'yyyymmdd') between to_char(oc.startdate, 'yyyymmdd')
                                    and to_char(oc.enddate, 'yyyymmdd')
                                and a.inTime<=sysdate;
    
    -- 조퇴 횟수
    select count(distinct a.intime) into pearly from tblAttendance a
    inner join tblRegistration r
        on a.regseq=r.seq
            inner join tblStudent s
                on s.seq=r.studentseq
                    inner join tblOpenCourse oc
                        on r.opencourseq=oc.seq
                            where s.seq=pseq
                                and a.stateseq=3
                                and to_char(a.inTime, 'yyyymmdd') between to_char(oc.startdate, 'yyyymmdd')
                                    and to_char(oc.enddate, 'yyyymmdd')
                                and a.inTime<=sysdate;
    -- 외출 횟수
    select count(distinct a.intime) into pgoout from tblAttendance a
    inner join tblRegistration r
        on a.regseq=r.seq
            inner join tblStudent s
                on s.seq=r.studentseq
                    inner join tblOpenCourse oc
                        on r.opencourseq=oc.seq
                            where s.seq=pseq
                                and a.stateseq=4
                                and to_char(a.inTime, 'yyyymmdd') between to_char(oc.startdate, 'yyyymmdd')
                                    and to_char(oc.enddate, 'yyyymmdd')
                                and a.inTime<=sysdate;
                                        
    -- 병가 횟수
    select count(distinct a.intime) into psick from tblAttendance a
    inner join tblRegistration r
        on a.regseq=r.seq
            inner join tblStudent s
                on s.seq=r.studentseq
                    inner join tblOpenCourse oc
                        on r.opencourseq=oc.seq
                            where s.seq=pseq
                                and a.stateseq=5
                                and to_char(a.inTime, 'yyyymmdd') between to_char(oc.startdate, 'yyyymmdd')
                                    and to_char(oc.enddate, 'yyyymmdd')
                                and a.inTime<=sysdate;
    
    -- 기타 횟수
    select count(distinct a.intime) into petc from tblAttendance a
    inner join tblRegistration r
        on a.regseq=r.seq
            inner join tblStudent s
                on s.seq=r.studentseq
                    inner join tblOpenCourse oc
                        on r.opencourseq=oc.seq
                            where s.seq=pseq
                                and a.stateseq=6
                                and to_char(a.inTime, 'yyyymmdd') between to_char(oc.startdate, 'yyyymmdd')
                                    and to_char(oc.enddate, 'yyyymmdd')
                                and a.inTime<=sysdate;
    
end;

declare
    vnormal number;
    vlate number;
    vearly number;
    vgoout number;
    vsick number;
    vetc number;
begin
    procShowStateCount(140, vnormal, vlate, vearly, vgoout, vsick, vetc);
    dbms_output.put_line('정상 : '||vnormal||'회, '||
                        '지각 : '||vlate||'회, '||
                        '조퇴 : '||vearly||'회, '||
                        '외출 : '||vgoout||'회, '||
                        '병가 : '||vsick||'회, '||
                        '기타 : '||vetc||'회');
end;