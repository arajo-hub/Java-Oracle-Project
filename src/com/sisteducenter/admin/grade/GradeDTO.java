package com.sisteducenter.admin.grade;

/**
 * 성적정보를 다루기 위한 DTO 클래스입니다.
 * @author 조아라
 *
 */
public class GradeDTO {

	private String courseSeq; // 개설과정번호
	private String subjectName; // 과목명
	private String startDate; // 과목시작일
	private String endDate; // 과목종료일
	private String book; // 교재명
	private String teacherName; // 교사명
	private int handwritingAllot; // 필기배점
	private int practiceAllot; // 실기배점
	private int attendanceAllot; // 출결배점
	private int handwritingScore; // 필기점수
	private int practiceScore; // 실기점수
	private int attendanceScore; // 출결점수
	private String examDate; // 시험일
	
	public String getCourseSeq() {
		return courseSeq;
	}
	public void setCourseSeq(String courseSeq) {
		this.courseSeq = courseSeq;
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
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getHandwritingAllot() {
		return handwritingAllot;
	}
	public void setHandwritingAllot(int handwritingAllot) {
		this.handwritingAllot = handwritingAllot;
	}
	public int getPracticeAllot() {
		return practiceAllot;
	}
	public void setPracticeAllot(int practiceAllot) {
		this.practiceAllot = practiceAllot;
	}
	public int getAttendanceAllot() {
		return attendanceAllot;
	}
	public void setAttendanceAllot(int attendanceAllot) {
		this.attendanceAllot = attendanceAllot;
	}
	public int getHandwritingScore() {
		return handwritingScore;
	}
	public void setHandwritingScore(int handwritingScore) {
		this.handwritingScore = handwritingScore;
	}
	public int getPracticeScore() {
		return practiceScore;
	}
	public void setPracticeScore(int practiceScore) {
		this.practiceScore = practiceScore;
	}
	public int getAttendanceScore() {
		return attendanceScore;
	}
	public void setAttendanceScore(int attendanceScore) {
		this.attendanceScore = attendanceScore;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
}
