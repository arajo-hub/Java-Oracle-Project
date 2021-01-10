package com.sisteducenter.attendance;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 교육생별 출결조회를 위한 화면을 담당하는 클래스입니다.
 * @author 이준오
 * @for 교육생 별 출결 조회
 * 
 */
public class AttendanceStudentAdmin {

	/**
	 * 교육생별 출결조회 화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("       교육생 별 출결 조회 ");
		System.out.println("====================================");
		System.out.println();
		System.out.print("교육생 번호 입력 : ");
		Scanner scan = new Scanner(System.in);
		String seq = scan.nextLine();
		
		VwStudentInSubjectAdminDAO vdao = new VwStudentInSubjectAdminDAO();
		ArrayList<VwStudentInSubjectAdminDTO> arrList = vdao.selStudent(seq);
		System.out.println("교육생의 출결 목록을 생성중입니다...");
		System.out.println();
		System.out.println();
		System.out.println(" ---- 출결 목록 출력 ---");
		System.out.println();
		System.out.printf("[교육생번호]\t[교육생이름]\t[날짜]\t\t[출결상태]\n");
		for (VwStudentInSubjectAdminDTO vdto : arrList) {
			System.out.printf("%s\t\t%s\t\t%s\t%s\n",
					vdto.getStudentSeq(),
					vdto.getStudentName(),
					vdto.getInDate(),
					vdto.getState());
		}
		System.out.println();
		System.out.println("상위 메뉴로 이동합니다...");
		System.out.println("계속 하시려면 엔터를 눌러주세요...");
		scan.nextLine();
		AttendanceAdmin.intro();
	}

	
	
	

}
