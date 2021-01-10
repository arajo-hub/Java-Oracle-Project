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
 * 현재 진행중인 과정의 평가정보를 데이터베이스로부터 가져오는 DAO 입니다.
 * @author 황원준
 */
public class EvaluationCurrentDAO {
	private Connection conn;
	private Statement stat; 		 //매개변수 X
	private PreparedStatement pstat; //매개변수 O
	private CallableStatement cstat;
	private ResultSet rs;
	
	public EvaluationCurrentDAO() {
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 진행중인 과정의 평가정보를 데이터베이스로부터 가져오는 메서드입니다.
	 * @param teacherSeq : 교사번호
	 * @return 진행중인 과정의 평가정보들을 담고 있는 ArrayList를 반환합니다.
	 */
	public ArrayList<EvaluationCurrentDTO> ecList(String teacherSeq) {
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procCheckEvalCurrent(?, ?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, teacherSeq);
			
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
	
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<EvaluationCurrentDTO> result2 = new ArrayList<EvaluationCurrentDTO>();
			
			while(rs.next()) {
				
				//System.out.println("ecdto");
				
				EvaluationCurrentDTO ecdto = new EvaluationCurrentDTO();
				
				ecdto.setOs_seq(rs.getString("os_seq"));
				ecdto.setS_subjectname(rs.getString("s_subjectname"));
				ecdto.setOs_startdate(rs.getString("os_startdate"));
				ecdto.setOs_enddate(rs.getString("os_enddate"));
				ecdto.setSub_state(rs.getString("sub_state"));
				ecdto.setC_name(rs.getString("c_name"));
				ecdto.setOc_startdate(rs.getString("oc_startdate"));
				ecdto.setOc_enddate(rs.getString("oc_enddate"));
				ecdto.setStu_count(rs.getString("stu_count"));
				ecdto.setCourse_state(rs.getString("course_state"));
				ecdto.setT_name(rs.getString("t_name"));
				ecdto.setAvg_score(rs.getString("avg_score"));
	
				result2.add(ecdto);
				
			}
			return result2;
			
		} catch (Exception e) {
			System.out.println("EvaluationCurrentDAO.ecList()");
			e.printStackTrace();
		}
		
		
		return null;
	}

}
