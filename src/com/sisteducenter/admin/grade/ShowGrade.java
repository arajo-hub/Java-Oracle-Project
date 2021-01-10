package com.sisteducenter.admin.grade;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.student.studentMain;

/**
 * 성적정보를 출력하기 위한 클래스입니다.
 * @author 조아라
 *
 */
public class ShowGrade {
	
	static Scanner scan=new Scanner(System.in);

	/**
	 * 성적정보를 리스트형식으로 보여주는 메서드입니다.
	 * @param seq
	 */
	public static void showGradeList(String seq) {
		
		
		boolean loop=true;
		boolean isFirstTime=true;
		
		while(loop) {
			
			// 헤더 출력
			showHeader();
			
			showAllGrade(seq);
			// 메인메뉴 출력
			showMenu(isFirstTime);
			isFirstTime=false;
			// 메뉴를 이미 한 번 보여줬으므로 값을 false로 바꾼다.
			// 이후엔 메뉴번호를 제대로 입력하지 않으면 잘못된 메뉴 번호를 입력했다는 메시지가
			// 새 화면에 함께 뜬다.
			
			/*
			 * 메뉴 구성
			 * 
			 * 1. 뒤로가기
			 * 00. 종료
			 * */
			
			String sel=scan.nextLine();
			
			if (sel.equals("1")){ // 뒤로가기
				studentMain.showMain(seq);
				loop=false;
				
			}else if (sel.equals("00")) { // 프로그램 종료
				
				System.out.println("\t\t\t\t\t프로그램을 종료합니다.");
				loop=false;
			}
			
		} // while
		
	}
	
	private static void showAllGrade(String seq) {
		
		ArrayList<GradeDTO> list=new ArrayList<GradeDTO>();
		
		list=GradeDAO.getGrade(seq);
		
		// System.out.println("[과목번호]\t[과목명]\t[과정시작일]\t[과정종료일]\t[교재명]\t\t\t\t\t\t[교사명]\t[필기배점]\t[실기배점]\t[출결배점]\t[필기점수]\t[실기점수]\t[출결점수]\t[시험일]");
		for (GradeDTO dto:list) {
			
			System.out.println("\t\t------------------------------------------------------------------------------------");
			System.out.printf("\t\t\t[과목번호] %s\n", dto.getCourseSeq());
			System.out.println();
			System.out.printf("\t\t\t[과목명] %s\t[교사명] %s\t[교재명] %s\n", dto.getSubjectName(), dto.getTeacherName(), dto.getBook());
			System.out.printf("\t\t\t[과정시작일] %s\t[과정종료일] %s\n", dto.getStartDate().substring(0, 11), dto.getEndDate().substring(0, 11));
			System.out.println();
			System.out.printf("\t\t\t[필기배점] %s\t[실기배점] %s\t[출결배점] %s\n", dto.getHandwritingAllot(), dto.getPracticeAllot(), dto.getAttendanceAllot());
			System.out.printf("\t\t\t[필기점수] %s\t[실기점수] %s\t[출결점수] %s\n", dto.getHandwritingScore(), dto.getPracticeScore(), dto.getAttendanceScore());
			System.out.printf("\t\t\t[시험일] %s\n", dto.getExamDate().substring(0, 11));
			System.out.println("\t\t------------------------------------------------------------------------------------");
			System.out.println();
			
		}
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
		System.out.println("\t\t\t\t*\t\t성 적 조 회\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void showMenu(boolean isFirstTime) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t1. 뒤로가기");
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
