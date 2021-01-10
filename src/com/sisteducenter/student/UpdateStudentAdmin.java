package com.sisteducenter.student;

import java.util.Scanner;

import com.sisteducenter.admin.StudentManagingMainAdmin;
import com.sisteducenter.registration.UpdateRegistrationInfoAdmin;

/**
 * 
 * @author 이준오
 * @for 교육생 정보 수정 메인메뉴
 *
 */
public class UpdateStudentAdmin {

	/**
	 * 교육생 정보 수정 메뉴 화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("         교육생 정보 수정 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("1. 교육생 기초정보 수정");
		System.out.println();
		System.out.println("2. 교육생 과정 수강, 수료 및 중도 탈락 처리");
		System.out.println();
		System.out.println("0. 이전메뉴");
		System.out.println();
		System.out.println();
		System.out.print("번호 입력 : ");
		String sel = scan.nextLine();
		
		boolean flag = true;
		
		while (flag) {
			if (sel.equals("1")) {
				flag = false;
				UpdateBasicInfoAdmin.intro();
				
			} else if (sel.equals("2")) {
				flag = false;
				UpdateRegistrationInfoAdmin.intro();
				
			} else if (sel.equals("0")) {
				flag = false;
				StudentManagingMainAdmin.intro();
				
			} else {
				flag = true;
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println();
				System.out.println("1. 교육생 기초정보 수정");
				System.out.println();
				System.out.println("2. 교육생 과정 수강, 수료 및 중도 탈락 처리");
				System.out.println();
				System.out.println("0. 이전메뉴");
				System.out.println();
				System.out.println();
				System.out.print("번호 입력 : ");
				sel = scan.nextLine();
			
			}
		}
	}
	

}
