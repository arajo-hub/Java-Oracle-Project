-- 개설과정 더미데이터
-- 컬럼 : 개설과정번호, 과정시작일, 과정종료일, 강의실번호, 과정기초정보번호
-- 6건.

insert into tblOpenCourse values (seqTblOpenCourse.nextVal, to_date('20200701', 'yyyymmdd'), to_date('20201202', 'yyyymmdd'), 1, 1); --22주
insert into tblOpenCourse values (seqTblOpenCourse.nextVal, to_date('20200803', 'yyyymmdd'), to_date('20210104', 'yyyymmdd'), 2, 2); --22주
insert into tblOpenCourse values (seqTblOpenCourse.nextVal, to_date('20200901', 'yyyymmdd'), to_date('20210216', 'yyyymmdd'), 3, 3); --24주
insert into tblOpenCourse values (seqTblOpenCourse.nextVal, to_date('20201001', 'yyyymmdd'), to_date('20210318', 'yyyymmdd'), 4, 4); --24주
insert into tblOpenCourse values (seqTblOpenCourse.nextVal, to_date('20201102', 'yyyymmdd'), to_date('20210419', 'yyyymmdd'), 5, 5); --24주
insert into tblOpenCourse values (seqTblOpenCourse.nextVal, to_date('20201201', 'yyyymmdd'), to_date('20210615', 'yyyymmdd'), 6, 6); --28주