package com.sisteducenter.admin.opencourse;

//개설 과정 등록
/**
 * 시작날짜 ~ 종료날짜사이에 사용하지 않는 강의실 조회를 위한 DTO 입니다.
 * @author 장진영
 */
public class EmptyLectureRoomDTO {

	private String room;
	private String capacity;
	
	public String getRoom() {
		return room;
	}
	public void setroom(String room) {
		this.room = room;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
}
