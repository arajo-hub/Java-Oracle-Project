package com.sisteducenter.start;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import com.sisteducenter.admin.AdminMain;
import com.sisteducenter.student.studentMain;
import com.sisteducenter.teacher.TeacherMain;

import oracle.jdbc.OracleTypes;

/**
 * 교육생, 교사, 관리자의 로그인 기능을 담당하는 클래스입니다.
 * @author 1조
 *
 */
public class Login {
	
	private static Connection conn = null;
	private static CallableStatement cstat=null;
	private static Scanner scan;
	
	static {
		scan=new Scanner(System.in);
	}
	
	public static void login(String sel) {
		
		boolean loop=true;
		boolean goBack=false;
		HashMap<String, String> auth=new HashMap<String, String>();
		String seq="";
		String idNum="";
		
		showHeader();
		
		while(loop) {
			// 1. 로그인 시도
			// - 번호 입력
			// - 비밀번호 입력
			
			auth=insertInfo(sel);
			
			try {
				
				// 2. 데이터베이스 연결
				
				conn=DBUtil.open();
				
				// 3. 입력받은 정보로 쿼리 작성
				
				String sql="{ call procIsValid(?, ?, ?, ?) }";
				cstat=conn.prepareCall(sql);

				cstat.setString(1, sel);
				// 인증용으로 받은 고유번호와 비밀번호를 꺼낸다.
				
				for ( String key : auth.keySet() ) {
					seq=key; // 고유번호
					idNum=auth.get(key); // 비밀번호
				}
				
				if (!(seq.equals("") && idNum.equals(""))) {
					cstat.setString(2, seq); // 고유번호
					cstat.setString(3, idNum); // 비밀번호(주민번호뒷자리)
				}else { // 뒤로 가기 위해 고유번호와 비밀번호에 모두 엔터가 입력된 상태라면.
					goBack=true; // 뒤로 간다는 의사를 표시하는 변수를 true로 바꾸고.
				}
				
				// goBack이 true라면 while문을 탈출한다.
				if (goBack) {
					break;
				}
				
				cstat.registerOutParameter(4, OracleTypes.VARCHAR);
				// 결과를 나타내는 변수로, 이 변수의 값에 따라 예외처리를 한다.
				
				// 4. 쿼리 실행
				cstat.executeQuery();
			
				// 5. 프로시저로부터 결과값 반환.
				String result=cstat.getString(4);
				// 0 : 성공
				// 1 : 올바르지 않은 정보
				// 2 : 존재하지 않는 정보
				
				if (result.equals("0")) { // 로그인 성공
					cstat.close(); // 로그인에 성공했으니 자원 해제.
					
					if (sel.equals("1")) { // 교육생 로그인 선택
						studentMain.showMain(seq);
					}else if (sel.equals("2")) { // 교사 로그인 선택
						TeacherMain.showMain(seq);
					}else { // 관리자 로그인 선택
						AdminMain.showMain();
					}
					goBack=false;
					loop=false;
					
				}else if (result.equals("1")) {
					
					showHeader();
					System.out.println("\t\t\t\t\t[!!!]");
					System.out.println("\t\t\t\t\t올바르지 않은 정보입니다.");
					pause();
					
				}else if (result.equals("2")) {
					showHeader();
					
					System.out.println("\t\t\t\t\t[!!!]");
					System.out.println("\t\t\t\t\t존재하지 않는 정보입니다.");
					pause();
					
				}
				
				// 6. 틀리면 다시 처음으로.
				
			} catch (Exception e) {
				
				System.out.println("Login.login()");
				e.printStackTrace();
				
			}
		}
		
		// loop를 탈출하는 경우는 두 가지.
		// 1. 로그인 성공한 경우.
		// 2. 뒤로 가기 위해서 고유번호와 비밀번호(주민번호뒷자리)를 전부 엔터로 입력한 경우.
		
		if (goBack){
		// 2. 뒤로 가고 싶어서 고유번호와 비밀번호(주민번호뒷자리)를 전부 엔터로 입력했을 때.
			try {
				cstat.close(); // 이 화면을 떠나는 것이므로 자원 해제.
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
			Start.main(null);
		}else {
			// 1. 로그인 성공한 경우는
			// 그냥 로그인 성공한 채로 이 메서드에서는 더 이상 아무 것도 수행하지 않는다.
		}
		
		
	}
	
	/**
	 * 상단에 표시되는 헤더입니다.
	 */
	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t로  그  인\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static HashMap<String, String> insertInfo(String sel) {
		String who = "";
		switch (sel) {
		case "1":
			who="교육생";
			break;
		case "2":
			who="교사";
			break;
		case "3":
			who="관리자";
			break;
		}
		HashMap<String, String> auth=new HashMap<String, String>();
		
		System.out.println("\t\t\t\t\t* 입력없이 엔터를 입력하면 뒤로 돌아갑니다.");
		System.out.printf("\t\t\t\t\t* %s번호 입력 : ", who);
		String seq=scan.nextLine();
		System.out.print("\t\t\t\t\t* 비밀번호 입력 : ");
		String pw=scan.nextLine();
		
		auth.put(seq, pw);
		
		return auth;
	}
	
	private static void pause() {
		System.out.println("\t\t\t\t\t다시 시도하려면 엔터를 눌러주세요.");
		scan.nextLine();
	}

}
