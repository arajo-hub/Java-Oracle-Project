package com.sisteducenter.attendance;

import java.util.Scanner;

import com.sisteducenter.admin.StudentManagingMainAdmin;

/**
 * 관리자의 교육생 관리 메인 메뉴에서 5. 출결 관리 및 조회를 선택했을 때의 화면을 담당하는 클래스입니다.
 * @author 이준오
 * @for 출결 관리 및 출결 조회 메인메뉴
 *
 */
public class AttendanceAdmin {

	/**
	 * 관리자의 교육생 관리 메인 메뉴에서 5. 출결 관리 및 조회를 선택했을 때의 화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("         출결 관리 및 조회 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("1. 개설 과정 내 출결 조회");
		System.out.println();
		System.out.println("2. 교육생 별 출결 조회");
		System.out.println();
		System.out.println("3. 출결 현황 날짜 별 조회");
		System.out.println();
		System.out.println("0. 이전 메뉴");
		System.out.println();
		System.out.println();
		System.out.print("번호 입력 : ");
		Scanner scan = new Scanner(System.in);
		String sel = scan.nextLine();
		
		boolean flag = true;
		
		while (flag) {

			if (sel.equals("1")) {
				flag = false;
				AttendanceSubjectAdmin.intro();
				
			} else if (sel.equals("2")) {
				flag = false;
				AttendanceStudentAdmin.intro();
				
			} else if (sel.equals("3")) {
				flag = false;
				AttendanceDateAdmin.intro();
				
			} else if (sel.equals("0")) {
				flag = false;
				StudentManagingMainAdmin.intro();
				
			} else {
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println("1. 개설 과정 내 출결 조회");
				System.out.println();
				System.out.println("2. 교육생 별 출결 조회");
				System.out.println();
				System.out.println("3. 출결 현황 날짜 별 조회");
				System.out.println();
				System.out.println("0. 이전 메뉴");
				System.out.println();
				System.out.println();
				System.out.print("번호 입력 : ");
				sel = scan.nextLine();
			}
			
			
		}
	}

	
	

}
