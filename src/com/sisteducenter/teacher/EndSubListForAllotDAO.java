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
 * 종료된 과목의 배점정보를 데이터베이스로부터 가져오는 DAO 클래스입니다.
 * @author 황원준
 *
 */
public class EndSubListForAllotDAO {
	
	private Connection conn;
	private Statement stat; 		 //매개변수 X
	private PreparedStatement pstat; //매개변수 O
	private CallableStatement cstat;
	private ResultSet rs;
	
	public EndSubListForAllotDAO() {
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 종료된 과목의 배점정보를 데이터베이스로부터 가져오는 메서드입니다.
	 * @param teacherSeq : 교사번호
	 * @return 종료된 과목의 배점정보들이 담긴 ArrayList를 반환합니다.
	 */
	public ArrayList<EndSubListForAllotDTO> list(String teacherSeq) {
		
		try {
			conn = DBUtil.open();
			
			String sql = "{ call EndSubListForAllot(?, ?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, teacherSeq);
			
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
	
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<EndSubListForAllotDTO> result6 = new ArrayList<EndSubListForAllotDTO>();
			
			while(rs.next()) {
				
				EndSubListForAllotDTO eslfadto = new EndSubListForAllotDTO();
						
				
				eslfadto.setOc_seq(rs.getString("oc_seq"));
				eslfadto.setS_subjectname(rs.getString("s_subjectname"));
				eslfadto.setOs_startdate(rs.getString("os_startdate"));
				eslfadto.setOs_enddate(rs.getString("os_enddate"));
				eslfadto.setSub_state(rs.getString("sub_state"));
				eslfadto.setC_name(rs.getString("c_name"));
				eslfadto.setOc_startdate(rs.getString("oc_startdate"));
				eslfadto.setOc_enddate(rs.getString("oc_enddate"));
				eslfadto.setStu_count(rs.getString("stu_count"));
				eslfadto.setCourse_state(rs.getString("course_state"));

				result6.add(eslfadto);
			}
			return result6;
		} catch (Exception e) {
			System.out.println("EndSubListForAllotDAO.eblfaList()");
			e.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	
}
