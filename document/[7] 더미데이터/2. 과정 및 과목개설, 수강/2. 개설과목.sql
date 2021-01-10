-- 개설과목 더미데이터

--개설강좌(진행중 6개)
--insert into tblOpenCourse(seq, startDate, endDate, lectureRoomSeq, courseSeq)
--insert into tblOpenCourse values (seqTblOpenCourse.nextVal, '20200701', '20201202', 1, 1); --22주
--insert into tblOpenCourse values (seqTblOpenCourse.nextVal, '20200803', '20210104', 2, 2); --22주
--insert into tblOpenCourse values (seqTblOpenCourse.nextVal, '20200901', '20200216', 3, 3); --24주
--insert into tblOpenCourse values (seqTblOpenCourse.nextVal, '20201001', '20210318', 4, 4); --24주
--insert into tblOpenCourse values (seqTblOpenCourse.nextVal, '20201102', '20210419', 5, 5); --24주
--insert into tblOpenCourse values (seqTblOpenCourse.nextVal, '20201201', '20210615', 6, 6); --28주


-- 개설과목 데이터 삽입

-- 개설강좌1 (22주)
--공통과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-07-01', '2020-07-14', '1', '1', '1'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-07-15', '2020-07-28', '1', '2', '1'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-07-29', '2020-08-11', '1', '3', '1'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-08-12', '2020-08-25', '1', '4', '1'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-08-26', '2020-09-08', '1', '5', '1'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-09-09', '2020-09-22', '1', '6', '1'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-09-23', '2020-10-06', '1', '7', '1'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-10-07', '2020-10-20', '1', '8', '1'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-10-21', '2020-11-03', '1', '9', '1'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-04', '2020-11-17', '1', '10', '1'); 
--추가과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-18', '2020-11-24', '1', '19', '1');
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-25', '2020-12-01', '1', '20', '1');


-- 개설강좌2 (22주)
--공통과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-08-03', '2020-08-16', '2', '1', '2'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-08-17', '2020-08-30', '2', '2', '2'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-08-31', '2020-09-13', '2', '3', '2'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-09-14', '2020-09-27', '2', '4', '2'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-09-28', '2020-10-11', '2', '5', '2'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-10-12', '2020-10-25', '2', '6', '2'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-10-26', '2020-11-08', '2', '7', '2'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-09', '2020-11-22', '2', '8', '2'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-23', '2020-12-06', '2', '9', '2'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-07', '2020-12-20', '2', '10', '2'); 
--추가과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-21', '2020-12-27', '2', '19', '2');
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-28', '2021-01-03', '2', '20', '2');


-- 개설강좌3 (24주)
--공통과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-09-01', '2020-09-14', '3', '1', '3'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-09-15', '2020-09-28', '3', '2', '3'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-09-29', '2020-10-12', '3', '3', '3'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-10-13', '2020-10-26', '3', '4', '3'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-10-27', '2020-11-09', '3', '5', '3'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-10', '2020-11-23', '3', '6', '3'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-24', '2020-12-07', '3', '7', '3'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-08', '2020-12-21', '3', '8', '3'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-22', '2021-01-04', '3', '9', '3'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-01-05', '2021-01-18', '3', '10', '3'); 
--추가과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-01-19', '2021-02-01', '3', '17', '3');
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-02-02', '2021-02-15', '3', '18', '3');


-- 개설강좌4 (24주)
--공통과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-10-01', '2020-10-14', '4', '1', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-10-15', '2020-10-28', '4', '2', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-10-29', '2020-11-11', '4', '3', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-12', '2020-11-25', '4', '4', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-26', '2020-12-09', '4', '5', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-10', '2020-12-23', '4', '6', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-24', '2021-01-06', '4', '7', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-01-07', '2021-01-20', '4', '8', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-01-21', '2021-02-03', '4', '9', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-02-04', '2021-02-17', '4', '10', '4'); 

--추가과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-02-18', '2021-03-03', '4', '15', '4'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-03-04', '2021-03-17', '4', '16', '4'); 



-- 개설강좌5 (24주)
--공통과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-02', '2020-11-15', '5', '1', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-16', '2020-11-29', '5', '2', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-11-30', '2020-12-13', '5', '3', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-14', '2020-12-27', '5', '4', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-28', '2021-01-10', '5', '5', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-01-11', '2021-01-24', '5', '6', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-01-25', '2021-02-07', '5', '7', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-02-08', '2021-02-21', '5', '8', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-02-22', '2021-03-07', '5', '9', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-03-08', '2021-03-21', '5', '10', '5'); 
--추가과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-03-22', '2021-04-04', '5', '13', '5'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-04-05', '2021-04-18', '5', '14', '5'); 


-- 개설강좌6 (28주)
--공통과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-01', '2020-12-14', '6', '1', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-15', '2020-12-28', '6', '2', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2020-12-29', '2021-01-11', '6', '3', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-01-12', '2021-01-25', '6', '4', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-01-26', '2021-02-08', '6', '5', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-02-09', '2021-02-22', '6', '6', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-02-23', '2021-03-08', '6', '7', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-03-09', '2021-03-22', '6', '8', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-03-23', '2021-04-05', '6', '9', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-04-06', '2021-04-19', '6', '10', '6'); 
--추가과목
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-04-20', '2021-05-17', '6', '11', '6'); 
insert into tblOpenSubject (seq, startDate, endDate, openCourSeq, subjectSeq, teacherSeq) values (seqTblOpenSubject.nextVal, '2021-05-18', '2021-06-14', '6', '12', '6'); 