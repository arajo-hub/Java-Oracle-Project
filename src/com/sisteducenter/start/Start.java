package com.sisteducenter.start;

import java.util.Scanner;

/**
 * 쌍용교육센터 관리프로그램이 시작되는 클래스입니다.
 * @author 1조
 *
 */
public class Start {
	
	private static Scanner scan=new Scanner(System.in);

	/**
	 * 프로그램의 시작 메서드입니다.
	 */
	public static void main(String[] args) {
		
		// 프로그램 시작
		
		boolean loop=true;
		boolean isFirstTime=true;
		// 값에 따라 이 메뉴가 처음 출력되는지, 아님 이미 출력이 됐었는지 확인해준다.
		// 값이 true이면 평범한 메뉴번호 입력 메시지가 뜨지만(처음 메뉴를 선택하는 것이기 때문.),
		// 메뉴 외의 다른 값을 입력하게 되면 잘못된 메뉴 번호를 입력했다는 메시지를
		// 한 화면 전이 아니라, 새로 뜨는 화면에서 확인할 수 있게 해준다.
		
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
			 * 1. 교육생 로그인
			 * 2. 교사 로그인
			 * 3. 관리자 로그인
			 * 00. 종료
			 * */
			
			String sel=scan.nextLine();
			
			if (sel.equals("1") || sel.equals("2") || sel.equals("3")) {
				// 1. 교육생 로그인, 2. 교사 로그인, 3. 관리자 로그인
				// 로그인은 비슷한 코드가 많아 코드의 중복을 막기 위해 한꺼번에 처리.
				Login.login(sel);
				break;
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
		System.out.println("\t\t\t\t*\t\t관리프로그램\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void showMenu(boolean isFirstTime) {
		System.out.println("\t\t\t\t\t\t1. 교육생 로그인");
		System.out.println("\t\t\t\t\t\t2. 교사 로그인");
		System.out.println("\t\t\t\t\t\t3. 관리자 로그인");
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
