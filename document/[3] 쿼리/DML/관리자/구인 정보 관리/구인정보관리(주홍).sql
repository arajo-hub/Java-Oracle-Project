-- 구인공고 --------------------------------------------------------------------

-- a. 전체 내용 조회(관리자 - 마감된공고까지 전체 출력)
create or replace view vwJobInfoList
as
select rownum as No, j.* from 
(select 
    i.seq as seq,
    i.companyseq as cseq,
    c.name as 회사,
    ind.name as 업종,
    jobdivision as 모집직군,
    jobtype as 근무형태,
    to_char(annualincome, '9,999') as 연봉,
    to_char(p.startdate, 'yyyy-mm-dd') as 공고시작일,
    to_char(p.enddate, 'yyyy-mm-dd') as 공고마감일,
    case
        when sysdate < p.startdate then '예정'
        when p.startdate <= sysdate and sysdate < p.enddate+1 then '진행중'
        when p.enddate+1 <= sysdate then '마감'        
    end as 진행상태,
    recruitstep as 채용단계,
    detail as 내용
from tbljobinfo i                    -- 공고 테이블
    inner join tblcompany c     -- 회사 테이블
        on i.companyseq = c.seq
            inner join tbljobnoticeperiod p        -- 공고기간 테이블
                on i.seq = p.jobinfoseq
                    left outer join tblindustry ind     -- 업종 테이블
                        on c.industryseq = ind.seq
order by p.enddate desc, p.startdate desc) j;



-- 회사 ------------------------------------------------------------------------

-- a. 조회
create or replace view vwCompanyList
as
select
    c.seq as No,
    i.name as 업종,
    c.name as 회사명,
    ceo as 대표,
    to_char(establishDate, 'yyyy-mm-dd') as 설립일,
    employeeCount as 사원수,
    to_char(sales, '999,999,999,999,999') || '원' as 매출액,
    tel as 연락처,
    address as 주소
from tblCompany c
    left outer join tblindustry i
        on c.industrySeq = i.seq
order by c.seq;