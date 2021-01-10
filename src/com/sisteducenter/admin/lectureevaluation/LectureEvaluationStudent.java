package com.sisteducenter.admin.lectureevaluation;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.student.studentMain;

/**
 * 교육생의 강의평가를 조회할 수 있는 클래스입니다.
 * @author 조아라
 *
 */
public class LectureEvaluationStudent {
	
	static Scanner scan=new Scanner(System.in);

	/**
	 * 교육생의 강의평가를 나타내는 메서드입니다.
	 * @param seq
	 */
	public static void showEvaluation(String seq) {
		ArrayList<LectureEvaluationStudentDTO> list=new ArrayList<LectureEvaluationStudentDTO>();
		
		boolean loop=true;
		boolean isFirstTime=true;
		boolean alreadyDone=false;
		
		while(loop) {
			
			// 헤더 출력
			showHeader();
			
			// 수강한 과정의 과목리스트 출력
			list=LectureEvaluationStudentDAO.getList(seq);
			System.out.println("\t\t----------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t[번호]\t\t[과목명]\t\t\t[기간]\t[구분]\t[수업준비점수]\t[내용전달점수]\t[유익도점수]\t[평점]");
			System.out.println("\t\t----------------------------------------------------------------------------------------------------------------");
			for(int i=0;i<list.size();i++) {
				System.out.printf("\t\t%-5d\t%-25s\t%2s주\t%s\t%5s\t\t%5s\t\t%5s\t\t%5s\n", i+1,
																	list.get(i).getSubjectName(),
																	list.get(i).getPeriod(),
																	list.get(i).getDivision(),
																	list.get(i).getPreparationscore(),
																	list.get(i).getUnderstandscore(),
																	list.get(i).getUsefulscore(),
																	list.get(i).getTotalscore());
			}
			
			// 메인메뉴 출력
			showMenu(isFirstTime, alreadyDone);
			isFirstTime=false;
			alreadyDone=false;
			// 메뉴를 이미 한 번 보여줬으므로 값을 false로 바꾼다.
			// 이후엔 메뉴번호를 제대로 입력하지 않으면 잘못된 메뉴 번호를 입력했다는 메시지가
			// 새 화면에 함께 뜬다.
			
			
			/*
			 * 메뉴 구성
			 * 
			 * 00. 종료
			 * */
			
			
			String sel=scan.nextLine();
			
			if (sel.equals("")) {
				studentMain.showMain(seq);
			}else if (sel.equals("00")) { // 종료 선택
				loop=false;
			}else{
				if (Integer.parseInt(sel)>=0 && Integer.parseInt(sel)<=list.size()) {
			
					// 그 과목으로 이동
					
					int selNum=Integer.parseInt(sel)-1;
					
					
					// 이미 강의평가가 완료된 과목을 선택했다면
					if (Integer.parseInt(list.get(selNum).getPreparationscore())!=0
							&& Integer.parseInt(list.get(selNum).getUnderstandscore())!=0
							&& Integer.parseInt(list.get(selNum).getUsefulscore())!=0
							&& list.get(selNum).getTotalscore()!=0) {
						alreadyDone=true;
						
					}else { // 강의평가가 완료되지 않은 과목을 선택했다면
						SetEachEvaluationStudent.setEachSubjectEvalution(list.get(selNum), seq); // 개설과목번호를 넘겨준다.
						loop=false;					
					}
				}
			}
			
		} // while

	}
	
	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t강의평가조회\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void showMenu(boolean isFirstTime, boolean alreadyDone) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t* 엔터를 누르면 뒤로 이동합니다.");
		System.out.println("\t\t\t\t\t\t00. 종료");
		System.out.println();
		System.out.println();
		
		if (isFirstTime) { // 메뉴를 처음 볼 때의 메시지
			System.out.println("\t\t\t\t\t* 과목평가를 진행할 과목번호를 입력해주세요.");
			System.out.print("\t\t\t\t\t▶︎ ");
		}else {
			if (alreadyDone) { // 이미 메뉴를 한 번 봤고, 과목평가가 진행된 경우의 메시지
				System.out.println("\t\t\t\t\t[!!!]");
				System.out.println("\t\t\t\t\t이미 과목평가를 진행한 과목번호입니다.");
				System.out.println("\t\t\t\t\t다시 입력해주세요.");
				System.out.print("\t\t\t\t\t▶︎ ");
			}else { // 이미 메뉴를 한 번 봤고, 잘못된 과목번호를 입력한 경우의 메시지
				System.out.println("\t\t\t\t\t[!!!]");
				System.out.println("\t\t\t\t\t잘못된 과목번호입니다.");
				System.out.println("\t\t\t\t\t다시 입력해주세요.");
				System.out.print("\t\t\t\t\t▶︎ ");
			}
		}
		
	}
}
