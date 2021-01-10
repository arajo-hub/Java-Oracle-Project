package com.sisteducenter.student;

import java.util.Scanner;

import com.sisteducenter.registration.RegistrationAdminDAO;
import com.sisteducenter.registration.RegistrationAdminDTO;

/**
 * 
 * @author 이준오
 * @for 교육생 정보 등록하기
 *
 */
public class CreateStudentInfoAdmin {

	/**
	 * 교육생 정보를 등록하는 화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("====================================");
		System.out.println("        교육생 정보 등록하기");
		System.out.println("====================================");
		System.out.println();
		System.out.println("해당 항목을 입력하세요.");
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
		boolean flag = true;
		while (flag) {
			if (employment.equals("Y")) {
				flag = false;
			} else if (employment.equals("N")) {
				flag = false;
			} else {
				flag = true;
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
		
		StudentAdminDAO sdao = new StudentAdminDAO();
		StudentAdminDTO sdto = new StudentAdminDTO();
		RegistrationAdminDAO rdao = new RegistrationAdminDAO(); 
		RegistrationAdminDTO rdto = new RegistrationAdminDTO();
		
		sdto.setName(name);
		sdto.setIdNum(idNum);
		sdto.setTel(tel);
		sdto.setEmployment(employment);
		
		
		rdto.setOpenCourSeq(openCourSeq);
		
		int result1 = sdao.add(sdto);
		int result2 = rdao.add(rdto);
		
		if (result1 > 0 && result2 > 0) {
			System.out.println("정보 등록이 성공 했습니다.");
		} else {
			System.out.println("정보 등록이 실패 했습니다.");
		}
		pause();
		
	}


	private static void pause() {
		
		System.out.println("이전 단계로 돌아가시려면 엔터를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		CreateStudentAdmin.addStudentIntro();
		
	}


}
