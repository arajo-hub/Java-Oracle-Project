package com.sisteducenter.admin.lectureevaluation;

/**
 * 강의평가를 다루기 위한 DTO 클래스입니다.
 * String subjectseq : 개설과목번호
 * String subjectName : 개설과목명
 * String period : 과목기간
 * String division : 과목구분
 * String preparationscore : 수업준비점수
 * String understandscore : 내용전달점수
 * String usefulscore : 유익도점수
 * String totalscore : 수업준비점수, 내용전달점수, 유익도점수 평균.
 * @author 조아라
 *
 */
public class LectureEvaluationStudentDTO {

	private String subjectseq;
	private String subjectName;
	private String period;
	private String division;
	private String preparationscore;
	private String understandscore;
	private String usefulscore;
	private int totalscore;
	
	public String getSubjectseq() {
		return subjectseq;
	}
	public void setSubjectseq(String subjectseq) {
		this.subjectseq = subjectseq;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getPreparationscore() {
		return preparationscore;
	}
	public void setPreparationscore(String preparationscore) {
		this.preparationscore = preparationscore;
	}
	public String getUnderstandscore() {
		return understandscore;
	}
	public void setUnderstandscore(String understandscore) {
		this.understandscore = understandscore;
	}
	public String getUsefulscore() {
		return usefulscore;
	}
	public void setUsefulscore(String usefulscore) {
		this.usefulscore = usefulscore;
	}
	public int getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(int totalscore) {
		this.totalscore = totalscore;
	}
}
