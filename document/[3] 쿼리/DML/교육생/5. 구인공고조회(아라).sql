/*

    교육생 요구사항 쿼리
    요구사항 5.
    현재 진행중인 구인공고 목록을 조회할 수 있다.

*/

-- * vwShowJobInfoLatest
-- 구인정보를 조회할 뷰
create or replace view vwShowJobInfo
as
select c.name,
    i.name as industryname,
    c.sales,
    ji.jobdivision,
    ji.jobtype,
    ji.annualincome,
    ji.recruitstep,
    ji.detail,
    jnp.startdate,
    jnp.enddate
from tblCompany c
    inner join tblJobInfo ji
        on c.seq=ji.companyseq
            inner join tblJobNoticePeriod jnp
                on ji.seq=jnp.jobinfoSeq
                    inner join tblIndustry i
                        on c.industrySeq=i.seq
                            where to_char(sysdate, 'yyyy-mm-dd')
                                between to_char(jnp.startdate, 'yyyy-mm-dd')
                                and to_char(jnp.enddate, 'yyyy-mm-dd');

-- 구인정보를 최신순으로 조회
select name as 회사명,
    industryname as 업종명,
    sales as 매출액,
    jobdivision as 모집직군,
    jobtype as 근무형태,
    annualIncome as 연봉,
    recruitstep as 채용단계,
    detail as 공고내용,
    to_char(startDate, 'yyyy-mm-dd') as 공고시작일,
    to_char(endDate, 'yyyy-mm-dd') as 공고마감일
from vwShowJobInfo
    order by vwShowJobInfo.startDate desc;

-- 구인정보를 연봉순으로 조회
select name as 회사명,
    industryname as 업종명,
    sales as 매출액,
    jobdivision as 모집직군,
    jobtype as 근무형태,
    annualIncome as 연봉,
    recruitstep as 채용단계,
    detail as 공고내용,
    to_char(startDate, 'yyyy-mm-dd') as 공고시작일,
    to_char(endDate, 'yyyy-mm-dd') as 공고마감일
from vwShowJobInfo
    order by vwShowJobInfo.annualincome desc;
    
    
-- 구인정보를 매출액순으로 조회
select name as 회사명,
    industryname as 업종명,
    sales as 매출액,
    jobdivision as 모집직군,
    jobtype as 근무형태,
    annualIncome as 연봉,
    recruitstep as 채용단계,
    detail as 공고내용,
    to_char(startDate, 'yyyy-mm-dd') as 공고시작일,
    to_char(endDate, 'yyyy-mm-dd') as 공고마감일
from vwShowJobInfo
    order by vwShowJobInfo.sales desc;