package com.sisteducenter.admin.lectureevaluation;

/**
 * 강의평가를 다루기 위한 DTO입니다.
 * @author 장진영
 *
 */
public class LectureEvaluationAdminDTO {

	
	private String seq;
	private String subjectName;			//과목이름
	private String sName;				//학생이름
	private String prepareationScore;	//준비
	private String understandScore;		//이해
	private String usefulScore;			//유용
	private String totalScore;			//총합
	
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
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getPrepareationScore() {
		return prepareationScore;
	}
	public void setPrepareationScore(String prepareationScore) {
		this.prepareationScore = prepareationScore;
	}
	public String getUnderstandScore() {
		return understandScore;
	}
	public void setUnderstandScore(String understandScore) {
		this.understandScore = understandScore;
	}
	public String getUsefulScore() {
		return usefulScore;
	}
	public void setUsefulScore(String usefulScore) {
		this.usefulScore = usefulScore;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	
}
