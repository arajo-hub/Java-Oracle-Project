package com.sisteducenter.jobinfo;

/**
 * 구직정보를 다루기 위한 DTO입니다.
 * String company : 회사명
 * String industryName : 업종명
 * String sales : 매출액
 * String jobDivision : 모집직군
 * String jobType : 근무형태
 * String annualIncome : 연봉
 * String recruitStep : 채용단계
 * String detail : 공고내용
 * String startDate : 공고시작일
 * String endDate : 공고종료일
 * @author 조아라
 *
 */
public class JobInfoListDTO {

	private String company;
	private String industryName;
	private String sales;
	private String jobDivision;
	private String jobType;
	private int annualIncome;
	private String recruitStep;
	private String detail;
	private String startDate;
	private String endDate;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getJobDivision() {
		return jobDivision;
	}
	public void setJobDivision(String jobDivision) {
		this.jobDivision = jobDivision;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public int getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getRecruitStep() {
		return recruitStep;
	}
	public void setRecruitStep(String recruitStep) {
		this.recruitStep = recruitStep;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
