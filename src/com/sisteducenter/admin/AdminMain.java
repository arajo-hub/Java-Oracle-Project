package com.sisteducenter.admin;

import java.util.Scanner;

import com.sisteducenter.admin.jobinfo.JobInfo;
import com.sisteducenter.admin.lectureevaluation.LectureEvaluationAdmin;
import com.sisteducenter.admin.opencourse.OpenCourse;
import com.sisteducenter.admin.opensubject.OpenSubject;
import com.sisteducenter.admin.teacher.Teacher;

/**
 * 관리자의 메인메뉴 화면을 담당하는 클래스입니다.
 *
 * 메뉴 구성
 * 
 * 1. 기초정보 관리
 * 2. 교사 관리
 * 3. 교육생 관리
 * 4. 개설 과목 관리
 * 5. 개설 과정 관리
 * 6. 시험 및 성적 관리
 * 7. 구인 정보 관리
 * 8. 강의 평가 관리
 * 00. 종료
 * 
 * @author 조아라
 *
 */
public class AdminMain {
	
	static Scanner scan=new Scanner(System.in);

	public static void showMain() {
		
		// 프로그램 시작
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
			 * 1. 기초정보 관리
			 * 2. 교사 관리
			 * 3. 교육생 관리
			 * 4. 개설 과목 관리
			 * 5. 개설 과정 관리
			 * 6. 시험 및 성적 관리
			 * 7. 구인 정보 관리
			 * 8. 강의 평가 관리
			 * 00. 종료
			 * */
			
			String sel=scan.nextLine();
			
			if (sel.equals("1")){ // 기초정보 관리
				BasisManagingMainAdmin.intro();
				loop=false;
			}else if(sel.equals("2")) { // 교사 관리
				Teacher.intro();
				loop=false;
			}else if(sel.equals("3")) { // 교육생 관리
				StudentManagingMainAdmin.intro();
				loop=false;
			}else if(sel.equals("4")) { // 개설 과목 관리
				OpenSubject.showMain();
				loop=false;
			}else if(sel.equals("5")) { // 개설 과정 관리
				OpenCourse.openCourseMain();
				loop=false;
			}else if(sel.equals("6")) { // 시험 및 성적 관리
				TestScoreManagingMainAdmin.intro();
				loop=false;
			}else if(sel.equals("7")) { // 구인 정보 관리
				JobInfo.intro();
				loop=false;
			}else if(sel.equals("8")) { // 강의 평가 관리
				LectureEvaluationAdmin.showMain();
				loop=false;
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
		System.out.println("\t\t\t\t*\t\t관 리 자 메 인\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	/**
	 * 관리자 메뉴를 출력하는 메서드입니다.
	 * 
	 * @param isFirstTime : 이 메뉴가 처음 출력되는지(true), 아닌지를 나타내는 변수입니다.
	 * isFirstTime가 true이면 정상적인 메시지가 출력되고,
	 * false이면 메뉴를 선택했는데, 잘못된 메뉴를 선택된 경우이므로 잘못된 메뉴번호라는 메시지를 출력합니다.
	 */
	private static void showMenu(boolean isFirstTime) {
		System.out.println("\t\t\t\t\t\t1. 기초정보 관리");
		System.out.println("\t\t\t\t\t\t2. 교사 관리");
		System.out.println("\t\t\t\t\t\t3. 교육생 관리");
		System.out.println("\t\t\t\t\t\t4. 개설 과목 관리");
		System.out.println("\t\t\t\t\t\t5. 개설 과정 관리");
		System.out.println("\t\t\t\t\t\t6. 시험 및 성적 관리");
		System.out.println("\t\t\t\t\t\t7. 구인 정보 관리");
		System.out.println("\t\t\t\t\t\t8. 강의 평가 관리");
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
