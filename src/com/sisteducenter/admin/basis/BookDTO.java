package com.sisteducenter.admin.basis;

/**
 * 교재 정보를 저장하는 클래스입니다.
 * 교재번호, 제목, 출판사를 저장합니다.
 * private String seq;	// 교재번호
 * private String title; // 제목
 * private String publisher; // 출판사
 * @author 권주홍
 */
public class BookDTO {
	
	private String seq; 		//번호
	private String title; 		//제목
	private String publisher; 	//출판사
	
	/**
	 * 교재번호를 출력하는 메소드입니다.
	 * @return 교재번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 교재번호를 저장하는 메소드입니다.
	 * @param 교재번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 제목을 출력하는 메소드입니다.
	 * @return 제목
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 제목을 저장하는 메소드입니다.
	 * @param 제목
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 출판사를 출력하는 메소드입니다.
	 * @return 출판사
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * 출판사를 저장하는 메소드입니다.
	 * @param 출판사
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	
	

}
