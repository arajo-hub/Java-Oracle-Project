package com.sisteducenter.attendance;


/**
 * 관리자가 교육생 출결조회를 하기 위한 데이터를 담당할 DTO 클래스입니다.
 * @author 이준오
 *
 */
public class VwStudentInSubjectAdminDTO {


	private String studentSeq; // 교육생번호
	private String studentName; // 교육생이름
	private String inDate; // 입실체크일
	private String state; // 출결상태
	
	
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
	
	

}
