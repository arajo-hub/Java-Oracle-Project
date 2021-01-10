package com.sisteducenter.admin.lectureevaluation;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.admin.AdminMain;
import com.sisteducenter.admin.opencourse.OpenCourseListDTO;
import com.sisteducenter.admin.opencourse.SpecificOpenCourseDTO;
import com.sisteducenter.admin.opencourse.SpecificStudentDTO;

/**
 * 메인 메뉴에서 강의평가 선택시 화면을 나타내는 클래스입니다.
 * 강의평가를 조회하고, 강의평가를 실행할 수 있습니다.
 * @author 장진영
 *
 */
public class LectureEvaluationAdmin {
	
	
	private static Scanner scan;
	private static LectureEvaluationAdminDAO leDAO;
	
	static {
		scan = new Scanner(System.in);
		leDAO = new LectureEvaluationAdminDAO();
//			osDAO = new OpenSubjectDAO();
//			ocDAO = new OpenCourseDAO();
	}

	/**
	 * 강의평가 선택시 메뉴를 나타내는 메서드입니다.
	 */
	public static void showMain() {
		
		boolean loop = true;
		
		
		while(loop) {
			

			System.out.println("1. 강의 평가 조회");
			System.out.println("2. 강의 평가 개설");
			System.out.println("3. 강의 평가 삭제");
			System.out.println("4. 뒤로가기");
			System.out.println("00. 종료");
			
			System.out.print("입력 : ");
			String sel = scan.nextLine();
			
			switch(sel) {
				case "1":
					list();
					break;
				case "2":
					add();
					break;
				case "3":
					delete();
					break;
				case "4":
					AdminMain.showMain();
					loop = false;
				case "00":
					loop = false;
					break;
				default:
					System.out.println("잘못된 입력입니다.");
					break;
			}
		}
		
	}


	private static void list() {
		
		System.out.println("\n\t\t\t\t\t\t[개설 과정 조회]");
		
		ArrayList<OpenCourseListDTO> ocList = leDAO.ocList();
		int n = 0;
		
		line();
		//개설 과정명, 개설 과정기간(시작 년월일, 끝 년월일), 강의실명, 개설 과목 등록 여부, 교육생 등록 인원
		System.out.println("[번호]\t\t[과정명]\t\t\t\t\t\t[과정기간]\t     [강의실]   [등록인원]");
		for(OpenCourseListDTO oc : ocList) {
			System.out.printf("%2d\t%-40s\t%s ~ %s\t%1s\t%s\n"
								,n+1
								,oc.getCoursename()
								,oc.getStartdate(), oc.getEnddate()
								,oc.getLectureroom()
								,oc.getRegstudentnumber());
			n++;
		}
		
		//개설 과정 선택
		System.out.print ("개설 과정 선택 : ");
		int sel = scan.nextInt();
		
		//시퀀스 번호를 가져온다.
		String seq = ocList.get(sel-1).getSeq();
		
		//시퀀스 번호를 가지고 쿼리문 다시 작성 오버로딩
		ArrayList<SpecificOpenCourseDTO> socList = leDAO.socList(seq);
		line();
		n=0;
		System.out.printf("과정명 : %s\n과정기간 : %s ~ %s\n",ocList.get(sel-1).getCoursename(), ocList.get(sel-1).getStartdate(), ocList.get(sel-1).getEnddate());
		//개설 과목 정보(과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명)
		System.out.printf("[번호]\t[과목명]\t\t\t\t\t[과정기간]\t\t\t[교재명]\t\t\t\t\t[교사명]\n");
		for(SpecificOpenCourseDTO soc : socList) {
			System.out.printf("%2d\t%-25s\t%s ~ %s\t%-40s\t%s\n"
								, n+1
								, soc.getSubjectname()
								, soc.getStartdate(), soc.getEnddate()
								, soc.getTitle()
								, soc.gettName());
			n++;
		}
		
		System.out.print("조회하실 강의 평가번호를 입력하세요 : ");
		sel = scan.nextInt();
		
		seq = socList.get(sel-1).getSeq();
		
		ArrayList<LectureEvaluationAdminDTO> leList = leDAO.leList(seq);
		System.out.println("[이름]\t[준비점수]\t[전달점수]\t[유익도]\t[평균]");
		for(LectureEvaluationAdminDTO le : leList) {
			System.out.printf("%s\t", le.getsName());
			System.out.printf("%5s\t", le.getPrepareationScore());
			System.out.printf("%5s\t", le.getUnderstandScore());
			System.out.printf("%5s\t", le.getUsefulScore());
			System.out.printf("%5s\n", le.getTotalScore());
		}

		System.out.println();
		pause();
		
	}
	
