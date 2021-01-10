package com.sisteducenter.teacher;

/**
 * 교사 > 출결 정보 확인 DTO
 * CourseSeq
 * courseName
 * studentSeq
 * studentName
 * attendanceIn
 * attendanceOut
 * teacherSeq
 * courseStart
 * courseEnd
 * @author 신지수
 *
 */
public class AttendanceDTO {
	
	private String CourseSeq;
	private String courseName;
	private String studentSeq;
	private String studentName;
	private String attendanceIn;
	private String attendanceOut;
	private String teacherSeq;
	private String courseStart;
	private String courseEnd;
	private String inDate; //입실시간 날짜
	private String outDate;
	private String state; //근태
	
	public String getCourseSeq() {
		return CourseSeq;
	}
	public void setCourseSeq(String courseSeq) {
		CourseSeq = courseSeq;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
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
	public String getAttendanceIn() {
		return attendanceIn;
	}
	public void setAttendanceIn(String attendanceIn) {
		this.attendanceIn = attendanceIn;
	}
	public String getAttendanceOut() {
		return attendanceOut;
	}
	public void setAttendanceOut(String attendanceOut) {
		this.attendanceOut = attendanceOut;
	}
	public String getTeacherSeq() {
		return teacherSeq;
	}
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	public String getCourseStart() {
		return courseStart;
	}
	public void setCourseStart(String courseStart) {
		this.courseStart = courseStart;
	}
	public String getCourseEnd() {
		return courseEnd;
	}
	public void setCourseEnd(String courseEnd) {
		this.courseEnd = courseEnd;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "AttendanceDTO [CourseSeq=" + CourseSeq + ", courseName=" + courseName + ", studentSeq=" + studentSeq
				+ ", studentName=" + studentName + ", attendanceIn=" + attendanceIn + ", attendanceOut=" + attendanceOut
				+ ", teacherSeq=" + teacherSeq + ", courseStart=" + courseStart + ", courseEnd=" + courseEnd
				+ ", inDate=" + inDate + ", state=" + state + "]";
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	
	
	
}
