package com.sisteducenter.registration;

import java.util.Scanner;

import com.sisteducenter.student.StudentAdminDAO;
import com.sisteducenter.student.StudentAdminDTO;
import com.sisteducenter.student.UpdateStudentAdmin;


/**
 * 
 * @author 이준오
 * @for 교육생 과정 수강, 수료 및 중도 탈락 처리
 *
 */
public class UpdateRegistrationInfoAdmin {

	/**
	 * 교육생 과정 수강, 수료 및 중도 탈락 처리 화면을 출력하는 메서드입니다.
	 */
	public static void main(String[] args) {
	
	}

	public static void intro() {
		
		System.out.println();
		System.out.println("=========================================");
		System.out.println(" 교육생 과정 수강, 수료 및 중도 탈락 처리");
		System.out.println("=========================================");
		System.out.println();
		System.out.println("해당 항목을 입력하세요.");
		System.out.println();
		update();
		
	}

	private static void update() {
		
		try {
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
					
				}//if
			
			}//while
			
			System.out.println("수정 내용을 입력하세요.");
			System.out.println();
			System.out.println(" *** 수강 상태는 수료(P), 진행중(Y), 중도포기(G) 중 하나를 입력하세요.");
			System.out.print("* 수강 상태(P, Y, G) : ");
			String regState = scan.nextLine().toUpperCase();
			boolean flag2 = true;
			while (flag2) {
				if (regState.equals("P")) {
					flag2 = false;
				} else if (regState.equals("Y")) {
					flag2 = false;
				} else if (regState.equals("G")) {
					flag2 = false;
				} else {
					flag2 = true;
					System.out.println();
					System.out.println("잘못된 입력입니다. 다시 입력하세요.");
					System.out.println();
					System.out.println(" *** 수강 상태는 수료(P), 진행중(Y), 중도포기(G) 중 하나를 입력하세요.");
					System.out.print("* 수강 상태(P, Y, G) : ");
					regState = scan.nextLine().toUpperCase();
				}
			}
			System.out.println();
			System.out.println("진행중(Y)인 경우, 현재일을 입력하시오.");
			System.out.print("* 날짜(YYYY-MM-DD) : ");
			String date = scan.nextLine();
			
			
			
			StudentAdminDTO sdto = new StudentAdminDTO();
			StudentAdminDAO sdao = new StudentAdminDAO();
			RegistrationAdminDTO rdto = new RegistrationAdminDTO();
			RegistrationAdminDAO rdao = new RegistrationAdminDAO();
			
			
			rdto.setStudentSeq(seq);
			rdto.setRegState(regState.toUpperCase());
			rdto.setDate(date); 
						
			
			
			int result = rdao.stateEdit(rdto);
			
			if (result > 0) {
				System.out.println("정보 수정이 성공 했습니다.");
			} else {
				System.out.println("정보 수정이 실패 했습니다.");
			}
						
			pause();
			
			
			
		} catch (Exception e) {
			System.out.println("UpdateRegistrationInfo.update()");
			e.printStackTrace();
		} 
			
		
		
	}

	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("이전 단계로 돌아가시려면 엔터를 입력하세요.");
		scan.nextLine();
		UpdateStudentAdmin.intro();
		
		
	}
	
	
	

}