	private static void delete() {
		
		ArrayList<OpenCourseListDTO> ocList = leDAO.ocList();
		int n = 0;
		
		line();
		//개설 과정명, 개설 과정기간(시작 년월일, 끝 년월일), 강의실명, 개설 과목 등록 여부, 교육생 등록 인원
		System.out.println("[번호]\t\t[과정명]\t\t\t\t\t\t[과정기간]\t     [강의실]   [등록인원]");
		for(OpenCourseListDTO oc : ocList) {
			System.out.printf("%2d\t%-40s\t%s ~ %s\t%1s\t%s\n"
								,n+1
								,oc.getCoursename()
								,oc.getStartdate(), oc.getEnddate()
								,oc.getLectureroom()
								,oc.getRegstudentnumber());
			n++;
		}
		
		//개설 과정 선택
		System.out.print ("개설 과정 선택 : ");
		String sel = scan.nextLine();
		
		//시퀀스 번호를 가져온다.
		String opencourseq = ocList.get(Integer.parseInt(sel)-1).getSeq();

		//특정 과정의 과목 seq 번호 가져옴
		ArrayList<SpecificOpenCourseDTO> socList = leDAO.socList(opencourseq);
		line();
		n=0;
		System.out.printf("과정명 : %s\n과정기간 : %s ~ %s\n",ocList.get(Integer.parseInt(sel)-1).getCoursename(), 
							ocList.get(Integer.parseInt(sel)-1).getStartdate(), ocList.get(Integer.parseInt(sel)-1).getEnddate());
		//개설 과목 정보(과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명)
		System.out.printf("[번호]\t[과목명]\t\t\t\t\t[과정기간]\t\t\t[교재명]\t\t\t\t\t[교사명]\n");
		for(SpecificOpenCourseDTO soc : socList) {
			System.out.printf("%2d\t%-25s\t%s ~ %s\t%-40s\t%s\n"
								, n+1
								, soc.getSubjectname()
								, soc.getStartdate(), soc.getEnddate()
								, soc.getTitle()
								, soc.gettName());
			n++;
		}
		
		System.out.print("삭제할 과목 선택 : ");
		sel = scan.nextLine();
		
		String opensubseq = socList.get(Integer.parseInt(sel)-1).getSeq();
		
		int result = leDAO.delete(opensubseq);
		
		if(result > 0)
			System.out.println("강의평가가 삭제되었습니다.");
		else
			System.out.println("강의평가가 삭제되지못했습니다.");
		
		
	}

	private static void add() {
		
		ArrayList<OpenCourseListDTO> ocList = leDAO.ocList();
		int n = 0;
		
		line();
		//개설 과정명, 개설 과정기간(시작 년월일, 끝 년월일), 강의실명, 개설 과목 등록 여부, 교육생 등록 인원
		System.out.println("[번호]\t\t[과정명]\t\t\t\t\t\t[과정기간]\t     [강의실]   [등록인원]");
		for(OpenCourseListDTO oc : ocList) {
			System.out.printf("%2d\t%-40s\t%s ~ %s\t%1s\t%s\n"
								,n+1
								,oc.getCoursename()
								,oc.getStartdate(), oc.getEnddate()
								,oc.getLectureroom()
								,oc.getRegstudentnumber());
			n++;
		}
		
		//개설 과정 선택
		System.out.print ("개설 과정 선택 : ");
		String sel = scan.nextLine();
		
		//시퀀스 번호를 가져온다.
		String opencourseq = ocList.get(Integer.parseInt(sel)-1).getSeq();

		//특정 과정의 과목 seq 번호 가져옴
		ArrayList<SpecificOpenCourseDTO> socList = leDAO.socList(opencourseq);
		
		//특정 과정을 듣는 학생의 seq번호 가져옴
		ArrayList<SpecificStudentDTO> ssList = leDAO.ssList(opencourseq);
		
		leDAO.add(socList, ssList);
		
		System.out.println("강의평가가 개설되었습니다.");
		
	}
	
	private static void line() {
		
		for(int i=0; i<120; i++)
			System.out.print("-");
		System.out.println();
	}

	private static void pause() {
			line();
			System.out.println("계속하시려면 엔터를 누르세요.");
				scan.nextLine();
	}
}
