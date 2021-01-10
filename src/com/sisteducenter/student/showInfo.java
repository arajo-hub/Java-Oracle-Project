package com.sisteducenter.student;

import java.util.Scanner;

/**
 * 교육생 메뉴 '개인정보 조회 및 수정'을 선택했을 때 나타나는 화면을 담당하는 클래스입니다.
 * @author 조아라
 *
 */
public class showInfo {
	
	static Scanner scan=new Scanner(System.in);
	
	/**
	 * 개인정보 조회 및 수정을 선택했을 때의 화면을 나타내는 메서드입니다.
	 * @param seq
	 */
	public static void showPersonalInfo(String seq){
		
		boolean loop=true;
		boolean isFirstTime=true;
		
		while(loop) {
			
			// 헤더 출력
			showHeader();
			
			// 교육생 정보 출력
			studentDTO dto=new studentDTO();
			
			dto=studentDAO.getPersonalInfo(seq);
			
			System.out.println();
			System.out.println();
			System.out.printf("\t\t\t\t* 교육생번호 : %s\n", dto.getSeq());
			System.out.printf("\t\t\t\t* 교육생명 : %s\n", dto.getName());
			System.out.printf("\t\t\t\t* 주민번호 : %s\n", dto.getIdNum());
			System.out.printf("\t\t\t\t* 연락처 : %s\n", dto.getTel());
			System.out.printf("\t\t\t\t* 취업여부 : %s\n", dto.getEmployment().equals("Y")?"취업":"미취업");
			
			// 메인메뉴 출력
			showMenu(isFirstTime);
			isFirstTime=false;
			
			
			
			/*
			 * 메뉴 구성
			 * 
			 * 1. 뒤로가기
			 * 2. 개인정보수정
			 * 00. 종료
			 * */
			
			String sel=scan.nextLine();
			
			if (sel.equals("1")){ // 뒤로가기
				studentMain.showMain(seq);
				loop=false;
			
			}else if (sel.equals("2")){ // 개인정보 수정
				editInfo.editPersonalInfo(dto);
				loop=false;
					
			}else if (sel.equals("00")) { // 프로그램 종료
				
				System.out.println("\t\t\t\t\t프로그램을 종료합니다.");
				loop=false;
			}
			
		} // while
	}
	
	private static void showMenu(boolean isFirstTime) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t1. 뒤로가기");
		System.out.println("\t\t\t\t\t\t2. 개인정보수정");
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
	
	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t개인정보조회\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
