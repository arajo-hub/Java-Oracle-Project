package com.sisteducenter.attendance;

/**
 * 개설과목의 교육생별 출결조회를 위한 데이터를 다루는 DTO 클래스입니다.
 * @author 이준오
 *
 */
public class VwSubjectStudentAdminDTO {

	private String openSubSeq; // 개설과목번호
	private String subjectName; // 개설과목명
	private String studentSeq; // 교육생번호
	private String studentName; // 교육생명
	private String inTime; // 입실체크일
	private String state; // 출결상태
	
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
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	

}
