package com.sisteducenter.admin.opencourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.sisteducenter.start.DBUtil;



/**
 * 교육생번호로 검색하여 교육생정보를 가져올 DAO 클래스입니다.
 * @author 이준오
 *
 */
public class VwStudentOpenCourseAdminDAO {


	private Connection conn;
	private Statement stat; //매개변수X
	private PreparedStatement pstat; //매개변수O
	private ResultSet rs;
	
	public VwStudentOpenCourseAdminDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 교육생번호로 검색하여 교육생정보를 가져오는 메서드입니다.
	 * @param seq 검색할 교육생번호
	 * @return View(vwStudentOpenCourse)를 통한 해당 교육생 과정정보 list로 반환
	 */
	public ArrayList<VwStudentOpenCourseAdminDTO> list(String seq) {
		
		try {

			String sql = "select studentSeq, name, startDate, endDate, lectureRoomSeq, state, stateDate from vwStudentOpenCourse where studentSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setNString(1, seq);
			rs = pstat.executeQuery();
			
			ArrayList<VwStudentOpenCourseAdminDTO> list = new ArrayList<VwStudentOpenCourseAdminDTO>();
			
			while (rs.next()) {
				VwStudentOpenCourseAdminDTO dto = new VwStudentOpenCourseAdminDTO();
				
				
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setName(rs.getString("name"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setLectureRoomSeq(rs.getString("lectureRoomSeq"));
				dto.setState(rs.getString("state"));
				dto.setStateDate(rs.getString("stateDate"));
				
				list.add(dto);
				
				
			}
			
			return list;
			

		} catch (Exception e) {
			System.out.println("VwStudentOpenCourseDAO.list()");
			e.printStackTrace();
		}
		
		
		
		return null;
	}


}
