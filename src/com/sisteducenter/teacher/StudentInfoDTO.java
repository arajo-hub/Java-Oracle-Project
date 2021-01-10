package com.sisteducenter.teacher;

/**
 * 교사 > 학생 정보 DTO
 * studentSeq 학생번호
 * studentName 학생이름
 * studentTel 전화번호
 * registState 진행상태
 * stateFailDate 중도포기날짜
 * writingScore 필기점수
 * practiceScore 실기점수
 * attendanceScore 출결점수
 * teacherSeq 교사 번호
 * openSubjectSeq 과목번호
 * @author 신지수
 *
 */
public class StudentInfoDTO {
	
	private String studentSeq;
	private String studentName;
	private String studentTel;
	private String registState;
	private String stateFailDate;
	private String writingScore;
	private String practiceScore;
	private String attendanceScore;
	private String teacherSeq;
	private String openSubjectSeq;
	private String courseSeq;
	
	
	public String getStudentSeq() {
		return studentSeq;
	}
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentTel() {
		return studentTel;
	}
	public void setStudentTel(String studentTel) {
		this.studentTel = studentTel;
	}
	public String getRegistState() {
		return registState;
	}
	public void setRegistState(String registState) {
		this.registState = registState;
	}
	public String getStateFailDate() {
		return stateFailDate;
	}
	public void setStateFailDate(String stateFailDate) {
		this.stateFailDate = stateFailDate;
	}
	public String getWritingScore() {
		return writingScore;
	}
	public void setWritingScore(String writingScore) {
		this.writingScore = writingScore;
	}
	public String getPracticeScore() {
		return practiceScore;
	}
	public void setPracticeScore(String practiceScore) {
		this.practiceScore = practiceScore;
	}
	public String getAttendanceScore() {
		return attendanceScore;
	}
	public void setAttendanceScore(String attendanceScore) {
		this.attendanceScore = attendanceScore;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getOpenSubjectSeq() {
		return openSubjectSeq;
	}
	public void setOpenSubjectSeq(String openSubjectSeq) {
		this.openSubjectSeq = openSubjectSeq;
	}
	public String getCourseSeq() {
		return courseSeq;
	}
	public void setCourseSeq(String courseSeq) {
		this.courseSeq = courseSeq;
	}
	
	@Override
	public String toString() {
		return "StudentInfoDTO [studentSeq=" + studentSeq + ", studentName=" + studentName + ", studentTel="
				+ studentTel + ", registState=" + registState + ", stateFailDate=" + stateFailDate + ", writingScore="
				+ writingScore + ", practiceScore=" + practiceScore + ", attendanceScore=" + attendanceScore
				+ ", teacherSeq=" + teacherSeq + ", openSubjectSeq=" + openSubjectSeq + ", courseSeq=" + courseSeq
				+ "]";
	}
	
	
	

}
