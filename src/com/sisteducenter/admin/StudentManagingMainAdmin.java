package com.sisteducenter.admin;

import java.util.Scanner;

import com.sisteducenter.attendance.AttendanceAdmin;
import com.sisteducenter.student.CreateStudentAdmin;
import com.sisteducenter.student.DeleteStudentAdmin;
import com.sisteducenter.student.SearchStudentAdmin;
import com.sisteducenter.student.UpdateStudentAdmin;


/**
 * 관리자 메인 메뉴에서 3. 교육생 관리를 선택했을 때의 화면을 담당하는 클래스입니다.
 * @author 이준오
 * @for 교육생 관리 메인메뉴
 *
 */
public class StudentManagingMainAdmin {

	/**
	 * 교육생 관리 메인메뉴를 나타내는 메서드입니다.
	 */
	public static void intro() {
		
		Scanner scan = new Scanner(System.in);
				
		System.out.println("====================================");
		System.out.println("           교육생 관리");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("1. 교육생 등록");
		System.out.println();
		System.out.println("2. 교육생 정보 수정");
		System.out.println();
		System.out.println("3. 교육생 정보 보기");
		System.out.println();
		System.out.println("4. 교육생 정보 삭제하기");
		System.out.println();
		System.out.println("5. 출결 관리 및 조회");
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
				CreateStudentAdmin.addStudentIntro();
				
			} else if (sel.equals("2")) {
				flag = false;
				UpdateStudentAdmin.intro();
				
			} else if (sel.equals("3")) {
				flag = false;
				SearchStudentAdmin.intro();
				
			} else if (sel.equals("4")) {
				flag = false;
				DeleteStudentAdmin.intro();
				
			} else if (sel.equals("5")) {
				flag = false;
				AttendanceAdmin.intro();
				
			} else if (sel.equals("0")) {
				flag = false;
				AdminMain.showMain();
				
			} else {
				flag = true;
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println("1. 교육생 등록");
				System.out.println();
				System.out.println("2. 교육생 정보 수정");
				System.out.println();
				System.out.println("3. 교육생 정보 보기");
				System.out.println();
				System.out.println("4. 교육생 정보 삭제하기");
				System.out.println();
				System.out.println("5. 출결 관리 및 조회");
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
