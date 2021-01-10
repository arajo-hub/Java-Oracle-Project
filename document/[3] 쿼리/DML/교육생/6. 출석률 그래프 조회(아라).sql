/*

    교육생 요구사항 쿼리
    
    요구사항 7.
    본인의 출석률을 그래프로 조회할 수 있어야 한다.

*/

-- 출결 %로 조회
-- 출석률 = 출석일수(정상)/전체날짜

-- 1. 전체날짜 계산
-- 공휴일 제외

create table tblHoliday
(
    seq number primary key,
    regdate date not null,
    name varchar2(30) not null
);

create sequence seqTblHoliday;

-- 2020년
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-01-01', '새해');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-01-24', '설연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-01-25', '설연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-01-25', '설연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-03-01', '삼일절');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-04-30', '석가탄신일');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-05-05', '어린이날');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-06-06', '현충일');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-08-15', '광복절');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-08-17', '광복절대체휴일');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-09-30', '추석연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-10-01', '추석연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-10-02', '추석연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-10-03', '개천절');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-10-09', '한글날');
insert into tblHoliday values(seqTblHoliday.nextVal, '2020-12-25', '크리스마스');

-- 2021년
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-01-01', '새해');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-02-11', '설연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-02-12', '설연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-02-13', '설연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-03-01', '삼일절');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-05-05', '어린이날');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-05-19', '석가탄신일');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-06-06', '현충일');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-08-15', '광복절');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-09-20', '추석연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-09-21', '추석연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-09-22', '추석연휴');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-10-03', '개천절');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-10-09', '한글날');
insert into tblHoliday values(seqTblHoliday.nextVal, '2021-12-25', '크리스마스');




-- 전체 날짜를 담고 있는 뷰
-- 시작은 2020년부터.
-- 종료는 2999년까지.(임의)
create or replace view vwDate
as
select to_date('2020-01-01', 'yyyy-mm-dd')+level-1 as regdate from dual
    connect by level <=(to_date('2999-12-31', 'yyyy-mm-dd')-to_date('2020-01-01', 'yyyy-mm-dd')+1);

select * from vwDate;


-- * procShowAttendRate
-- 출석률을 계산해주는 프로시저.
-- 입력 : 교육생번호
-- 출력 : 결과(출석률)
create or replace procedure procShowAttendRate
(
    pseq tblStudent.seq%type,
    prate out number,
    presult out number
)
is
    palldate number;
    pattenddate number;
begin

    -- 출석해야 하는 일수
    select count(*) into palldate
    from
    (select d.regdate
        from vwDate d
            inner join tblOpenCourse oc
                on to_char(d.regdate, 'yyyy-mm-dd')
                    between to_char(oc.startdate, 'yyyy-mm-dd')
                        and to_char(oc.enddate, 'yyyy-mm-dd')
                        inner join tblRegistration r
                            on r.openCourSeq=oc.seq
                                inner join tblStudent s
                                    on s.seq=r.studentseq
                                    where oc.seq=r.opencourseq
                                        and s.seq=pseq
                                        and to_char(d.regdate, 'd') not in ('1', '7')
    minus
    select regdate from tblHoliday); -- 공휴일 제외 
    
    
    
    -- 출석일수
    select count(distinct a.intime) into pattenddate from tblAttendance a
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
    
    -- 출석률 계산
    select trunc(pattenddate/palldate*100, 2) into prate from dual;
    presult:=0;
EXCEPTION
  WHEN ZERO_DIVIDE THEN -- 수강한 과정이 없을 때
      presult:=1;
end;

/*

-- procShowAttendRate 테스트

declare
    vrate number;
    vresult number;
begin
    procShowAttendRate(200, vrate, vresult);
    if vresult=0 then
        dbms_output.put_line('출석률 : '||vrate);
    else
        dbms_output.put_line('수강중인 과정 없음');
    end if;
end;

*/