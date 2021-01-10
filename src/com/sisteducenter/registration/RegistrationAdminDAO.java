package com.sisteducenter.registration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sisteducenter.start.DBUtil;

/**
 * 
 * @author 이준오
 *
 */
public class RegistrationAdminDAO {

	private Connection conn;
	private Statement stat; 
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	public RegistrationAdminDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param rdto 추가 할 교육생 수강 정보
	 * @return 처리 결과
	 */
	public int add(RegistrationAdminDTO rdto) {
		try {

			
			
			String sql = "{ call procAddRegistration(?)}";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, rdto.getOpenCourSeq());
			
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
	 * @param rdto 수정 할 교육생 수강 정보
	 * @return 처리 결과
	 */
	public int edit(RegistrationAdminDTO rdto) {
		
		try {

			String sql = "{ call procUpRegStudent(?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, rdto.getOpenCourSeq());
			cstat.setString(2, rdto.getStudentSeq());
					
			System.out.println();
			
			return cstat.executeUpdate();
			

		} catch (Exception e) {
			System.out.println("RegistrationDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/**
	 * 
	 * @param rdto 수정 할 교육생의 수강상태
	 * @return 처리 결과
	 */
	public int stateEdit(RegistrationAdminDTO rdto) {
		
		try {

			String sql = "{ call procUpdateRegistration(?,?,?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, rdto.getStudentSeq());
			cstat.setString(2, rdto.getRegState());
			cstat.setString(3, rdto.getDate());
			
			
			String sql2 = "update tblRegistration set completiondate = ?, faildate = ? where studentSeq = ? ";
			
			CallableStatement cstat2 = null;
			
			cstat2 = conn.prepareCall(sql2);
			
			cstat2.setString(1, null);
			cstat2.setString(2, null);
			cstat2.setString(3, rdto.getStudentSeq());
			
			cstat2.executeQuery();
			
			cstat2.close();
			System.out.println();
			
			return cstat.executeUpdate();
			
			
			

		} catch (Exception e) {
			System.out.println("RegistrationDAO.stateEdit()");
			e.printStackTrace();
		}
		
		return 0;
	}


}
