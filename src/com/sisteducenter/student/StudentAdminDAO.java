package com.sisteducenter.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sisteducenter.start.DBUtil;

/**
 * 관리자가 학생을 등록 혹은 수정하기 위한 DAO 클래스입니다.
 * @author 이준오
 *
 */
public class StudentAdminDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public StudentAdminDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param sdto 추가 할 교육생 기본 정보
	 * @return 처리 결과
	 */
	public int add(StudentAdminDTO sdto) {
		try {

						
			String sql = "{ call procAddStudent(?,?,?,?)}";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, sdto.getName());
			cstat.setString(2, sdto.getIdNum());
			cstat.setString(3, sdto.getTel());
			cstat.setString(4, sdto.getEmployment());
			
			System.out.println();
			
			
			return cstat.executeUpdate();
			
			

		} catch (Exception e) {
			System.out.println("StudentBasicInfo.main()");
			e.printStackTrace();
		}
		return 0;
		
	}

	/**
	 * 
	 * @param sdto 수정 할 교육생 기본 정보
	 * @return 처리 결과
	 */
	public int edit(StudentAdminDTO sdto) {

		try {
			String sql = "{ call procUpdateStudent(?,?,?,?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, sdto.getSeq());
			cstat.setString(2, sdto.getName());
			cstat.setString(3, sdto.getIdNum());
			cstat.setString(4, sdto.getTel());
			cstat.setString(5, sdto.getEmployment());

			System.out.println();
			
			
			return cstat.executeUpdate();
			

		} catch (Exception e) {
			System.out.println("StudentDAO.edit()");
			e.printStackTrace();
		}
				
		return 0;

		
	}


	

	
	


}
