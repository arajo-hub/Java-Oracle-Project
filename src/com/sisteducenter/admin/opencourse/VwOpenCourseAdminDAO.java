package com.sisteducenter.admin.opencourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.sisteducenter.start.DBUtil;


/**
 * 개설과정정보를 다루는 DAO 클래스입니다.
 * @author 이준오
 *
 */
public class VwOpenCourseAdminDAO {


	private Connection conn;
	private Statement stat; //매개변수X
	private PreparedStatement pstat; //매개변수O
	private ResultSet rs;
	
	public VwOpenCourseAdminDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 개설과정정보를 가져오는 메서드입니다.
	 * @return 개설과정정보 list로 반환
	 */
	public ArrayList<VwOpenCourseAdminDTO> courseList() {

		try {

			String sql = "select openCourSeq, courseName, startDate, endDate, lectureRoomSeq from vwopencourse";
						
			rs = stat.executeQuery(sql);
			
			ArrayList<VwOpenCourseAdminDTO> list = new ArrayList<VwOpenCourseAdminDTO>();
			
			while (rs.next()) {
				
				VwOpenCourseAdminDTO dto = new VwOpenCourseAdminDTO();
				
				dto.setOpenCourSeq(rs.getString("openCourSeq"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setStartDate(rs.getString("startDate"));
				dto.setEndDate(rs.getString("endDate"));
				dto.setLectureRoomSeq(rs.getString("lectureRoomSeq"));
				
				list.add(dto);
				
			}
			
			return list;
			
			

		} catch (Exception e) {
			System.out.println("VwOpenCourseDAO.subjectList()");
			e.printStackTrace();
		}
		return null;
	}


}
