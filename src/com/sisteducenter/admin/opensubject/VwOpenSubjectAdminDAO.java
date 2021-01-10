package com.sisteducenter.admin.opensubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.sisteducenter.start.DBUtil;



/**
 * 
 * @author 이준오
 *
 */
public class VwOpenSubjectAdminDAO {


	private Connection conn;
	private Statement stat; //매개변수X
	private PreparedStatement pstat; //매개변수O
	private ResultSet rs;
	
	public VwOpenSubjectAdminDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 
	 * @param seq 검색할 개설과정번호
	 * @return View(vwOpenSubject)를 통한 개설과정 내 과목정보 list로 반환
	 */
	public ArrayList<VwOpenSubjectAdminDTO> subjectList(String seq) {

		try {

			String sql = "select openSubSeq, subjectName, startDate, endDate, teacherName, lectureRoomSeq from vwOpenSubject where lectureRoomSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq); 
			
			rs = pstat.executeQuery();
			
			ArrayList<VwOpenSubjectAdminDTO> list = new ArrayList<VwOpenSubjectAdminDTO>();
			while (rs.next()) {
				VwOpenSubjectAdminDTO dto = new VwOpenSubjectAdminDTO();
				dto.setOpenSubSeq(rs.getString("openSubSeq"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setTeacherName(rs.getString("teacherName"));
				dto.setLectureRoomSeq(rs.getString("lectureRoomSeq"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("VwOpenSubjectDAO.subjectList()");
			e.printStackTrace();
		}
		return null;
	}
	
	

}
