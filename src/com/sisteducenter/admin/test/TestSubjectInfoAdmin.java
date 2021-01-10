package com.sisteducenter.admin.test;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.admin.TestScoreManagingMainAdmin;
import com.sisteducenter.admin.opencourse.VwOpenCourseAdminDAO;
import com.sisteducenter.admin.opencourse.VwOpenCourseAdminDTO;


/**
 * 
 * @author 이준오
 * @for 과목 시험 정보 조회
 *
 */
public class TestSubjectInfoAdmin {

	public static void intro() {
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("         과목 시험 정보 조회");
		System.out.println("====================================");
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
		String seq = scan.nextLine();
		System.out.println("과정에 속한 과목의 시험 정보를 불러오는 중입니다...");
		System.out.println();
		System.out.printf("[개설과목번호]\t[과목명]\t[교사명]\t[시험일]\t\t[강의실번호]\n");
		VwExamAdminDAO vedao = new VwExamAdminDAO();
		
		ArrayList<VwExamAdminDTO> arrList2 = vedao.testInfo(seq);
		
		for (VwExamAdminDTO vedto : arrList2) {
			System.out.printf("\t%s\t%-9s\t%s\t\t%s\t%s\n",
					vedto.getOpenSubSeq(),
					vedto.getSubjectName(),
					vedto.getTeacherName(),
					vedto.getExamDate(),
					vedto.getLectureRoomSeq());
			
		}
		System.out.println();
		System.out.println("----- 출력 완료 -----");
		System.out.println();
		System.out.println("이전 단계로 돌아가려면 엔터를 눌러주세요.");
		scan.nextLine();
		TestScoreManagingMainAdmin.intro();
		
		
		
		
		
	}

}
