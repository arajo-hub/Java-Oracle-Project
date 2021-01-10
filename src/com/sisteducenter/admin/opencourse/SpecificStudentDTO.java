package com.sisteducenter.admin.opencourse;

//특정 개설 과정 정보 - 2
/**
 * 특정 개설 과정을 듣는 학생을 조회를 위한 DTO입니다.
 * 등록된 교육생 정보(교육생 이름, 주민번호 뒷자리, 전화번호, 등록일, 수료 및 중도탈락)
 * @author 장진영
 */
public class SpecificStudentDTO {

	private String seq;
	private String sName;		//이름
	private String ssn;			//주민번호 뒷자리
	private String tel;			//전화번호
	private String regdate;		//등록일
	private String regstate;	//수료상태
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getRegstate() {
		return regstate;
	}
	public void setRegstate(String regstate) {
		this.regstate = regstate;
	}
	
	
}
