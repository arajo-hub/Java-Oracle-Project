package com.sisteducenter.teacher;

/**
 * 데이터베이스에 입력할 배점정보를 담고 있는 DTO 클래스입니다.
 * @author 황원준
 *
 */
public class InputAllotDTO {
	
	private String sub;
	private String openCourse;
	private String write;
	private String practice;
	private String attendance;
	private String examDate;
	
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getOpenCourse() {
		return openCourse;
	}
	public void setOpenCourse(String openCourse) {
		this.openCourse = openCourse;
	}
	public String getWrite() {
		return write;
	}
	public void setWrite(String write) {
		this.write = write;
	}
	public String getPractice() {
		return practice;
	}
	public void setPractice(String practice) {
		this.practice = practice;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	
	

}
