package com.sisteducenter.admin.grade;

/**
 * 개설과목번호나 교육생번호로 가져오는 성적정보를 담을 DTO 클래스입니다.
 * @author 이준오
 *
 */
public class VwOpenSubGradeAdminDTO {

	
	private String courseName;
	private String courseStartDate;
	private String courseEndDate;
	private String lectureRoomSeq;
	private String subjectName;
	private String teacherName;
	private String bookTitle;
	private String studentName;
	private String idNum;
	private String handwritingScore;
	private String practiceScore;
	private String studentSeq;
	private String subjectStartDate;
	private String subjectEndDate;
	private String subjectSeq;
	
	public String getSubjectSeq() {
		return subjectSeq;
	}
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseStartDate() {
		return courseStartDate;
	}
	public void setCourseStartDate(String courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	public String getCourseEndDate() {
		return courseEndDate;
	}
	public void setCourseEndDate(String courseEndDate) {
		this.courseEndDate = courseEndDate;
	}
	public String getLectureRoomSeq() {
		return lectureRoomSeq;
	}
	public void setLectureRoomSeq(String lectureRoomSeq) {
		this.lectureRoomSeq = lectureRoomSeq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getHandwritingScore() {
		return handwritingScore;
	}
	public void setHandwritingScore(String handwritingScore) {
		this.handwritingScore = handwritingScore;
	}
	public String getPracticeScore() {
		return practiceScore;
	}
	public void setPracticeScore(String practiceScore) {
		this.practiceScore = practiceScore;
	}
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	public String getSubjectStartDate() {
		return subjectStartDate;
	}
	public void setSubjectStartDate(String subjectStartDate) {
		this.subjectStartDate = subjectStartDate;
	}
	public String getSubjectEndDate() {
		return subjectEndDate;
	}
	public void setSubjectEndDate(String subjectEndDate) {
		this.subjectEndDate = subjectEndDate;
	}
	
	

}
