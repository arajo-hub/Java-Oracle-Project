package com.sisteducenter.admin.grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.sisteducenter.start.DBUtil;


/**
 * 개설과목번호나 교육생번호로 성적정보를 출력하는 클래스입니다.
 * @author 이준오
 *
 */
public class VwOpenSubGradeAdminDAO {

	private Connection conn;
	private Statement stat; //매개변수X
	private PreparedStatement pstat; //매개변수O
	private ResultSet rs;
	
	public VwOpenSubGradeAdminDAO() {
		
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
	 * @return View(vwOpenSubGrade)를 통한 해당 과목내 성적정보 list로 반환
	 */
	public ArrayList<VwOpenSubGradeAdminDTO> gradeList(String sSeq) {

		try {

			String sql = "select courseName, courseStartDate, courseEndDate, lectureRoomSeq, subjectName, teacherName, bookTitle, studentName, idNum, handwritingScore, practiceScore, studentSeq, subjectStartDate, subjectEndDate, subjectSeq from vwOpenSubGrade where subjectSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, sSeq);
			
			rs = pstat.executeQuery();
			
			
			ArrayList<VwOpenSubGradeAdminDTO> list = new ArrayList<VwOpenSubGradeAdminDTO>();
			
			while (rs.next()) {
				VwOpenSubGradeAdminDTO dto = new VwOpenSubGradeAdminDTO();
				dto.setCourseName(rs.getString("courseName"));
				dto.setCourseStartDate(rs.getString("courseStartDate"));
				dto.setCourseEndDate(rs.getString("courseEndDate"));
				dto.setLectureRoomSeq(rs.getString("lectureRoomSeq"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setTeacherName(rs.getString("teacherName"));
				dto.setBookTitle(rs.getString("bookTitle"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setIdNum(rs.getString("idNum"));
				dto.setHandwritingScore(rs.getString("handwritingScore"));
				dto.setPracticeScore(rs.getString("practiceScore"));
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setSubjectStartDate(rs.getString("subjectStartDate"));
				dto.setSubjectEndDate(rs.getString("subjectEndDate"));
				dto.setSubjectSeq(rs.getString("subjectSeq"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
			
		} catch (Exception e) {
			System.out.println("VwOpenSubGradeDAO.gradeList()");
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 
	 * @param seq 검색할 교육생번호
	 * @return View(vwOpenSubGrade)를 통한 해당 교육생 성적정보 list로 반환
	 */
	public ArrayList<VwOpenSubGradeAdminDTO> studentGradeList(String seq) {
		try {
		String sql = "select courseName, courseStartDate, courseEndDate, lectureRoomSeq, subjectName, teacherName, bookTitle, studentName, idNum, handwritingScore, practiceScore, studentSeq, subjectStartDate, subjectEndDate, subjectSeq from vwOpenSubGrade where studentSeq = ?";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, seq);
		
		rs = pstat.executeQuery();
		
		
		ArrayList<VwOpenSubGradeAdminDTO> list = new ArrayList<VwOpenSubGradeAdminDTO>();
		
		while (rs.next()) {
			VwOpenSubGradeAdminDTO dto = new VwOpenSubGradeAdminDTO();
			dto.setCourseName(rs.getString("courseName"));
			dto.setCourseStartDate(rs.getString("courseStartDate"));
			dto.setCourseEndDate(rs.getString("courseEndDate"));
			dto.setLectureRoomSeq(rs.getString("lectureRoomSeq"));
			dto.setSubjectName(rs.getString("subjectName"));
			dto.setTeacherName(rs.getString("teacherName"));
			dto.setBookTitle(rs.getString("bookTitle"));
			dto.setStudentName(rs.getString("studentName"));
			dto.setIdNum(rs.getString("idNum"));
			dto.setHandwritingScore(rs.getString("handwritingScore"));
			dto.setPracticeScore(rs.getString("practiceScore"));
			dto.setStudentSeq(rs.getString("studentSeq"));
			dto.setSubjectStartDate(rs.getString("subjectStartDate"));
			dto.setSubjectEndDate(rs.getString("subjectEndDate"));
			dto.setSubjectSeq(rs.getString("subjectSeq"));
			
			list.add(dto);
			
			}
			
			return list;
			
			
			
		} catch (Exception e) {
			System.out.println("VwOpenSubGradeDAO.gradeList()");
			e.printStackTrace();
		} 
		return null;
	}


}
