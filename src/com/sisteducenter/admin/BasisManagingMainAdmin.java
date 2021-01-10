package com.sisteducenter.admin;

import java.util.Scanner;

import com.sisteducenter.admin.basis.Book;
import com.sisteducenter.admin.basis.Course;
import com.sisteducenter.admin.basis.LectureRoom;
import com.sisteducenter.admin.basis.Subject;

/**
 * 관리자 메뉴 중 기초정보관리를 선택했을 때의 화면을 담당하는 클래스입니다.
 * @author 장진영
 *
 */
public class BasisManagingMainAdmin {

	private static Scanner scan;
	
	static {
		scan = new Scanner(System.in);
	}
	
	/**
	 * 관리자 메뉴 중 기초정보관리를 선택했을 때의 화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		
		
		boolean loop = true;
		
		System.out.println("기초정보관리");
		while(loop) {
			
			System.out.println("1. 과정 관리");
			System.out.println("2. 과목 관리");
			System.out.println("3. 강의실 관리");
			System.out.println("4. 교재 관리");
			System.out.print("입력 : ");
			String sel = scan.nextLine();
			
			switch(sel) {
				case "1":
					Course.intro();
					break;
				case "2":
					Subject.intro();
					break;
				case "3":
					LectureRoom.intro();
					break;
				case "4":
					Book.intro();
					break;
				default:
					break;
				
			}
		}
	}
}
