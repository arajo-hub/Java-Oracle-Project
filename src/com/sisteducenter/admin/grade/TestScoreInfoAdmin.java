package com.sisteducenter.admin.grade;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.admin.TestScoreManagingMainAdmin;
import com.sisteducenter.admin.opencourse.VwOpenCourseAdminDAO;
import com.sisteducenter.admin.opencourse.VwOpenCourseAdminDTO;
import com.sisteducenter.admin.opensubject.VwOpenSubjectAdminDAO;
import com.sisteducenter.admin.opensubject.VwOpenSubjectAdminDTO;

/**
 * 
 * @author 이준오
 * @for 교육생 성적 정보 조회
 *
 */
public class TestScoreInfoAdmin {

	/**
	 * 교육생 성적 정보 조회 메뉴를 나타내는 메서드입니다.
	 */
	public static void intro() {
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("         교육생 성적 정보 조회");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("1. 개설 과목별 조회");
		System.out.println();
		System.out.println("2. 교육생 개인별 조회");
		System.out.println();
		System.out.println("0. 이전 화면");
		System.out.println();
		System.out.println();
		System.out.print("번호 선택 : ");
		Scanner scan = new Scanner(System.in);
		String sel = scan.nextLine();
		
		boolean flag = true;
		
		while (flag) {
			
			if (sel.equals("1")) {
				flag = false;
				searchBySubject();
				
			} else if (sel.equals("2")) {
				flag = false;
				searchByStudent();
				
			} else if (sel.equals("0")) {
				flag = false;
				TestScoreManagingMainAdmin.intro();
				
			} else {
				flag = true;
				System.out.println("============================================");
				System.out.println("올바르지 않은 입력입니다. 다시 입력 하세요.");
				System.out.println();
				System.out.println("1. 개설 과목별 조회");
				System.out.println();
				System.out.println("2. 교육생 개인별 조회");
				System.out.println();
				System.out.println();
				System.out.print("번호 선택 : ");
				sel = scan.nextLine();
			}
		}
		
		
	}
	
	private static void searchBySubject() {
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("          개설 과목별 조회");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("과정 목록이 출력 중입니다..");
		System.out.println();
		
		VwOpenCourseAdminDAO dao = new VwOpenCourseAdminDAO();
		ArrayList<VwOpenCourseAdminDTO> arrList = dao.courseList();
		System.out.printf("[개설과정번호]\t[과정명]\t\t\t\t\t\t\t[과정시작일]\t\t[과정종료일]\t\t\t[강의실번호]\n");
		for (VwOpenCourseAdminDTO dto : arrList) {
			System.out.printf("\t%s\t%-40s\t%-10s\t\t%s\t\t%s\n",
					dto.getOpenCourSeq(),
					dto.getCourseName(),
					dto.getStartDate(),
					dto.getEndDate(),
					dto.getLectureRoomSeq());
			
		}
		
		System.out.println();
		System.out.print("개설과정번호 : ");
		Scanner scan = new Scanner(System.in);
		String cSeq = scan.nextLine();
		System.out.println();
		System.out.println("과정 내 과목 목록을 생성중입니다...");
		System.out.println();
		System.out.println();
		System.out.println(" ---- 과목 목록 출력 ---");
		System.out.println();
		System.out.printf("[개설과목번호]\t[과목명]\t\t[과목시작일]\t\t[과목종료일]\t\t[교사이름]\t[강의실번호]\n");
		VwOpenSubjectAdminDAO vsdao = new VwOpenSubjectAdminDAO();
				
		ArrayList<VwOpenSubjectAdminDTO> arrList2 = vsdao.subjectList(cSeq);
		
		for (VwOpenSubjectAdminDTO vsdto : arrList2) {
			System.out.printf("\t%s\t%-16s\t%s\t%s\t%s\t\t%s\n",
					vsdto.getOpenSubSeq(),
					vsdto.getSubjectName(),
					vsdto.getStartDate(),
					vsdto.getEndDate(),
					vsdto.getTeacherName(),
					vsdto.getLectureRoomSeq());
			
		}
		System.out.println();
		System.out.println(" --- 과목 목록 출력 끝 ---");
		System.out.println();
		System.out.println();
		System.out.println(" ** 과목 내 교육생 성적 목록이 출력됩니다..");
		System.out.println();
		System.out.print("과목 번호 입력 : ");
		
		String sSeq = scan.nextLine();
		
		System.out.println();
		System.out.println(" ---- 성적 목록이 출력 중입니다.---");
		System.out.println();
		System.out.printf("[개설과정명]\t\t\t\t\t[개설과목명]\t[과목시작일]\t\t[과목종료일]\t\t[강의실번호]\t[교사명]\t[교육생이름]\t[주민번호뒷자리]\t[필기점수]\t[실기점수]\t[교재명]\n");
		
		VwOpenSubGradeAdminDAO vgdao = new VwOpenSubGradeAdminDAO();
		
		ArrayList<VwOpenSubGradeAdminDTO> arrList3 = vgdao.gradeList(sSeq);
		
		for (VwOpenSubGradeAdminDTO dto : arrList3) {
			
			System.out.printf("%s\t%s\t\t%s\t%s\t%s\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n",
					dto.getCourseName(),
					dto.getSubjectName(),
					dto.getSubjectStartDate(),
					dto.getSubjectEndDate(),
					dto.getLectureRoomSeq(),
					dto.getTeacherName(),
					dto.getStudentName(),
					dto.getIdNum(),
					dto.getHandwritingScore(),
					dto.getPracticeScore(),
					dto.getBookTitle());
			
		}
		System.out.println();
		System.out.println(" ---- 성적 목록 출력 완료 ---");
		System.out.println();
		System.out.println("이전 화면으로 돌아가려면 엔터를 눌러주세요.");
		scan.nextLine();
		TestScoreManagingMainAdmin.intro();
		
	}
	
	private static void searchByStudent() {
		
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("          교육생 개인별 조회");
		System.out.println("====================================");
		System.out.println();
		System.out.print("교육생 번호 : ");
		
		Scanner scan = new Scanner(System.in);
		String seq = scan.nextLine();
		
		System.out.println(" ---- 성적 목록이 출력 중입니다.---");
		System.out.println();
		System.out.printf("[개설과정명]\t\t\t\t\t[개설과목명]\t[과목시작일]\t\t[과목종료일]\t\t[강의실번호]\t[교사명]\t[교육생이름]\t[주민번호뒷자리]\t[필기점수]\t[실기점수]\t[교재명]\n");
		
		VwOpenSubGradeAdminDAO vgdao = new VwOpenSubGradeAdminDAO();
		
		ArrayList<VwOpenSubGradeAdminDTO> arrList = vgdao.studentGradeList(seq);
		for (VwOpenSubGradeAdminDTO dto : arrList) {
			
			System.out.printf("%s\t%s\t\t%s\t%s\t%s\t\t%s\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\n",
					dto.getCourseName(),
					dto.getSubjectName(),
					dto.getSubjectStartDate(),
					dto.getSubjectEndDate(),
					dto.getLectureRoomSeq(),
					dto.getTeacherName(),
					dto.getStudentName(),
					dto.getIdNum(),
					dto.getHandwritingScore(),
					dto.getPracticeScore(),
					dto.getBookTitle());
			
		}
		System.out.println();
		System.out.println(" ---- 성적 목록 출력 완료 ---");
		
		System.out.println();
		System.out.println("이전 화면으로 돌아가려면 엔터를 눌러주세요.");
		scan.nextLine();
		TestScoreManagingMainAdmin.intro();
		
	}


}
