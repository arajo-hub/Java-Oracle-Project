package com.sisteducenter.teacher;

/**
 * 종료된 과목의 배점정보를 담을 DTO 클래스입니다.
 * @author 황원준
 *
 */
public class EndSubListForAllotDTO {
	
	private String oc_seq; //개설 과정번호
	private String s_subjectname; //과목명
	private String os_startdate;//과목 시작일
	private String os_enddate;//과목 종료일
	private String sub_state;//과목상태
	private String c_name;//과정명
	private String oc_startdate;//과정시작일
	private String oc_enddate;//과정종료일
	private String stu_count;//교육생등록인원
	private String course_state;//과정상태
	
	public String getOc_seq() {
		return oc_seq;
	}
	public void setOc_seq(String oc_seq) {
		this.oc_seq = oc_seq;
	}
	public String getS_subjectname() {
		return s_subjectname;
	}
	public void setS_subjectname(String s_subjectname) {
		this.s_subjectname = s_subjectname;
	}
	public String getOs_startdate() {
		return os_startdate;
	}
	public void setOs_startdate(String os_startdate) {
		this.os_startdate = os_startdate;
	}
	public String getOs_enddate() {
		return os_enddate;
	}
	public void setOs_enddate(String os_enddate) {
		this.os_enddate = os_enddate;
	}
	public String getSub_state() {
		return sub_state;
	}
	public void setSub_state(String sub_state) {
		this.sub_state = sub_state;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getOc_startdate() {
		return oc_startdate;
	}
	public void setOc_startdate(String oc_startdate) {
		this.oc_startdate = oc_startdate;
	}
	public String getOc_enddate() {
		return oc_enddate;
	}
	public void setOc_enddate(String oc_enddate) {
		this.oc_enddate = oc_enddate;
	}
	public String getStu_count() {
		return stu_count;
	}
	public void setStu_count(String stu_count) {
		this.stu_count = stu_count;
	}
	public String getCourse_state() {
		return course_state;
	}
	public void setCourse_state(String course_state) {
		this.course_state = course_state;
	}



}
