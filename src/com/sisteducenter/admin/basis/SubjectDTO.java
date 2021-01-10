package com.sisteducenter.admin.basis;

/**
 * 과목정보를 저장하는 클래스입니다.
 * 과목번호, 과목명, 구분, 기간, 교재번호, 교재제목을 저장합니다.
 * private String seq; // 과목번호
 * private String name; // 과목명
 * private String division; //과목구분
 * private String period; //기간
 * private String bookSeq; //교재번호
 * private String bookTitle; //교재제목
 * @author 권주홍
 */
public class SubjectDTO {
	
	private String seq; // 과목번호
	private String name; // 과목명
	private String division; //과목구분
	private String period; //기간
	private String bookSeq; //교재번호
	private String bookTitle; //교재제목
	
	/**
	 * 과목번호를 출력하는 메소드입니다.
	 * @return 과목번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 과목번호를 저장하는 메소드입니다.
	 * @param 과목번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 과목명을 출력하는 메소드입니다.
	 * @return 과목명
	 */
	public String getName() {
		return name;
	}
	/**
	 * 과목명을 저장하는 메소드입니다.
	 * @param 과목명
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 과목구분을 출력하는 메소드입니다.
	 * @return 과목구분
	 */
	public String getDivision() {
		return division;
	}
	
	/**
	 * 과목구분을 저장하는 메소드입니다.
	 * @param 과목구분
	 */
	public void setDivision(String division) {
		this.division = division;
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
	 * @param 기간
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	
	/**
	 * 교재번호를 출력하는 메소드입니다.
	 * @return 교재번호
	 */
	public String getBookSeq() {
		return bookSeq;
	}
	
	/**
	 * 교재번호를 저장하는 메소드입니다.
	 * @param 교재번호
	 */
	public void setBookSeq(String bookSeq) {
		this.bookSeq = bookSeq;
	}
	
	/**
	 * 교재제목을 출력하는 메소드입니다.
	 * @return 교재제목
	 */
	public String getBookTitle() {
		return bookTitle;
	}
	
	/**
	 * 교재제목을 저장하는 메소드입니다.
	 * @param 교재제목
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	
	
}
