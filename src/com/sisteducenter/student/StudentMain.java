package com.sisteducenter.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.admin.grade.ShowGrade;
import com.sisteducenter.admin.lectureevaluation.LectureEvaluationStudent;
import com.sisteducenter.attendance.AttendanceMain;
import com.sisteducenter.attendance.ShowAttendanceAll;
import com.sisteducenter.jobinfo.ShowJobInfoList;

/**
 * 교육생의 메인 화면을 담당하는 클래스입니다.
 * @author 조아라
 *
 */
public class StudentMain {

	private static Scanner scan;
	
	static {
		scan=new Scanner(System.in);
	}
	
	public static void showMain(String seq) {
		
		boolean loop=true;
		boolean isFirstTime=true;
		// 값에 따라 이 메뉴가 처음 출력되는지, 아님 이미 출력이 됐었는지 확인해준다.
		// 값이 true이면 평범한 메뉴번호 입력 메시지가 뜨지만(처음 메뉴를 선택하는 것이기 때문.),
		// 메뉴 외의 다른 값을 입력하게 되면 잘못된 메뉴 번호를 입력했다는 메시지를
		// 한 화면 전이 아니라, 새로 뜨는 화면에서 확인할 수 있게 해준다.
		
		while(loop) {
			
			// 헤더 출력
			showHeader();
			
			// 수강한 과정정보를 담고 있는 간략한 타이틀 출력
			showInfo(seq);
			ShowAttendanceAll.showAttendanceRate(seq);
			
			// 메인메뉴 출력
			showMenu(isFirstTime);
			isFirstTime=false;
			// 메뉴를 이미 한 번 보여줬으므로 값을 false로 바꾼다.
			// 이후엔 메뉴번호를 제대로 입력하지 않으면 잘못된 메뉴 번호를 입력했다는 메시지가
			// 새 화면에 함께 뜬다.
			
			/*
			 * 메뉴 구성
			 * 
			 * 1. 출결 조회 및 관리
			 * 2. 성적 조회
			 * 3. 강의 평가
			 * 4. 개인 정보 조회
			 * 5. 구인 정보 조회
			 * 00. 종료
			 * */
			
			String sel=scan.nextLine();
			
			if (sel.equals("1")){ // 출결 조회 및 관리
				AttendanceMain.showMain(seq);
				break;
				
			}else if (sel.equals("2")) { // 성적 조회
				ShowGrade.showGradeList(seq);
				break;
				
			}else if (sel.equals("3")) { // 강의 평가
				LectureEvaluationStudent.showEvaluation(seq);
				break;
				
			}else if (sel.equals("4")) { // 개인 정보 조회
				ShowInfo.showPersonalInfo(seq);
				break;
				
			}else if (sel.equals("5")) { // 구인 정보 조회
				ShowJobInfoList.showAllInfo(seq);
				break;
				
			}else if (sel.equals("00")) { // 프로그램 종료
				
				System.out.println("\t\t\t\t\t프로그램을 종료합니다.");
				break;
			}
			
		} // while
	}
	
	private static void showMenu(boolean isFirstTime) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t1. 출결 조회 및 입력");
		System.out.println("\t\t\t\t\t\t2. 성적 조회");
		System.out.println("\t\t\t\t\t\t3. 강의 평가");
		System.out.println("\t\t\t\t\t\t4. 개인 정보 조회 및 수정");
		System.out.println("\t\t\t\t\t\t5. 구인 정보 조회");
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
	
	private static void showInfo(String seq) {
		
		ArrayList<String> list=new ArrayList<String>();
		
		list=StudentDAO.getTitle(seq);
		// 교육생번호를 인자로 보내서 교육생이 수강한 과정의 간략한 정보를 list로 가져온다.
		/*
		 * list.get(0) : 교육생명
		 * list.get(1) : 과정명
		 * list.get(2) : 과정시작일
		 * list.get(3) : 과정종료일
		 * list.get(4) : 강의실
		 * */
		
		System.out.printf("\t\t\t\t안녕하세요, %s님!\n", list.get(0));
		
		if (list.get(1)!=null) { // 과정명이 null이 아니라면.
			System.out.printf("\t\t\t\t* %s(%s ~ %s)\n", list.get(1), list.get(2), list.get(3));
			System.out.printf("\t\t\t\t* 강의실 : %s강의실\n", list.get(4));
		}else { // 과정명이 null이라면. 수강한 과정이 없음.
			System.out.println("\t\t\t\t* 수강한 과정이 없습니다.");
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
		System.out.println("\t\t\t\t*\t\t교 육 생 메 인\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}

}
