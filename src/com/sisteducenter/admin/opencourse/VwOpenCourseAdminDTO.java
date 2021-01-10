package com.sisteducenter.admin.opencourse;


/**
 * 개설과정정보를 담을 DTO 클래스입니다.
 * @author 이준오
 *
 */
public class VwOpenCourseAdminDTO {

	private String openCourSeq; // 개설과정번호
	private String courseName; // 개설과정명
	private String startDate; // 과정시작일
	private String endDate; // 과정종료일
	private String lectureRoomSeq; // 강의실번호
	
	public String getOpenCourSeq() {
		return openCourSeq;
	}
	public void setOpenCourSeq(String openCourSeq) {
		this.openCourSeq = openCourSeq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String name) {
		this.courseName = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLectureRoomSeq() {
		return lectureRoomSeq;
	}
	public void setLectureRoomSeq(String lectureRoomSeq) {
		this.lectureRoomSeq = lectureRoomSeq;
	}
		

}
