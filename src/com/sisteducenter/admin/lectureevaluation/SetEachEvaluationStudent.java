package com.sisteducenter.admin.lectureevaluation;

import java.util.Scanner;

/**
 * 강의평가를 입력하는 클래스입니다.
 * @author 조아라
 *
 */
public class SetEachEvaluationStudent {
	
	static Scanner scan=new Scanner(System.in);

	/**
	 * 개별 과목의 강의평가를 입력하는 메서드입니다.
	 * @param dto : 교육생의 한 과목의 강의평가정보입니다.
	 * @param seq : 교육생번호입니다.
	 */
	public static void setEachSubjectEvalution(LectureEvaluationStudentDTO dto, String seq) {
		
		boolean loop=true;
		boolean isFirstTime=true;
		boolean wrongScore=false;
		
		while(loop) {
			
			// 헤더 출력
			showHeader();
			
			
			/*
			 * 강의평가
			 * 
			 * 1. 수업준비점수
			 * 2. 내용전달점수
			 * 3. 유익도점수
			 * 
			 * */
			
			if(wrongScore) {
				System.out.println("\t\t\t\t[!!!]");
				System.out.println("\t\t\t\t점수는 1 ~ 5 사이의 값만 입력할 수 있습니다.");
				System.out.println();
			}
			
			System.out.println("\t\t\t\t* 교사의 수업준비점수");
			System.out.println("\t\t\t\t* 1 ~ 5 사이의 점수를 입력해주세요.");
			System.out.print("\t\t\t\t▶ ");
			String preparationscore=scan.nextLine();
			
			if (Integer.parseInt(preparationscore)<1
					&& Integer.parseInt(preparationscore)>5) {
				wrongScore=true;
				continue;
			}
			if (preparationscore.equals("")) {
				preparationscore=dto.getPreparationscore();
			}
			
			System.out.println();
			System.out.println("\t\t\t\t* 교사의 내용전달점수");
			System.out.println("\t\t\t\t* 1 ~ 5 사이의 점수를 입력해주세요.");
			System.out.print("\t\t\t\t▶ ");
			String understandscore=scan.nextLine();
			
			
			if (Integer.parseInt(understandscore)<1
					&& Integer.parseInt(understandscore)>5) {
				wrongScore=true;
				continue;
			}
			if (understandscore.equals("")) {
				understandscore=dto.getUnderstandscore();
			}
			System.out.println();
			System.out.println("\t\t\t\t* 과목의 유익도점수");
			System.out.println("\t\t\t\t* 1 ~ 5 사이의 점수를 입력해주세요.");
			System.out.print("\t\t\t\t▶ ");
			String usefulscore=scan.nextLine();
			
			if (Integer.parseInt(usefulscore)<1
					&& Integer.parseInt(usefulscore)>5) {
				wrongScore=true;
				continue;
			}
			if (usefulscore.equals("")) {
				usefulscore=dto.getUsefulscore();
			}
			
			LectureEvaluationStudentDTO dto2=new LectureEvaluationStudentDTO();
			
			dto2.setSubjectseq(dto.getSubjectseq());
			dto2.setSubjectName(dto.getSubjectName());
			dto2.setPeriod(dto.getPeriod());
			dto2.setDivision(dto.getDivision());
			dto2.setPreparationscore(preparationscore);
			dto2.setUnderstandscore(understandscore);
			dto2.setUsefulscore(usefulscore);
			dto2.setTotalscore((int)(Integer.parseInt(preparationscore)
								+Integer.parseInt(understandscore)
								+Integer.parseInt(usefulscore))/3);
			
			int result=LectureEvaluationStudentDAO.setEvaluation(dto2, seq);
			
			System.out.println();
			System.out.println();
			if (result>0) {
				System.out.println("\t\t\t\t강의평가를 완료했습니다.");
			}else {
				System.out.println("\t\t\t\t[!!!]");
				System.out.println("\t\t\t\t강의평가를 완료하지 못했습니다.");
				System.out.println("\t\t\t\t관리자에게 문의해주세요.");
			}
			
			// 메인메뉴 출력
			showMenu(isFirstTime);
			isFirstTime=false;
			
			
			/*
			 * 메뉴 구성
			 * 1. 뒤로가기
			 * 00. 종료
			 * */
			
			String sel=scan.nextLine();
			
			if (sel.equals("1")) {
				LectureEvaluationStudent.showEvaluation(seq);
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
		System.out.println("\t\t\t\t*\t\t강의평가입력\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void showMenu(boolean isFirstTime) {
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
