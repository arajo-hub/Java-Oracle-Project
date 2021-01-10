package com.sisteducenter.admin.opensubject;

/**
 * 과목기초정보를 담을 DTO 클래스입니다.
 * @author 장진영
 *
 */
public class SubjectListDTO {

	private String seq;
	private String subjectName;
	private String division;
	private String period;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	
}
