package com.sisteducenter.admin.teacher;


/**
 * 교사정보를 저장하는 클래스입니다.
 * 교사 기초정보인 교사번호, 교사명, 주민번호 뒷자리, 전화번호를 저장합니다.
 * 상세정보로 담당과정, 과목, 과목시작일, 과목종료일, 진행상태, 강의실, 교재 정보를 저장합니다.
 * private String seq; //교사번호
 * private String name; //교사명
 * private String idNum; //주민번호뒷자리
 * private String tel; //전화번호
 * private String course; //담당과정
 * private String subject; //담당과목
 * private String startDate; //과목시작일
 * private String endDate; //과목종료일
 * private String state; //진행상태
 * private String lectureRoom; //강의실
 * private String book; //교재
 * @author 권주홍
 */
public class TeacherDTO {
	
	// 기초정보
	private String seq; //교사번호
	private String name; //교사명
	private String idNum; //주민번호뒷자리
	private String tel; //전화번호
	
	// 상세정보
	private String course; //담당과정
	private String subject; //담당과목
	private String startDate; //과목시작일
	private String endDate; //과목종료일
	private String state; //진행상태
	private String lectureRoom; //강의실
	private String book; //교재
	
	
	/**
	 * 교사번호를 출력하는 메소드입니다.
	 * @return 교사번호
	 */
	public String getSeq() {
		return seq;
	}
	/** 
	 * 교사번호를 저장하는 메소드입니다.
	 * @param 교사번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * 교사명을 출력하는 메소드입니다.
	 * @return 교사명
	 */
	public String getName() {
		return name;
	}
	/** 
	 * 교사명을 저장하는 메소드입니다.
	 * @param 교사명 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 주민번호 뒷자리를 출력하는 메소드입니다.
	 * @return 주민번호 뒷자리
	 */
	public String getIdNum() {
		return idNum;
	}
	/** 
	 * 주민번호 뒷자리를 저장하는 메소드입니다.
	 * @param 주민번호 뒷자리
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	/**
	 * 전화번호를 출력하는 메소드입니다.
	 * @return 전화번호
	 */
	public String getTel() {
		return tel;
	}
	/** 
	 * 전화번호를 저장하는 메소드입니다.
	 * @param 전화번호
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 담당과정을 출력하는 메소드입니다.
	 * @return 담당과정
	 */
	public String getCourse() {
		return course;
	}
	/** 
	 * 담당과정을 저장하는 메소드입니다.
	 * @param 담당과정
	 */
	public void setCourse(String course) {
		this.course = course;
	}
	/**
	 * 담당과목을 출력하는 메소드입니다.
	 * @return 담당과목
	 */
	public String getSubject() {
		return subject;
	}
	/** 
	 * 담당과목을 저장하는 메소드입니다.
	 * @param 담당과목
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * 과목시작일을 출력하는 메소드입니다.
	 * @return 과목시작일
	 */
	public String getStartDate() {
		return startDate;
	}
	/** 
	 * 과목시작일을 저장하는 메소드입니다.
	 * @param 과목시작일
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * 과목종료일을 출력하는 메소드입니다.
	 * @return 과목종료일
	 */
	public String getEndDate() {
		return endDate;
	}
	/** 
	 * 과목종료일을 저장하는 메소드입니다.
	 * @param 과목종료일
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * 진행상태를 출력하는 메소드입니다.
	 * @return 진행상태
	 */
	public String getState() {
		return state;
	}
	/** 
	 * 진행상태를 저장하는 메소드입니다.
	 * @param 진행상태
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 강의실를 출력하는 메소드입니다.
	 * @return 강의실
	 */
	public String getLectureRoom() {
		return lectureRoom;
	}
	/** 
	 * 강의실를 저장하는 메소드입니다.
	 * @param 강의실
	 */
	public void setLectureroom(String lectureRoom) {
		this.lectureRoom = lectureRoom;
	}
	/**
	 * 교재를 출력하는 메소드입니다.
	 * @return 교재
	 */
	public String getBook() {
		return book;
	}
	/** 
	 * 교재를 저장하는 메소드입니다.
	 * @param 교재
	 */
	public void setBook(String book) {
		this.book = book;
	}
	
	

}
