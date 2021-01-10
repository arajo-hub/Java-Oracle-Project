package com.sisteducenter.attendance;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.admin.opencourse.VwOpenCourseAdminDAO;
import com.sisteducenter.admin.opencourse.VwOpenCourseAdminDTO;
import com.sisteducenter.admin.opensubject.VwOpenSubjectAdminDAO;
import com.sisteducenter.admin.opensubject.VwOpenSubjectAdminDTO;


/**
 * 개설과정내 출결조회를 위한 화면을 담당하는 클래스입니다.
 * @author 이준오
 * @for 개설 과정 내 출결 조회
 *
 */
public class AttendanceSubjectAdmin {

	/**
	 * 개설과정내 출결조회를 위한 화면을 나타냅니다.
	 */
	public static void intro() {
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("       개설 과정 내 출결 조회 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println(" ---- 과정 목록 출력 ---");
		System.out.println();
		
		VwOpenCourseAdminDAO vdao = new VwOpenCourseAdminDAO();
		
		ArrayList<VwOpenCourseAdminDTO> arrList = vdao.courseList();
		
		
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
		System.out.println(" --- 과정 목록 출력 끝 ---");
		System.out.println();
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("1. 과정 내 과목 목록 보기");
		System.out.println();
		System.out.println("0. 이전 화면");
		System.out.println();
		System.out.println();
		System.out.print("번호 입력 : ");
		Scanner scan = new Scanner(System.in);
		String sel = scan.nextLine();
		
		boolean flag = true;
		while(flag) {
			if (sel.equals("1")) {
				flag = false;
				subList();
				
			} else if (sel.equals("0")) {
				flag = false;
				AttendanceAdmin.intro();
				
			} else {
				flag = true;
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println("1. 과정 내 과목 목록 보기");
				System.out.println();
				System.out.println("0. 이전 화면");
				System.out.println();
				System.out.println();
				System.out.print("번호 입력 : ");
				sel = scan.nextLine();
			}
		}
		
		
		
	}

	private static void subList() {
		System.out.println();
		System.out.println(" ** 과정 내 과목 목록이 출력됩니다..");
		System.out.print("과정 번호 입력 : ");
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
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("1. 과목 내 출결 목록 보기");
		System.out.println();
		System.out.println("0. 이전 화면");
		System.out.println();
		System.out.println();
		System.out.print("번호 입력 : ");
		String sel = scan.nextLine();
		
		boolean flag = true;
		while(flag) {
			if (sel.equals("1")) {
				flag = false;
				attendanceList();
				
			} else if (sel.equals("0")) {
				flag = false;
				AttendanceAdmin.intro();
				
			} else {
				flag = true;
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println("1. 과목 내 출결 목록 보기");
				System.out.println();
				System.out.println("0. 이전 화면");
				System.out.println();
				System.out.println();
				System.out.print("번호 입력 : ");
				sel = scan.nextLine();
			}
		}
		
		
		
	}

	private static void attendanceList() {
		System.out.println();
		System.out.println(" ** 과목 내 교육생 출결 목록이 출력됩니다..");
		System.out.print("과목 번호 입력 : ");
		Scanner scan = new Scanner(System.in);
		String sSeq = scan.nextLine();
		System.out.println();
		System.out.println("과목 내 출결 목록을 생성중입니다...");
		System.out.println();
		System.out.println();
		System.out.println(" ---- 출결 목록 출력 ---");
		System.out.println();
		System.out.printf("[과목명]\t[교육생번호]\t[교육생이름]\t[날짜]\t[출결상태]\n");
		
		VwSubjectStudentAdminDAO vdao = new VwSubjectStudentAdminDAO();
		ArrayList<VwSubjectStudentAdminDTO> arrList = vdao.studentList(sSeq);
		
		for(VwSubjectStudentAdminDTO dto : arrList) {
			System.out.printf("%s\t\t%s\t\t%s\t\t%s\t%s\n",
					dto.getSubjectName(),
					dto.getStudentSeq(),
					dto.getStudentName(),
					dto.getInTime(),
					dto.getState());
		}
		System.out.println();
		System.out.println(" --- 출결 목록 출력 끝 ---");
		System.out.println();
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("상위 메뉴로 이동합니다...");
		System.out.println("계속 하시려면 엔터를 눌러주세요...");
		scan.nextLine();
		AttendanceAdmin.intro();
		
		
		
		
	}
	

}
