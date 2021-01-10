package com.sisteducenter.teacher;


public class StudentInfo1DTO {

	private String os_seq;			//개설 과목 번호
	private String s_name;			//교육생 이름
	private String s_tel;			//전화번호
	private String reg_regdate;		//등록일
	private String state;			//참여상태
	private String s_subjectName;	//과목명
	
	public String getOs_seq() {
		return os_seq;
	}
	public void setOs_seq(String os_seq) {
		this.os_seq = os_seq;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_tel() {
		return s_tel;
	}
	public void setS_tel(String s_tel) {
		this.s_tel = s_tel;
	}
	public String getReg_regdate() {
		return reg_regdate;
	}
	public void setReg_regdate(String reg_regdate) {
		this.reg_regdate = reg_regdate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getS_subjectName() {
		return s_subjectName;
	}
	public void setS_subjectName(String s_subjectName) {
		this.s_subjectName = s_subjectName;
	}
	

}
