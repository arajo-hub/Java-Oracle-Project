package com.sisteducenter.teacher;

/**
 * 종료된 과목의 정보를 담을 DTO 클래스입니다.
 * @author 황원준
 *
 */
public class EndSubListDTO {
	
	private String os_seq;
	private String s_subjectname;
	private String os_startdate;
	private String os_enddate;
	private String sub_state;
	private String oc_seq; // 여기에 추가.
	private String c_name;
	private String oc_startdate;
	private String oc_enddate;
	private String stu_count;
	private String course_state;
	private String t_name;
	private String avg_score;
	
	public String getOs_seq() {
		return os_seq;
	}
	public void setOs_seq(String os_seq) {
		this.os_seq = os_seq;
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
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getAvg_score() {
		return avg_score;
	}
	public void setAvg_score(String avg_score) {
		this.avg_score = avg_score;
	}
	public String getOc_seq() {
		return oc_seq;
	}
	public void setOc_seq(String oc_seq) {
		this.oc_seq = oc_seq;
	}
	
	

}
