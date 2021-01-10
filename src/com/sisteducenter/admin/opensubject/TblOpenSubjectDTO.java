package com.sisteducenter.admin.opensubject;

/**
 * 개설과목정보를 담을 DTO 클래스입니다.
 * @author 장진영
 *
 */
public class TblOpenSubjectDTO {

	private String seq;         // 개설과목번호
	private String startdate;   // 과목시작일
	private String enddate;     // 과목종료일
	private String openCourseq; // 개설과정번호
	private String subjectseq;  // 과목기초정보번호
	private String teacherseq;  // 교사번호
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getOpenCourseq() {
		return openCourseq;
	}
	public void setOpenCourseq(String openCourseq) {
		this.openCourseq = openCourseq;
	}
	public String getSubjectseq() {
		return subjectseq;
	}
	public void setSubjectseq(String subjectseq) {
		this.subjectseq = subjectseq;
	}
	public String getTeacherseq() {
		return teacherseq;
	}
	public void setTeacherseq(String teacherseq) {
		this.teacherseq = teacherseq;
	}
	
}

