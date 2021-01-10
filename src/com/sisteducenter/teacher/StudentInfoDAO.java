package com.sisteducenter.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 특정 과목의 학생정보를 데이터베이스로부터 가져오는 DAO 클래스입니다.
 * @author 황원준
 *
 */
public class StudentInfoDAO {
	
	private Connection conn;
	private Statement stat; 		 //매개변수 X
	private PreparedStatement pstat; //매개변수 O
	private CallableStatement cstat;
	private ResultSet rs;
	
	public StudentInfoDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 특정 과목의 학생정보를 데이터베이스로부터 가져오는 메서드입니다.
	 * @param subSeq : 개설과목번호입니다.
	 * @return 학생정보들이 담긴 ArrayList를 반환합니다.
	 */	
	public ArrayList<StudentInfo1DTO> stuInfo(String subSeq) {
		
		
		try {
			String sql = "{ call procStudentInfo(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, subSeq);
			
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<StudentInfo1DTO> result = new ArrayList<StudentInfo1DTO>();
			
			
			while(rs.next()) {
				
				StudentInfo1DTO sdto = new StudentInfo1DTO();
				
				sdto.setOs_seq(rs.getString("os_seq"));
				sdto.setS_name(rs.getString("s_name"));
				sdto.setS_tel(rs.getString("s_tel"));
				sdto.setReg_regdate(rs.getString("reg_regdate"));
				sdto.setState(rs.getString("stu_state"));
				sdto.setS_subjectName(rs.getString("s_subjectname"));
				
				result.add(sdto);
			}
			return result;
			
		} catch (Exception e) {
			System.out.println("StudentInfoDAO.stuInfo()");
			e.printStackTrace();
		}
		
		return null;
	}




}
