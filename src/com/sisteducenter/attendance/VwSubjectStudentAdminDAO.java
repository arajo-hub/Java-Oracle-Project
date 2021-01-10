package com.sisteducenter.attendance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

import oracle.jdbc.internal.OracleTypes;


/**
 * 개설과목의 교육생별 출결조회를 위한 클래스입니다.
 * @author 이준오
 *
 */
public class VwSubjectStudentAdminDAO {

	private Connection conn;
	private Statement stat; //매개변수X
	private PreparedStatement pstat; //매개변수O
	private ResultSet rs;
	
	public VwSubjectStudentAdminDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param sSeq 검색할 개설과목번호
	 * @return Procedure(procSelObjectAttendance)를 통한 해당 교육생 출결정보 list로 반환
	 */
	public ArrayList<VwSubjectStudentAdminDTO> studentList(String sSeq) {
		try {

			String sql = " { call procSelObjectAttendance(?,?) } ";
			CallableStatement cstat = null;
			cstat = conn.prepareCall(sql);
			cstat.setString(1, sSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			ArrayList<VwSubjectStudentAdminDTO> list = new ArrayList<VwSubjectStudentAdminDTO>();
			while (rs.next()) {
				VwSubjectStudentAdminDTO dto = new VwSubjectStudentAdminDTO();
				dto.setOpenSubSeq(rs.getString("openSubSeq"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setInTime(rs.getString("inTime"));
				dto.setState(rs.getString("state"));
				
				list.add(dto);
				
				
			}
			return list;

		} catch (Exception e) {
			System.out.println("VwSubjectStudentDAO.studentList()");
			e.printStackTrace();
		}
		return null;
		
	}
//		public ArrayList<VwSubjectStudentDTO> studentList(String sSeq) {
//		try {
//
//			String sql = "select openSubSeq, subjectName, studentSeq, studentName, inTime, state  from vwSubjectStudent where openSubSeq = ?";
//			
//			pstat = conn.prepareStatement(sql);
//			pstat.setString(1, sSeq);
//			rs = pstat.executeQuery();
//			ArrayList<VwSubjectStudentDTO> list = new ArrayList<VwSubjectStudentDTO>();
//			while (rs.next()) {
//				VwSubjectStudentDTO dto = new VwSubjectStudentDTO();
//				dto.setOpenSubSeq(rs.getString("openSubSeq"));
//				dto.setSubjectName(rs.getString("subjectName"));
//				dto.setStudentSeq(rs.getString("studentSeq"));
//				dto.setStudentName(rs.getString("studentName"));
//				dto.setInTime(rs.getString("inTime"));
//				dto.setState(rs.getString("state"));
//				
//				list.add(dto);
//				
//				
//			}
//			return list;
//
//		} catch (Exception e) {
//			System.out.println("VwSubjectStudentDAO.studentList()");
//			e.printStackTrace();
//		}
//		return null;
//	}


}
