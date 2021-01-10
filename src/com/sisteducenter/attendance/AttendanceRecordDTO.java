package com.sisteducenter.attendance;

/**
 * 출결정보를 다루기 위한 DTO 클래스입니다.
 * String inTime : 입실시간
 * String outTime : 퇴실시간
 * String state : 출결상태
 * 출결상태는 1. 정상, 2. 지각, 3. 조퇴, 4. 외출, 5. 병가, 6. 기타 입니다.
 * @author 조아라
 *
 */
public class AttendanceRecordDTO {

	private String inTime; // 입실시간
	private String outTime; // 퇴실시간
	private String state; // 출결상태
	
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
