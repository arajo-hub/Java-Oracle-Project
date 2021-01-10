package com.sisteducenter.admin.opencourse;

/**
 * 교육생정보를 다루는 DTO 클래스입니다.
 * @author 이준오
 *
 */
public class VwStudentOpenCourseAdminDTO {

	private String studentSeq; // 교육생번호
	private String name; // 교육생명
	private String startDate; // 과정시작일
	private String endDate; // 과정종료일
	private String lectureRoomSeq; // 강의실
	private String state; // 수료 및 중도탈락
	private String stateDate; // 수료 및 중도탈락 날짜
	
	
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentseq) {
		this.studentSeq = studentseq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startdate) {
		this.startDate = startdate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String enddate) {
		this.endDate = enddate;
	}
	public String getLectureRoomSeq() {
		return lectureRoomSeq;
	}
	public void setLectureRoomSeq(String lectureroomseq) {
		this.lectureRoomSeq = lectureroomseq;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateDate() {
		return stateDate;
	}
	public void setStateDate(String statedate) {
		this.stateDate = statedate;
	}
	
	
	

}
