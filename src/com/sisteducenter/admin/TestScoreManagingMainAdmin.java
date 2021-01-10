package com.sisteducenter.admin;

import java.util.Scanner;

import com.sisteducenter.admin.grade.TestScoreInfoAdmin;
import com.sisteducenter.admin.test.TestSubjectInfoAdmin;

import java.util.Scanner;


/**
 * 관리자 메뉴에서 6. 시험 및 성적 관리를 선택했을 때의 화면을 담당하는 클래스입니다.
 * @author 이준오
 * @for  시험 및 성적 관리 메인 메뉴
 *
 */
public class TestScoreManagingMainAdmin {

	/**
	 * 시험 및 성적 관리 메뉴를 나타내는 메서드입니다.
	 */
	public static void intro() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("          시험 및 성적 관리");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("1. 과목 시험 정보 조회");
		System.out.println();
		System.out.println("2. 교육생 성적 정보 조회");
		System.out.println();
		System.out.println("0. 이전 메뉴");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("번호 입력 : ");
		String sel = scan.nextLine();
		
		boolean flag = true;
		while (flag) {
			if (sel.equals("1")) {
				flag = false;
				TestSubjectInfoAdmin.intro();
				
			} else if (sel.equals("2")) {
				flag = false;
				TestScoreInfoAdmin.intro();
				
			} else if (sel.equals("0")) {
				flag = false;
				AdminMain.showMain();
				
			} else {
				flag = true;
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println("1. 교육생 관리");
				System.out.println();
				System.out.println("2. 시험 및 성적 관리");
				System.out.println();
				System.out.println("0. 이전 메뉴");
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.print("번호 입력 : ");
				sel = scan.nextLine();
			}
		
		}
		
	}


}
