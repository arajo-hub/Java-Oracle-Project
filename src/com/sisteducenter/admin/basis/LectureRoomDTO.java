package com.sisteducenter.admin.basis;

/**
 * 강의실 정보를 저장하는 클래스입니다.
 * 강의실번호, 정원을 저장합니다.
 * 	private String seq; //번호
 *	private String capacity; //정원
 * @author 권주홍
 */
public class LectureRoomDTO {
	
	private String seq; //번호
	private String capacity; //정원
	
	/**
	 * 강의실 번호를 출력하는 메소드입니다.
	 * @return 강의실 번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 강의실 번호를 저장하는 메소드입니다.
	 * @param 강의실 번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/** 
	 * 정원을 출력하는 메소드입니다.
	 * @return 정원
	 */
	public String getCapacity() {
		return capacity;
	}
	
	/**
	 * 정원을 저장하는 메소드입니다.
	 * @param 정원
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	
}
