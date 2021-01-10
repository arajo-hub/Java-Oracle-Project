package com.sisteducenter.admin.teacher;


/**
 * 강의가능과목 정보를 저장하는 클래스입니다.
 * 교사번호, 교사명, 과목번호, 과목명을 저장합니다.
 * private String teacherSeq; //교사번호
 * private String subjectSeq; //과목번호
 * private String teacherName; //교사명
 * private String subjectName; //과목명
 * @author 권주홍
 */
public class PossibleSubjectDTO {
	
	private String teacherSeq; //교사번호
	private String subjectSeq; //과목번호
	
	private String teacherName; //교사명
	private String subjectName; //과목명
	
	/**
	 * 교사번호를 출력하는 메소드입니다.
	 * @return 교사번호
	 */
	public String getTeacherSeq() {
		return teacherSeq;
	}
	/**
	 * 교사번호를 저장하는 메소드입니다.
	 * @param 교사번호
	 */
	public void setTeacherSeq(String teacherSeq) {
		this.teacherSeq = teacherSeq;
	}
	/**
	 * 과목번호를 출력하는 메소드입니다.
	 * @return 과목번호
	 */
	public String getSubjectSeq() {
		return subjectSeq;
	}
	/**
	 * 과목번호를 저장하는 메소드입니다.
	 * @param 과목번호
	 */
	public void setSubjectSeq(String subjectSeq) {
		this.subjectSeq = subjectSeq;
	}
	/**
	 * 교사명을 출력하는 메소드입니다.
	 * @return 교사명
	 */
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * 교사명을 저장하는 메소드입니다.
	 * @param 교사명
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	 * 과목명을 출력하는 메소드입니다.
	 * @return 과목명
	 */
	public String getSubjectName() {
		return subjectName;
	}	
	/**
	 * 과목명을 저장하는 메소드입니다.
	 * @param 과목명
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	

}
