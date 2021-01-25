package com.sisteducenter.student;

import java.util.Scanner;

/**
 * 교육생 정보 수정을 담당하는 클래스입니다.
 * @author 조아라
 *
 */
public class EditInfo {
	
	static Scanner scan=new Scanner(System.in);

	/**
	 * 교육생 개인정보를 나타내고 수정하는 화면을 나타내는 메서드입니다.
	 * @param dto : 교육생 개인정보입니다.
	 */
	public static void editPersonalInfo(StudentDTO dto) {
		/*
		 * 개인정보
		 * 
		 * 1. 교육생번호
		 * 2. 교육생명
		 * 3. 교육생 주민번호뒷자리
		 * 4. 전화번호
		 * 5. 취업여부
		 * 
		 * */
		
		showHeader();
		
		System.out.println("\t\t\t\t* 수정을 하지 않을 정보는 엔터를 입력해주세요.");
		System.out.print("\t\t\t\t수정할 이름 : ");
		String name=scan.nextLine();
		
		if (name.equals("")) {
			name=dto.getName();
		}
		
		System.out.print("\t\t\t\t수정할 전화번호 : ");
		String tel=scan.nextLine();
		
		if (tel.equals("")) {
			tel=dto.getTel();
		}
		
		System.out.print("\t\t\t\t수정할 취업여부(Y|N) : ");
		String employment=scan.nextLine();
		
		if (employment.equals("")) {
			employment=dto.getEmployment();
		}
		
		StudentDTO dto2=new StudentDTO();
		
		dto2.setSeq(dto.getSeq());
		dto2.setName(name);
		dto2.setIdNum(dto.getIdNum());
		dto2.setTel(tel);
		dto2.setEmployment(employment);
		
		int result=StudentDAO.editPersonalInfo(dto2);
		
		if (result>0) {
			System.out.println("\t\t\t\t정보를 수정했습니다.");
		}else {
			System.out.println("\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t정보를 수정하지 못했습니다.");
		}
		ShowInfo.showPersonalInfo(dto.getSeq());
		
	}
	
	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t개인정보수정\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
