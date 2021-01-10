package com.sisteducenter.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.sisteducenter.admin.StudentManagingMainAdmin;
import com.sisteducenter.start.DBUtil;


/**
 * 
 * @author 이준오
 * @for 교육생 정보 삭제하기
 *
 */
public class DeleteStudentAdmin {

	/**
	 * 교육생 정보를 삭제하는 화면을 나타내는 메서드입니다.
	 */
	public static void intro() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println();
		System.out.println();
		System.out.println("====================================");
		System.out.println("        교육생 정보 삭제하기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.println("1. 교육생 번호로 삭제하기");
		System.out.println();
		System.out.println("0. 이전메뉴");
		System.out.println();
		System.out.println();
		System.out.print("번호 입력 : ");
		String sel = scan.nextLine();

		boolean flag = true;
		
		while (flag) {
			if (sel.equals("1")) {
				flag = false;
				delete();
				
			} else if (sel.equals("0")) {
				flag = false;
				StudentManagingMainAdmin.intro();
				
			} else {
				flag = true;
				System.out.println("**잘못된 번호입니다. 다시 입력하세요.**");
				System.out.println("========================================");
				System.out.println();
				System.out.println();
				System.out.println("1. 교육생 번호로 삭제하기");
				System.out.println();
				System.out.println("0. 이전메뉴");
				System.out.println();
				System.out.println();
				System.out.print("번호 입력 : ");
				sel = scan.nextLine();
			
			}
		}
		
	}

	private static void delete() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("====================================");
		System.out.println("        교육생 정보 삭제하기 ");
		System.out.println("====================================");
		System.out.println();
		System.out.println();
		System.out.print("교육생 번호 입력 : ");
		
		int seq = Integer.parseInt(scan.nextLine());
		

		Connection conn = null;
		PreparedStatement pstat1 = null;
		PreparedStatement pstat2 = null;
		PreparedStatement pstat3 = null;
		
		
		try {
			
			conn = DBUtil.open();
			
			
			String sql1 = "delete from tbllectureevaluation where studentSeq = ?";
			String sql2 = "delete from tblRegistration where studentSeq = ?";
			String sql3 = "delete from tblStudent where seq = ?";
			
			pstat1 = conn.prepareStatement(sql1);
			pstat1.setInt(1, seq);
			
			pstat2 = conn.prepareStatement(sql2);
			pstat2.setInt(1, seq);
			
			pstat3 = conn.prepareStatement(sql3);
			pstat3.setInt(1, seq);
			
			
			pstat1.executeQuery();
			System.out.println("교육생 데이터 삭제 완료. ( 1 / 3 )");
			
			pstat2.executeQuery();
			System.out.println("교육생 데이터 삭제 완료. ( 2 / 3 )");
						
			pstat3.executeQuery();
			System.out.println("교육생 데이터 삭제 완료. ( 3 / 3 )");
				
			
			pstat3.close();
			pstat2.close();
			pstat1.close();
			conn.close();
			
			pause();
			
		} catch (Exception e) {
			System.out.println("오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
		
		
	}
	private static void pause() {
		
		System.out.println("** 이전 단계로 돌아가시려면 엔터를 입력하세요. **");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		StudentManagingMainAdmin.intro();
		
	}
	

}
