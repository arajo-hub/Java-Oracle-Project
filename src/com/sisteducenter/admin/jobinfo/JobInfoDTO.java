package com.sisteducenter.admin.jobinfo;

/**
 * 구인공고정보를 저장하는 클래스입니다.
 * 구인공고 번호, 직군, 근무형태, 연봉, 채용단계, 내용, 회사번호,
 * 회사명, 업종, 진행상태, 공고시작일, 공고마감일을 저장한다.
 * 	private String seq; //구인공고 번호
	private String jobDivision; // 직군
	private String jobType; //근무형태
	private String annualIncome; //연봉
	private String recruitStep; //채용단계
	private String detail; //내용
	private String companySeq; //회사번호
	
	private String company; //회사명
	private String industry; //업종
	private String state; //진행상태
	private String startDate; //공고시작일
	private String endDate; //공고마감일
 * @author 권주홍
 *
 */

public class JobInfoDTO {
	
	
	private String seq; //구인공고 번호
	private String jobDivision; // 직군
	private String jobType; //근무형태
	private String annualIncome; //연봉
	private String recruitStep; //채용단계
	private String detail; //내용
	private String companySeq; //회사번호
	
	private String company; //회사명
	private String industry; //업종
	private String state; //진행상태
	private String startDate; //공고시작일
	private String endDate; //공고마감일
	
	
	/**
	 * 구인공고 번호를 출력하는 메소드입니다.
	 * @return 구인공고 번호
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * 구인공고 번호를 저장하는 메소드입니다.
	 * @param 구인공고 번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * 직군을 출력하는 메소드입니다.
	 * @return 직군
	 */
	public String getJobDivision() {
		return jobDivision;
	}
	/**
	 * 직군 저장하는 메소드입니다.
	 * @param 직군
	 */
	public void setJobDivision(String jobDivision) {
		this.jobDivision = jobDivision;
	}
	/**
	 * 근무형태를 출력하는 메소드입니다.
	 * @return 근무형태
	 */
	public String getJobType() {
		return jobType;
	}
	/**
	 * 근무형태를 저장하는 메소드입니다.
	 * @param 근무형태
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	/**
	 * 연봉을 출력하는 메소드입니다.
	 * @return 연봉
	 */
	public String getAnnualIncome() {
		return annualIncome;
	}
	/**
	 * 연봉을 저장하는 메소드입니다.
	 * @param 연봉
	 */
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}
	/**
	 * 채용단계를 출력하는 메소드입니다.
	 * @return 채용단계
	 */ 
	public String getRecruitStep() {
		return recruitStep;
	}
	/**
	 * 채용단계를 저장하는 메소드입니다.
	 * @param 채용단계
	 */
	public void setRecruitStep(String recruitStep) {
		this.recruitStep = recruitStep;
	}
	/**
	 * 내용을 출력하는 메소드입니다.
	 * @return 내용
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * 내용을 저장하는 메소드입니다.
	 * @param 내용
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * 회사번호를 출력하는 메소드입니다.
	 * @return 회사번호
	 */
	public String getCompanySeq() {
		return companySeq;
	}
	/**
	 * 회사번호를 저장하는 메소드입니다.
	 * @param 회사번호
	 */
	public void setCompanySeq(String companySeq) {
		this.companySeq = companySeq;
	}
	/**
	 * 회사명을 출력하는 메소드입니다.
	 * @return 회사명
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * 회사명 저장하는 메소드입니다.
	 * @param 회사명
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 업종을 출력하는 메소드입니다.
	 * @return 업종
	 */
	public String getIndustry() {
		return industry;
	}
	/**
	 * 업종을 저장하는 메소드입니다.
	 * @param 업종
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
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
	 * 공고시작일을 출력하는 메소드입니다.
	 * @return 공고시작일
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * 공고시작일을 저장하는 메소드입니다.
	 * @param 공고시작일
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * 공고마감일을 출력하는 메소드입니다.
	 * @return 공고마감일
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * 공고마감일을 저장하는 메소드입니다.
	 * @param 공고마감일
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	

}
