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
 * 종료된 과목들을 데이터베이스에서 가져오는 DAO 클래스입니다.
 * @author 황원준
 *
 */
public class EndSubListDAO {
	
	private Connection conn;
	private Statement stat; 		 //매개변수 X
	private PreparedStatement pstat; //매개변수 O
	private CallableStatement cstat;
	private ResultSet rs;
	
	public EndSubListDAO() {
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 데이터베이스에서 강의가 끝난 과목들을 가져오는 메서드입니다.
	 * @param teacherSeq : 교사번호입니다.
	 * @return 강의가 끝난 과목정보들이 담긴 ArrayList를 반환합니다.
	 */
	public ArrayList<EndSubListDTO> eslList(String teacherSeq) {
		
		
		try {
			
			conn = DBUtil.open();
			String sql = "{ call EndSubList(?, ?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, teacherSeq);
			
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
	
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			
			ArrayList<EndSubListDTO> result4 = new ArrayList<EndSubListDTO>();
			
			while(rs.next()) {
				
				EndSubListDTO esldto = new EndSubListDTO();
				
				esldto.setOs_seq(rs.getString("os_seq"));
				esldto.setS_subjectname(rs.getString("s_subjectname"));
				esldto.setOs_startdate(rs.getString("os_startdate"));
				esldto.setOs_enddate(rs.getString("os_enddate"));
				esldto.setSub_state(rs.getString("sub_state"));
				esldto.setOc_seq(rs.getString("oc_seq"));  ///// TODO 여기 이 부분
				esldto.setC_name(rs.getString("c_name"));
				esldto.setOc_startdate(rs.getString("oc_startdate"));
				esldto.setOc_enddate(rs.getString("oc_enddate"));
				esldto.setStu_count(rs.getString("stu_count"));
				esldto.setCourse_state(rs.getString("course_state"));
				esldto.setT_name(rs.getString("t_name"));
				//esldto.setAvg_score(rs.getString("avg_score"));
				
				result4.add(esldto);
			}
			
			return result4;
			
		} catch (Exception e) {
			System.out.println("EndSubListDAO.eslList()");
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return null;
	}

}
