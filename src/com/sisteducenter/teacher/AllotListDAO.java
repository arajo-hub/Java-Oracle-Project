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
 * 개설과정번호로 배점데이터를 가져오는 DAO입니다.
 * @author 황원준
 *
 */
public class AllotListDAO {
	private Connection conn;
	private Statement stat; 		 //매개변수 X
	private PreparedStatement pstat; //매개변수 O
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	public AllotListDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 데이터베이스에서 배점정보를 가져오는 메서드입니다.
	 * @param couSeq
	 * @return 개설과정정보가 담긴 ArrayList를 반환합니다.
	 */
	public ArrayList<AllotListDTO> alList(String couSeq) {
		
		try {
			conn = DBUtil.open();
						
			String sql = "{ call procCheckScoreAllot(?, ?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, couSeq);
			
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
	
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			
			ArrayList<AllotListDTO> result5 = new ArrayList<AllotListDTO>();
			
			while(rs.next()) {
				AllotListDTO aldto = new AllotListDTO();
								
				aldto.setOs_seq(rs.getString("os_seq"));
				aldto.setC_name(rs.getString("c_name"));
				aldto.setOc_startdate(rs.getString("oc_startdate"));
				aldto.setOc_enddate(rs.getString("oc_enddate"));
				aldto.setOc_lectureroomseq(rs.getString("oc_lectureroomseq"));
				aldto.setS_subjectname(rs.getString("s_subjectname"));
				aldto.setOs_startdate(rs.getString("os_startdate"));
				aldto.setOs_enddate(rs.getString("os_enddate"));
				aldto.setB_title(rs.getString("b_title"));
				aldto.setSa_attendanceallot(rs.getString("sa_attendanceallot"));
				aldto.setSa_handwritingallot(rs.getString("sa_handwritingallot"));
				aldto.setSa_practiceallot(rs.getString("sa_practiceallot"));
				
				result5.add(aldto);
			}
			return result5;
			
		} catch (Exception e) {
			System.out.println("AllotListDAO.alList()");
			e.printStackTrace();
		}
		
		return null;
	}

}
