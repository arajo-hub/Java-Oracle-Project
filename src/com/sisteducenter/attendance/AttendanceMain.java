package com.sisteducenter.attendance;

import java.util.Scanner;

import com.sisteducenter.student.studentMain;

/**
 * 교육생 메인 메뉴에서 출결 조회 및 관리를 선택했을 때의 화면을 담당하는 클래스입니다.
 * @author 조아라
 *
 */
public class AttendanceMain {
	
	private static Scanner scan;
	
	static {
		scan=new Scanner(System.in);
	}

	/**
	 * 출결 조회 및 관리의 메뉴를 나타내는 화면입니다.
	 * @param seq 교육생번호입니다.
	 * 뒤로가기 선택시 이전화면으로 넘어가기 위해 교육생번호 변수가 필요합니다.
	 */
	public static void showMain(String seq) {
		
		boolean loop=true;
		boolean isFirstTime=true;
		
		while(loop) {
			
			// 헤더 출력
			showHeader();
			
			// 메인메뉴 출력
			showMenu(isFirstTime);
			isFirstTime=false;
			// 메뉴를 이미 한 번 보여줬으므로 값을 false로 바꾼다.
			// 이후엔 메뉴번호를 제대로 입력하지 않으면 잘못된 메뉴 번호를 입력했다는 메시지가
			// 새 화면에 함께 뜬다.
			
			/*
			 * 메뉴 구성
			 * 
			 * 1. 출결 현황 조회
			 * 2. 출결 입력
			 * 00. 종료
			 * */
			
			String sel=scan.nextLine();
			
			if (sel.equals("1")) { // 출결 현황 조회
				AttendanceSubMain.showSubMain(seq);
				loop=false;
			}else if (sel.equals("2")) { // 출결 입력
				InsertAttendanceRecord.insertRecord(seq);
				loop=false;
			}else if (sel.equals("3")) { // 뒤로가기
				studentMain.showMain(seq);
			}else if (sel.equals("00")) { // 프로그램 종료 선택
				System.out.println("\t\t\t\t\t프로그램을 종료합니다.");
				loop=false;
			}
			
		} // while
	}
	
	/**
	 * 상단에 표시되는 헤더입니다.
	 */
	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t출결조회관리\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	/**
	 * 교육생의 출결 조회 및 입력 메뉴를 출력하는 메서드입니다.
	 * 
	 * @param isFirstTime : 이 메뉴가 처음 출력되는지(true), 아닌지를 나타내는 변수입니다.
	 * isFirstTime가 true이면 정상적인 메시지가 출력되고,
	 * false이면 메뉴를 선택했는데, 잘못된 메뉴를 선택된 경우이므로 잘못된 메뉴번호라는 메시지를 출력합니다.
	 */
	private static void showMenu(boolean isFirstTime) {
		System.out.println("\t\t\t\t\t\t1. 출결 현황 조회");
		System.out.println("\t\t\t\t\t\t2. 출결 입력");
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
