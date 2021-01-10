package com.sisteducenter.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sisteducenter.start.DBUtil;

/**
 * 배점을 입력받아 데이터베이스에 저장하는 DAO 클래스입니다.
 * @author 황원준
 *
 */
public class InputAllotDAO {
	private Connection conn;
	private Statement stat; 		 //매개변수 X
	private PreparedStatement pstat; //매개변수 O
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	
	public InputAllotDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 배점을 데이터베이스에 입력하는 매서드입니다.
	 * @param iadto : 배점정보를 담고 있는 DTO입니다.
	 * @return 배점을 데이터베이스에 입력한 결과를 반환합니다.
	 */
	public int inputAllot(InputAllotDTO iadto) {
		
		try {
			
			
			String sql = "{ call procAddScoreAllot(?, ?, ?, ?, ?, to_date(?, 'yyyy-mm-dd')) }";
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, iadto.getSub());
			pstat.setString(2, iadto.getOpenCourse());
			pstat.setString(3, iadto.getWrite());
			pstat.setString(4, iadto.getPractice());
			pstat.setString(5, iadto.getAttendance());
			pstat.setString(6, iadto.getExamDate());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("InputAllotDAO.inputAllot()");
			e.printStackTrace();
		}
			
		
		return 0;
	}

}
