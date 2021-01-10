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
 * 과거 진행했던 과정의 강의평가정보를 가져오는 DAO 클래스입니다.
 * @author 황원준
 *
 */
public class EvaluationPastDAO {
	private Connection conn;
	private Statement stat; 		 //매개변수 X
	private PreparedStatement pstat; //매개변수 O
	private CallableStatement cstat;
	private ResultSet rs;
	
	public EvaluationPastDAO() {
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 과거 진행했던 과정의 강의평가정보를 가져오는 메서드입니다.
	 * @param teacherSeq : 교사번호
	 * @return 과거 진행했던 과정의 강의평가정보를 담은 ArrayList를 반환합니다.
	 */
	public ArrayList<EvaluationPastDTO> epList(String teacherSeq) {
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "{ call procCheckEvalEnd(?, ?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setInt(1, Integer.parseInt(teacherSeq));
			
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
	
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<EvaluationPastDTO> result3 = new ArrayList<EvaluationPastDTO>();
			
			while(rs.next()) {
				
				EvaluationPastDTO epdto = new EvaluationPastDTO();
				
				epdto.setOs_seq(rs.getString("os_seq"));
				epdto.setS_subjectname(rs.getString("s_subjectname"));
				epdto.setOs_startdate(rs.getString("os_startdate"));
				epdto.setOs_enddate(rs.getString("os_enddate"));
				epdto.setSub_state(rs.getString("sub_state"));
				epdto.setC_name(rs.getString("c_name"));
				epdto.setOc_startdate(rs.getString("oc_startdate"));
				epdto.setOc_enddate(rs.getString("oc_enddate"));
				epdto.setStu_count(rs.getString("stu_count"));
				epdto.setCourse_state(rs.getString("course_state"));
				epdto.setT_name(rs.getString("t_name"));
				epdto.setAvg_score(rs.getString("avg_score"));
	
				result3.add(epdto);
				
			}
			return result3;
			
		} catch (Exception e) {
			System.out.println("EvaluationPastDAO.epList()");
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}
}
