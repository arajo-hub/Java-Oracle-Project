package com.sisteducenter.admin.basis;

/**
 * 과정 정보를 저장하는 클래스입니다.
 * 과정 번호, 과정명, 기간을 저장합니다.
 * 	private String seq; //번호
 *	private String name; //과정명
 *	private String period; //기간
 * @author Juhong
 */
public class CourseDTO {
	
	private String seq; //번호
	private String name; //과정명
	private String period; //기간
	
	/**
	 * 과정번호를 출력하는 메소드입니다.
	 * @return 과정번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 과정번호를 저장하는 메소드입니다.
	 * @param 과정번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 과정명을 출력하는 메소드입니다.
	 * @return 과정명
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 과정명을 저장하는 메소드입니다.
	 * @param 과정명
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 기간을 출력하는 메소드입니다.
	 * @return 기간
	 */
	public String getPeriod() {
		return period;
	}
	
	/** 
	 * 기간을 저장하는 메소드입니다.
	 * @param period
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	
	

}
