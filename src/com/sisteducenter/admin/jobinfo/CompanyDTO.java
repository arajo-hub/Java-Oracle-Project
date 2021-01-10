package com.sisteducenter.admin.jobinfo;

/**
 * 회사정보를 저장하는 클래스입니다.
 * 회사번호, 회사명, 대표명, 설립일, 사원수, 전화번호, 주소, 매출, 업종번호, 업종명을 저장합니다.
 *  private String seq; //회사번호
	private String name; //회사명
	private String ceo; //대표
	private String establishDate; //설립일
	private String employeeCount; //사원수
	private String tel; //전화번호
	private String address; //주소
	private String sales; //매출
	private String industrySeq; //업종번호
	private String industryName; //업종명
 * @author 권주홍
 */

public class CompanyDTO {
	
	private String seq; //회사번호
	private String name; //회사명
	private String ceo; //대표명
	private String establishDate; //설립일
	private String employeeCount; //사원수
	private String tel; //전화번호
	private String address; //주소
	private String sales; //매출
	private String industrySeq; //업종번호
	
	private String industryName; //업종명
	
	
	/**
	 * 회사번호를 출력하는 메소드입니다.
	 * @return 회사번호
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * 회사번호를 저장하는 메소드입니다.
	 * @param 회사번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * 회사명을 출력하는 메소드입니다.
	 * @return 회사명
	 */
	public String getName() {
		return name;
	}
	/**
	 * 회사명을 저장하는 메소드입니다.
	 * @param 회사명
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 대표명을 출력하는 메소드입니다.
	 * @return 대표명
	 */
	public String getCeo() {
		return ceo;
	}
	/**
	 * 대표명을 저장하는 메소드입니다.
	 * @param 대표명
	 */
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	/**
	 * 설립일을 출력하는 메소드입니다.
	 * @return 설립일
	 */
	public String getEstablishDate() {
		return establishDate;
	}
	/**
	 * 설립일을 저장하는 메소드입니다.
	 * @param 설립일
	 */
	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}
	/**
	 * 사원수를 출력하는 메소드입니다.
	 * @return 사원수
	 */
	public String getEmployeeCount() {
		return employeeCount;
	}
	/**
	 * 사원수를 저장하는 메소드입니다.
	 * @param 사원수
	 */
	public void setEmployeeCount(String employeeCount) {
		this.employeeCount = employeeCount;
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
	 * 주소를 출력하는 메소드입니다.
	 * @return 주소
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 주소를 저장하는 메소드입니다.
	 * @param 주소
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 매출을 출력하는 메소드입니다.
	 * @return 매출
	 */
	public String getSales() {
		return sales;
	}
	/**
	 * 매출을 저장하는 메소드입니다.
	 * @param 매출
	 */
	public void setSales(String sales) {
		this.sales = sales;
	}
	/**
	 * 업종번호를 출력하는 메소드입니다.
	 * @return 업종번호
	 */
	public String getIndustrySeq() {
		return industrySeq;
	}
	/**
	 * 업종번호를 저장하는 메소드입니다.
	 * @param 업종번호
	 */
	public void setIndustrySeq(String industrySeq) {
		this.industrySeq = industrySeq;
	}
	/**
	 * 업종명을 출력하는 메소드입니다.
	 * @return 업종명
	 */
	public String getIndustryName() {
		return industryName;
	}
	/**
	 * 업종명을 저장하는 메소드입니다.
	 * @param 업종명
	 */
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	
	
	

}
