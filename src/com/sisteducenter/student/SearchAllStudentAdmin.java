package com.sisteducenter.student;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.admin.opencourse.VwStudentOpenCourseAdminDAO;
import com.sisteducenter.admin.opencourse.VwStudentOpenCourseAdminDTO;
/**
 * 
 * @author 이준오
 * @for 전체 교육생 정보 보기
 */
public class SearchAllStudentAdmin {

	/**
	 * 교육생 정보를 보여주는 화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("       전체 교육생 정보 보기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.printf("[교육생 번호]\t[교육생 이름]\t[주민번호 뒷자리]\t[전화번호]\t\t[등록일]\t\t[수강(신청) 횟수]\n");
		
		VwAllStudentAdminDTO vdto = new VwAllStudentAdminDTO();
		VwAllStudentAdminDAO vdao = new VwAllStudentAdminDAO();
		
		ArrayList<VwAllStudentAdminDTO> vwList = vdao.list();
		
		for (VwAllStudentAdminDTO dto : vwList) {
			System.out.printf("\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
					dto.getStudentSeq(),
					dto.getStudentName(),
					dto.getIdNumber(),
					dto.getTelNumber(),
					dto.getRegDate(),
					dto.getCount());
						
		}
		
		System.out.println("1. 특정 교육생 정보 보기");
		System.out.println();
		System.out.println("0. 이전메뉴");
		System.out.println();
		System.out.print("번호 입력 : ");
		String sel = scan.nextLine();
		
		boolean flag = true;
		
		while (flag) {
			if (sel.equals("0")) {
				flag = false;
				SearchStudentAdmin.intro();
				
			} else if (sel.equals("1")){
				flag = false;
				chooseOne();
			} else {
				flag = true;
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println();
				System.out.println("0. 이전메뉴");
				System.out.println();
				System.out.println();
				System.out.print("번호 입력 : ");
				sel = scan.nextLine();
			
			}
		}
	}

	private static void chooseOne() {
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.print(" * 교육생 번호 입력 : ");
		String seq = scan.nextLine();
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("  선택하신 교육생 번호는 "+ seq + "번 입니다.");
		System.out.println("--------------------------------------------");
		System.out.println("* 계속 진행하기(Y)");
		System.out.println("* 교육생번호 다시 선택하기(N)");
		System.out.println();
		System.out.print("입력(Y/N) : ");
		String sel = scan.nextLine();
		
		boolean flag = true;
		
		while (flag) {
			
			if (sel.toUpperCase().equals("Y")) {
				flag = false;
				
			} else if (sel.toUpperCase().equals("N")) {
				
				flag = false;
				chooseOne();
				
			} else {
				
				flag = true;
				
				System.out.println("올바르지 않은 입력입니다. 다시 입력하세요.");
				System.out.println("계속 진행하기(Y)");
				System.out.println("교육생번호 다시 선택하기(N)");
				System.out.println();
				System.out.print("입력(Y/N) : ");
				sel = scan.nextLine();
				
			}//if
		
		}//while
		
		
		
		
		
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("       특정 교육생 정보 보기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		
		VwStudentOpenCourseAdminDTO vdto = new VwStudentOpenCourseAdminDTO();
		VwStudentOpenCourseAdminDAO vdao = new VwStudentOpenCourseAdminDAO();
		
		
			
		ArrayList<VwStudentOpenCourseAdminDTO> vwList2 = vdao.list(seq);
		
		
		System.out.printf("*** %s번 교육생 정보 ***\n",seq);
		System.out.println();
		System.out.printf("[과정명]\t\t\t\t\t[과정시작일]\t\t[과정종료일]\t\t[강의실]\t[수료 및 중도탈락]\t[수료 및 중도탈락 날짜]\n");
		for (VwStudentOpenCourseAdminDTO dto : vwList2) {
			
			if (dto.getStudentSeq().equals(seq)) {
				
				System.out.println();
				System.out.printf("%s\t\t%s\t%s\t%s\t\t%s\t\t\t%s\n",
						dto.getName(),
						dto.getStartDate(),
						dto.getEndDate(),
						dto.getLectureRoomSeq(),
						dto.getState(),
						dto.getStateDate());
				
				
				System.out.println();
				System.out.println();
			}
			
			System.out.println();
			pause();
		}
		
		
		
		
		
		
	} 
	
	private static void pause() {
		
		System.out.println("이전 단계로 돌아가시려면 엔터를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		SearchStudentAdmin.intro();
		
	}
	


}
