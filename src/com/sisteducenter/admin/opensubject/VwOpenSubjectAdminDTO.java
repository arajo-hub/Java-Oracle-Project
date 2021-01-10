package com.sisteducenter.admin.opensubject;

/**
 * 
 * @author 이준오
 *
 */
public class VwOpenSubjectAdminDTO {

	private String openSubSeq;
	private String subjectName;
	private String startDate;
	private String endDate;
	private String teacherName;
	private String lectureRoomSeq;
	
	
	
	public String getOpenSubSeq() {
		return openSubSeq;
	}
	public void setOpenSubSeq(String openSubSeq) {
		this.openSubSeq = openSubSeq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getLectureRoomSeq() {
		return lectureRoomSeq;
	}
	public void setLectureRoomSeq(String lectureRoomSeq) {
		this.lectureRoomSeq = lectureRoomSeq;
	}
	
	

}
