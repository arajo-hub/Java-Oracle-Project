package com.sisteducenter.attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

/**
 * 관리자의 교육생 출결조회를 위한 클래스입니다.
 * @author 이준오
 *
 */
public class VwStudentInSubjectAdminDAO {

	private Connection conn;
	private Statement stat; //매개변수X
	private PreparedStatement pstat; //매개변수O
	private ResultSet rs;
	
	public VwStudentInSubjectAdminDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param seq 검색할 교육생번호
	 * @return View(vwStudentInSubject)를 통한 교육생 출결상태 목록 list로 반환
	 */
	public ArrayList<VwStudentInSubjectAdminDTO> selStudent(String seq) {
		try {

			String sql = "select studentSeq, studentName, inDate, state from vwStudentInSubject where studentSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			ArrayList<VwStudentInSubjectAdminDTO> list = new ArrayList<VwStudentInSubjectAdminDTO>();
			while (rs.next()) {
				VwStudentInSubjectAdminDTO dto = new VwStudentInSubjectAdminDTO();
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setInDate(rs.getString("inDate"));
				dto.setState(rs.getString("state"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("VwStudentInSubjectDAO.selStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param date 검색할 교육생 출결 날짜
	 * @return View(vwStudentInSubject)를 통한 교육생 출결상태 목록 list로 반환
	 */
	public ArrayList<VwStudentInSubjectAdminDTO> allStudent(String date) {
		
		try {

			String sql = "select studentSeq, studentName, inDate, state from vwstudentinsubject where inDate = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, date);
			
			rs = pstat.executeQuery();
			ArrayList<VwStudentInSubjectAdminDTO> list = new ArrayList<VwStudentInSubjectAdminDTO>();
			while (rs.next()) {
				VwStudentInSubjectAdminDTO dto = new VwStudentInSubjectAdminDTO();
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setInDate(rs.getString("inDate"));
				dto.setState(rs.getString("state"));
				
				list.add(dto);
				
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("VwStudentInSubjectDAO.allStudent()");
			e.printStackTrace();
		}
		return null;
	}


}
