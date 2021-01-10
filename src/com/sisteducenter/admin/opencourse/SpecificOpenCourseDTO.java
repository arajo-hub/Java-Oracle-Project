package com.sisteducenter.admin.opencourse;

//특정 개설 과정 정보 - 1
/**
 * 특정 개설 과정 조회를 위한 DTO 입니다.
 * 개설 과목 정보(과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명) 
 * @author 장진영
 */
public class SpecificOpenCourseDTO {

	private String seq;
	private String subjectname;	//과목명
	private String period;
	private String startdate;	//시작
	private String enddate;		//끝
	private String title;		//교재명
	private String tName;		//교사명
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSubjectname() {
		return subjectname;
	}

	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}

}
