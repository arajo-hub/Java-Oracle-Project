package com.sisteducenter.student;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author 이준오
 * @for 교육생 정보 검색 하기
 * 
 */
public class SearchOneStudentAdmin {

	/**
	 * 교육생 정보를 검색하는 화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("       교육생 정보 검색 하기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println("검색조건 선택");
		System.out.println();
		System.out.println("1. 교육생 번호");
		System.out.println("2. 교육생 이름");
		System.out.println("3. 주민번호 뒷자리");
		System.out.println("4. 전화번호");
		System.out.println("5. 등록일");
		System.out.println("6. 수강(신청) 횟수");
		System.out.println();
		System.out.println("0. 이전 화면");
		System.out.println();
		System.out.print("번호 입력 : ");
		
		Scanner scan = new Scanner(System.in);
		String sel = scan.nextLine();
		
		boolean flag = true;
		
		while (flag) {
			if (sel.equals("0")) {
				flag = false;
				SearchStudentAdmin.intro();
				
			} else if (sel.equals("1")){
				flag = false;
				SearchBySeq();
			} else if (sel.equals("2")){
				flag = false;
				SearchByName();
			} else if (sel.equals("3")){
				flag = false;
				SearchByIdNum();
			} else if (sel.equals("4")){
				flag = false;
				SearchByTel();
			} else if (sel.equals("5")){
				flag = false;
				SearchByRegDate();
			} else if (sel.equals("6")){
				flag = false;
				SearchByCount();
			} else {
				flag = true;
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println("1. 교육생 번호");
				System.out.println("2. 교육생 이름");
				System.out.println("3. 주민번호 뒷자리");
				System.out.println("4. 전화번호");
				System.out.println("5. 등록일");
				System.out.println("6. 수강(신청) 횟수");
				System.out.println();
				System.out.println("0. 이전메뉴");
				System.out.println();
				System.out.println();
				System.out.print("번호 입력 : ");
				sel = scan.nextLine();
			
			}
		}
		
	}

	private static void SearchBySeq() {
		System.out.println("====================================");
		System.out.println("      교육생 번호로 검색 하기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.print("교육생 번호 : ");
		Scanner scan = new Scanner(System.in);
		String seq = scan.nextLine();
		System.out.println();
		System.out.println();
		System.out.printf("[교육생 번호]\t[교육생 이름]\t[주민번호 뒷자리]\t[전화번호]\t\t[등록일]\t\t[수강(신청) 횟수]\n");
		System.out.println();
		
		VwAllStudentAdminDTO vdto = new VwAllStudentAdminDTO();
		VwAllStudentAdminDAO vdao = new VwAllStudentAdminDAO();
		
		ArrayList<VwAllStudentAdminDTO> vwList = vdao.getSeq(seq);
		for (int i = 0 ; i < vwList.size() ; i++) {
				
				System.out.printf("\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
						vwList.get(i).getStudentSeq(),
						vwList.get(i).getStudentName(),
						vwList.get(i).getIdNumber(),
						vwList.get(i).getTelNumber(),
						vwList.get(i).getRegDate(),
						vwList.get(i).getCount());
				
			
		}

		System.out.println();
		pause();
		
	}
	
	

	

	private static void SearchByName() {
		System.out.println("====================================");
		System.out.println("      교육생 이름으로 검색 하기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.print("교육생 이름 : ");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		System.out.println();
		System.out.println();
		System.out.printf("[교육생 번호]\t[교육생 이름]\t[주민번호 뒷자리]\t[전화번호]\t\t[등록일]\t\t[수강(신청) 횟수]\n");
		System.out.println();
		
		VwAllStudentAdminDTO vdto = new VwAllStudentAdminDTO();
		VwAllStudentAdminDAO vdao = new VwAllStudentAdminDAO();
		
		ArrayList<VwAllStudentAdminDTO> vwList = vdao.getName(name);
		for (int i = 0 ; i < vwList.size() ; i++) {
				
				System.out.printf("\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
						vwList.get(i).getStudentSeq(),
						vwList.get(i).getStudentName(),
						vwList.get(i).getIdNumber(),
						vwList.get(i).getTelNumber(),
						vwList.get(i).getRegDate(),
						vwList.get(i).getCount());
				
		
			
		}

		System.out.println();
		pause();
	}

	private static void SearchByIdNum() {
		System.out.println("====================================");
		System.out.println("    교육생 주민번호로 검색 하기 ");
		System.out.println("====================================");		
		System.out.println();
		System.out.print("주민번호 뒷자리: ");
		Scanner scan = new Scanner(System.in);
		String idNum = scan.nextLine();
		System.out.println();
		System.out.println();
		System.out.printf("[교육생 번호]\t[교육생 이름]\t[주민번호 뒷자리]\t[전화번호]\t\t[등록일]\t\t[수강(신청) 횟수]\n");
		System.out.println();
		
		VwAllStudentAdminDTO vdto = new VwAllStudentAdminDTO();
		VwAllStudentAdminDAO vdao = new VwAllStudentAdminDAO();
		
		ArrayList<VwAllStudentAdminDTO> vwList = vdao.getIdNum(idNum);
		for (int i = 0 ; i < vwList.size() ; i++) {
			
				System.out.printf("\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
						vwList.get(i).getStudentSeq(),
						vwList.get(i).getStudentName(),
						vwList.get(i).getIdNumber(),
						vwList.get(i).getTelNumber(),
						vwList.get(i).getRegDate(),
						vwList.get(i).getCount());
				
			
			
		}

		System.out.println();
		pause();
	}

	
	
	
	
	private static void SearchByTel() {
		System.out.println("====================================");
		System.out.println("    교육생 전화번호로 검색 하기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.print("교육생 전화번호 : ");
		Scanner scan = new Scanner(System.in);
		String tel = scan.nextLine();
		System.out.println();
		System.out.println();
		System.out.printf("[교육생 번호]\t[교육생 이름]\t[주민번호 뒷자리]\t[전화번호]\t\t[등록일]\t\t[수강(신청) 횟수]\n");
		System.out.println();

		VwAllStudentAdminDTO vdto = new VwAllStudentAdminDTO();
		VwAllStudentAdminDAO vdao = new VwAllStudentAdminDAO();
		
		ArrayList<VwAllStudentAdminDTO> vwList = vdao.getTel(tel);
		for (int i = 0 ; i < vwList.size() ; i++) {
				
				System.out.printf("\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
						vwList.get(i).getStudentSeq(),
						vwList.get(i).getStudentName(),
						vwList.get(i).getIdNumber(),
						vwList.get(i).getTelNumber(),
						vwList.get(i).getRegDate(),
						vwList.get(i).getCount());
				
			
		}

		System.out.println();
		pause();
	}

	
	
	
	private static void SearchByRegDate() {
		System.out.println("====================================");
		System.out.println("     교육생 등록일로 검색 하기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println("** 날짜 입력시 YYYY-MM-DD 로 입력하시오...");
		System.out.print("교육생 등록일(YYYY-MM-DD) : ");
		Scanner scan = new Scanner(System.in);
		String regDate = scan.nextLine();
		boolean flag = true;
		while (flag) {
			if (regDate.length() == 10 && regDate.indexOf("-")==4 && regDate.lastIndexOf("-")==7 && Integer.parseInt(regDate.substring(5,7)) >= 1 && Integer.parseInt(regDate.substring(5,7)) <= 12 &&	Integer.parseInt(regDate.substring(8)) <= 31) {
				flag = false;	
			} else {
				flag = true;
				System.out.println();
				System.out.println("잘못된 입력입니다. 날짜 입력방식을 다시 확인하고 입력하세요.");
				System.out.println("** 날짜 입력시 YYYY-MM-DD 로 입력하시오...");
				System.out.print("교육생 등록일(YYYY-MM-DD) : ");
				regDate = scan.nextLine();
			}
			
		}
		System.out.println();
		System.out.println();
		System.out.printf("[교육생 번호]\t[교육생 이름]\t[주민번호 뒷자리]\t[전화번호]\t\t[등록일]\t\t[수강(신청) 횟수]\n");
		System.out.println();

		VwAllStudentAdminDTO vdto = new VwAllStudentAdminDTO();
		VwAllStudentAdminDAO vdao = new VwAllStudentAdminDAO();
		
		ArrayList<VwAllStudentAdminDTO> vwList = vdao.getRegDate(regDate);
		for (int i = 0 ; i < vwList.size() ; i++) {
				
				System.out.printf("\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
						vwList.get(i).getStudentSeq(),
						vwList.get(i).getStudentName(),
						vwList.get(i).getIdNumber(),
						vwList.get(i).getTelNumber(),
						vwList.get(i).getRegDate(),
						vwList.get(i).getCount());
			
		}

		System.out.println();
		pause();
		
		
	}
	
	
	

	private static void SearchByCount() {
		System.out.println("====================================");
		System.out.println("   교육생 수강신청횟수로 검색 하기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.print("수강신청 횟수 : ");
		Scanner scan = new Scanner(System.in);
		String count = scan.nextLine();
		System.out.println();
		System.out.println();
		System.out.printf("[교육생 번호]\t[교육생 이름]\t[주민번호 뒷자리]\t[전화번호]\t\t[등록일]\t\t[수강(신청) 횟수]\n");
		System.out.println();
		
		VwAllStudentAdminDTO vdto = new VwAllStudentAdminDTO();
		VwAllStudentAdminDAO vdao = new VwAllStudentAdminDAO();
		
		ArrayList<VwAllStudentAdminDTO> vwList = vdao.getCount(count);
		for (int i = 0 ; i < vwList.size() ; i++) {
				
				System.out.printf("\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
						vwList.get(i).getStudentSeq(),
						vwList.get(i).getStudentName(),
						vwList.get(i).getIdNumber(),
						vwList.get(i).getTelNumber(),
						vwList.get(i).getRegDate(),
						vwList.get(i).getCount());
				
		}

		System.out.println();
		pause();
		
	}
	
	
	
	
	private static void pause() {
		
		System.out.println("** 이전 단계로 돌아가시려면 엔터를 입력하세요. **");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		intro();
		
	}
	
	

}
