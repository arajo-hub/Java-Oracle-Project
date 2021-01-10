package com.sisteducenter.admin.opencourse;

/**
 * 개설 과정 조회를 위한 DTO 입니다.
 * 개설 과정명, 개설 과정기간(시작 년월일, 끝 년월일), 강의실명, 개설 과목 등록 여부, 교육생 등록 인원
 * @author 장진영
 */
public class OpenCourseListDTO {

	private String seq;				
	private String coursename;		//개설 과정명
	private String startdate;		//개설 과정기간(시작)
	private String enddate;			//개설 과정기간(끝)
	private String lectureroom;		//강의실명
	private String regstudentnumber;//교육생 등록 인원
	private String courseseq;
	
	public String getCourseseq() {
		return courseseq;
	}
	public void setCourseseq(String courseseq) {
		this.courseseq = courseseq;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
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
	public String getLectureroom() {
		return lectureroom;
	}
	public void setLectureroom(String lectureroom) {
		this.lectureroom = lectureroom;
	}
	public String getRegstudentnumber() {
		return regstudentnumber;
	}
	public void setRegstudentnumber(String regstudentnumber) {
		this.regstudentnumber = regstudentnumber;
	}
	
	
}
