package com.sisteducenter.admin.opencourse;

/**
 * 과목기초정보를 다루기 위한 DTO 클래스입니다.
 * @author 장진영
 *
 */
public class CourseListDTO {

	private String seq;
	private String name;
	private String period;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	
}
