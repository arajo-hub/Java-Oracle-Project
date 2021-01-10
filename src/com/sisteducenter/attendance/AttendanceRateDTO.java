package com.sisteducenter.attendance;

/**
 * 출석률 조회시 필요한 데이터를 담당하는 DTO 클래스입니다.
 * @author ara
 *
 */
public class AttendanceRateDTO {

	private double rate; // 출석률
	private int result; // 출석률조회프로시저 호출 결과. 0이면 조회 성공. 1이면 수강한 과정 없음.
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
