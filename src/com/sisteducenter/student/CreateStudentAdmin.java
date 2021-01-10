package com.sisteducenter.student;

import java.util.Scanner;

import com.sisteducenter.admin.StudentManagingMainAdmin;


/**
 * 
 * @author 이준오
 * @for 교육생 등록 메인 메뉴
 *
 */
public class CreateStudentAdmin {

	/**
	 * 교육생 등록 화면을 나타내는 메서드입니다.
	 */
	public static void addStudentIntro() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("           교육생 등록 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("1. 교육생 정보 등록하기");
		System.out.println();
		System.out.println("0. 이전 메뉴");
		System.out.println();
		System.out.println();
		System.out.print("번호 입력 : ");
		
		String sel = scan.nextLine();
		
		boolean flag = true;
		while (flag) {
			if (sel.equals("1")) {
				flag = false;
				CreateStudentInfoAdmin.intro();
				
			} else if (sel.equals("0")) {
				flag = false;
				StudentManagingMainAdmin.intro();
				
			} else {
				flag = true;
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println();
				System.out.println("1. 교육생 정보 등록하기");
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
