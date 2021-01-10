package com.sisteducenter.start;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * DBUtil. 데이터베이스 연결 클래스입니다.
 * @author 1조
 *
 */

public class DBUtil {

	private static Connection conn;
	
	/**
	 * 데이터 베이스 연결 메소드입니다.
	 * @return 연결된 Connection 객체를 반환합니다.
	 */
	public static Connection open() {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="hr";
		String pw="java1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			return conn; // 성공하면 접속된 Connection 반환
		} catch (Exception e) {
			System.out.println("DBUtil.open()");
			e.printStackTrace();
		}
		
		return null; // 실패하면 null 반환
	}
	
	/**
	 * 데이터 베이스 연결 메소드입니다.
	 * @param server 접속할 서버 주소입니다.
	 * @param id 접속할 계정명입니다.
	 * @param pw 접속할 비밀번호입니다.
	 * @return 연결된 Connection 객체를 반환합니다.
	 */
	public static Connection open(String server, String id, String pw) {
		String url="jdbc:oracle:thin:@"+server+":1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			return conn; // 성공하면 접속된 Connection 반환
		} catch (Exception e) {
			System.out.println("DBUtil.open()");
			e.printStackTrace();
		}
		
		return null; // 실패하면 null 반환
	}
	
}
