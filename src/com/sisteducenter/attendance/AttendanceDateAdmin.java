package com.sisteducenter.attendance;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 관리자가 날짜별 출결현황을 조회하는 클래스입니다.
 * @author 이준오
 * @for 출결 현황 날짜별 조회
 *
 */
public class AttendanceDateAdmin {

	/**
	 * 관리자에게 날짜별 출결 현황 조회화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("       출결 현황 날짜별 조회 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println("** 날짜 입력시 YYYY-MM-DD 로 입력하시오...");
		System.out.print("날짜 입력(YYYY-MM-DD) : ");
		Scanner scan = new Scanner(System.in);
		String date = scan.nextLine();
		boolean flag = true;
		while (flag) {
			if (date.length() == 10 && date.indexOf("-")==4 && date.lastIndexOf("-")==7 && Integer.parseInt(date.substring(5,7)) >= 1 && Integer.parseInt(date.substring(5,7)) <= 12 &&	Integer.parseInt(date.substring(8)) <= 31) {
				flag = false;	
			} else {
				flag = true;
				System.out.println();
				System.out.println("잘못된 입력입니다. 날짜 입력방식을 다시 확인하고 입력하세요.");
				System.out.println("** 날짜 입력시 YYYY-MM-DD 로 입력하시오...");
				System.out.print("날짜 입력(YYYY-MM-DD) : ");
				date = scan.nextLine();
			}
			
		}
		System.out.println("교육생의 출결 목록을 생성중입니다...");
		System.out.println();
		System.out.println();
		System.out.println(" ---- 출결 목록 출력 ---");
		System.out.println();
		System.out.printf("[교육생번호]\t[교육생이름]\t[날짜]\t\t[출결상태]\n");
		VwStudentInSubjectAdminDAO vdao = new VwStudentInSubjectAdminDAO();
		ArrayList<VwStudentInSubjectAdminDTO> arrList = vdao.allStudent(date);
		
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
