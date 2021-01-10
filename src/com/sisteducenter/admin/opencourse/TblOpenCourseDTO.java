package com.sisteducenter.admin.opencourse;

/**
 * 개설과정을 나타내는 DTO 클래스입니다.
 * @author 장진영
 *
 */
public class TblOpenCourseDTO {
	
	private String seq;
	private String startdate;
	private String enddate;
	private String lectureroomseq;
	private String courseseq;
	
	
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
	public String getLectureroomseq() {
		return lectureroomseq;
	}
	public void setLectureroomseq(String lectureroomseq) {
		this.lectureroomseq = lectureroomseq;
	}
	public String getCourseseq() {
		return courseseq;
	}
	public void setCourseseq(String courseseq) {
		this.courseseq = courseseq;
	}
	
	

}
