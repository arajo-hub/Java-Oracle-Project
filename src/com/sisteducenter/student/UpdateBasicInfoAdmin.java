package com.sisteducenter.student;

import java.util.Scanner;

import com.sisteducenter.registration.RegistrationAdminDAO;
import com.sisteducenter.registration.RegistrationAdminDTO;

/**
 * 
 * @author 이준오
 * @for 교육생 기초정보 수정
 *
 */
public class UpdateBasicInfoAdmin {

	/**
	 * 관리자의 교육생 기초정보 수정 화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		
		System.out.println();
		System.out.println("====================================");
		System.out.println("        교육생 기초정보 수정");
		System.out.println("====================================");
		System.out.println();
		System.out.println("해당 항목을 입력하세요.");
		System.out.println();
		update();
		
	}
	

	private static void update() {

		Scanner scan = new Scanner(System.in);
		
		System.out.print("* 교육생번호 입력 : ");
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
				break;
				
			} else if (sel.toUpperCase().equals("N")) {
				
				flag = false;
				update();
				
			} else {
				
				flag = true;
				
				System.out.println("올바르지 않은 입력입니다. 다시 입력하세요.");
				System.out.println("계속 진행하기(Y)");
				System.out.println("교육생번호 다시 선택하기(N)");
				System.out.println();
				System.out.print("입력(Y/N) : ");
				sel = scan.nextLine();
				
			}
			
		}
		
		System.out.println("수정 내용을 입력하세요.");
		System.out.println();
		System.out.print("* 이름 : ");
		String name = scan.nextLine();
		System.out.print("* 주민번호 뒷자리 : ");
		String idNum = scan.nextLine();
		System.out.print("* 전화번호 : ");
		String tel = scan.nextLine();
		System.out.println("취업여부는 Y, N 중 하나만 입력이 가능합니다.");
		System.out.print("* 취업여부(Y/N) : ");
		String employment = scan.nextLine().toUpperCase();
		boolean flag2 = true;
		while (flag2) {
			if (employment.equals("Y")) {
				flag2 = false;
			} else if (employment.equals("N")) {
				flag2 = false;
			} else {
				flag2 = true;
				System.out.println();
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
				System.out.println();
				System.out.println("취업여부는 Y, N 중 하나만 입력이 가능합니다.");
				System.out.print("* 취업여부(Y/N) : ");
				employment = scan.nextLine().toUpperCase();
			}
		}
		System.out.print("* 개설과정번호 : ");
		String openCourSeq = scan.nextLine();
		
		
		StudentAdminDTO sdto = new StudentAdminDTO();
		StudentAdminDAO sdao = new StudentAdminDAO();
		RegistrationAdminDTO rdto = new RegistrationAdminDTO();
		RegistrationAdminDAO rdao = new RegistrationAdminDAO();
		
		sdto.setSeq(seq);
		sdto.setName(name);
		sdto.setIdNum(idNum);
		sdto.setTel(tel);
		sdto.setEmployment(employment.toUpperCase());
		
		rdto.setStudentSeq(seq);
		rdto.setOpenCourSeq(openCourSeq);
		
					
		int result1 = sdao.edit(sdto);
		int result2 = rdao.edit(rdto);
		
		if (result1 > 0 && result2 > 0) {
			System.out.println("정보 수정이 성공 했습니다.");
		} else {
			System.out.println("정보 수정이 실패 했습니다.");
		}
		
		
		pause();
		
		
		
	}

	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("이전 단계로 돌아가시려면 엔터를 입력하세요.");
		scan.nextLine();
		UpdateStudentAdmin.intro();
		
		
	}
	
	
	
	

}
