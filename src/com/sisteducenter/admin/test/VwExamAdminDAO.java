package com.sisteducenter.admin.test;

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
public class VwExamAdminDAO {

	private Connection conn;
	private Statement stat; //매개변수X
	private PreparedStatement pstat; //매개변수O
	private ResultSet rs;
	
	public VwExamAdminDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param seq 검색할 개설과정번호 내 시험정보
	 * @return 해당 시험정보 list로 반환
	 */
	public ArrayList<VwExamAdminDTO> testInfo(String seq) {

		try {

			String sql = "select openSubSeq, subjectName, teacherName, examDate, lectureRoomSeq from vwExam where lectureRoomSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<VwExamAdminDTO> list = new ArrayList<VwExamAdminDTO>();
			
			while (rs.next()) {
				VwExamAdminDTO dto = new VwExamAdminDTO();
				dto.setOpenSubSeq(rs.getString("openSubSeq"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setTeacherName(rs.getString("teacherName"));
				dto.setExamDate(rs.getString("examDate"));
				dto.setLectureRoomSeq(rs.getString("lectureRoomSeq"));
				
				list.add(dto);
			}
			
			return list;

		} catch (Exception e) {
			System.out.println("VwExamDAO.testInfo()");
			e.printStackTrace();
		}
		return null;
	}



}
