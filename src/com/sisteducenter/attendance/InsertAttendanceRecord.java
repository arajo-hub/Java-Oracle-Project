package com.sisteducenter.attendance;

import java.util.Scanner;

import com.sisteducenter.student.studentMain;

/**
 * 출결정보를 입력하는 클래스입니다.
 * @author 조아라
 *
 */
public class InsertAttendanceRecord {
	
	private static Scanner scan=new Scanner(System.in);

	/**
	 * 출결정보 입력을 선택한 후의 화면을 나타내는 메서드입니다.
	 * @param seq
	 */
	public static void insertRecord(String seq) {
		// 출결 데이터 입력
		
		boolean loop=true;
		boolean isFirstTime=true;
		
		while(loop) {
			
			// 헤더 출력
			showHeader();
			
			
			/*
			 * 출결입력
			 * 
			 * 1. 입실체크
			 * 2. 퇴실체크
			 * 3. 뒤로가기
			 * 00. 종료
			 * 
			 * */
			
			
			showMenu(isFirstTime);
			
			String sel=scan.nextLine();
			
			if (sel.equals("1")) { // 입실체크
				insertInTime(seq);
			}else if (sel.equals("2")) { // 퇴실체크
				insertStateAndOutTime(seq);
			}else if (sel.equals("3")) { // 뒤로가기
				studentMain.showMain(seq);
				loop=false;
			}else if (sel.equals("00")) { // 종료
				loop=false;
			}
			
			
		} // while
		
	}
	
	private static void insertInTime(String seq) {
		
		int result=AttendanceDAO.insertInRecord(seq);
		
		if (result==0) {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t입실체크 완료했습니다.");
			System.out.println("\t\t\t\t퇴실체크까지 정상적으로 이루어져야");
			System.out.println("\t\t\t\t정상출결로 인정됩니다.");
		}else if (result==1) {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t이미 입실체크되어 있습니다.");
		}else if (result==2) {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t입실체크 가능한 시간이 아닙니다.");
			System.out.println("\t\t\t\t입실체크 가능한 시간 : 오전 8시 30분 ~ 17시 59분");
		}else {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t입실체크에 실패했습니다.");
			System.out.println("\t\t\t\t관리자에게 문의해주세요.");
		}
	}
	
	private static void insertStateAndOutTime(String seq) {
		
		boolean loop=true;
		while(loop) {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t\t* 퇴실상태를 골라주세요.");
			System.out.println();
			System.out.println("\t\t\t\t\t\t1. 정상");
			System.out.println("\t\t\t\t\t\t2. 지각");
			System.out.println("\t\t\t\t\t\t3. 조퇴");
			System.out.println("\t\t\t\t\t\t4. 외출");
			System.out.println("\t\t\t\t\t\t5. 병가");
			System.out.println("\t\t\t\t\t\t6. 기타");
			System.out.print("\t\t\t\t▶︎ ");
		
			String sel=scan.nextLine();
			
			if (sel.equals("2")) { // 지각이면서 정상적으로 퇴실체크한 경우는 상태를 바꾸지 않고 그대로 퇴실체크.
				insertOutTime(seq, null);
				loop=false;
			}else if (sel.equals("1")
					|| sel.equals("3")
					|| sel.equals("4")
					|| sel.equals("5")
					|| sel.equals("6")) {
				insertOutTime(seq, sel);
				loop=false;
			}
		}
			
	}
	
	private static void insertOutTime(String seq, String state) {
		
		System.out.println();
		int result=AttendanceDAO.insertOutRecord(seq, state);
		
		if (result==0) {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t퇴실체크 완료했습니다.");
			System.out.println("\t\t\t\t정상출결 처리됐습니다.");
		}else if (result==1) {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t이미 퇴실체크되어 있습니다.");
		}else if (result==2) {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t퇴실체크 가능한 시간이 아닙니다.");
			System.out.println("\t\t\t\t퇴실체크 가능한 시간 : 오전 9시 ~ 오후 6시 30분");
		}else if (result==3) {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t상태를 잘못 입력했습니다.");
			System.out.println("\t\t\t\t정확한 상태를 입력해주세요.");
		}else {
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t퇴실체크에 실패했습니다.");
			System.out.println("\t\t\t\t관리자에게 문의해주세요.");
		}
	}
	
	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t출 결 입 력\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void showMenu(boolean isFirstTime) {
		System.out.println("\t\t\t\t\t\t1. 입실체크");
		System.out.println("\t\t\t\t\t\t2. 퇴실체크");
		System.out.println("\t\t\t\t\t\t3. 뒤로가기");
		System.out.println("\t\t\t\t\t\t00. 종료");
		System.out.println();
		System.out.println();
		if (isFirstTime) {
			System.out.println("\t\t\t\t\t* 원하는 메뉴번호를 입력해주세요.");
			System.out.print("\t\t\t\t\t▶︎ ");
		}else {
			System.out.println("\t\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t\t잘못된 메뉴번호입니다.");
			System.out.println("\t\t\t\t\t다시 입력해주세요.");
			System.out.print("\t\t\t\t\t▶︎ ");
		}
	}

}
