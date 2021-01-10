package com.sisteducenter.admin.grade;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 성적정보를 다루기 위한 DAO 클래스입니다.
 * 교육생 개인의 성적정보를 데이터베이스로부터 가져올 수 있습니다.
 * @author 조아라
 *
 */
public class GradeDAO {
	
	private static Connection conn = null;
	private static CallableStatement cstat=null;
	private static ResultSet rs=null;
	
	/**
	 * 데이터베이스로부터 성적정보를 가져오는 메서드입니다.
	 * @param seq : 교육생번호
	 * @return 성적정보(GradeDTO)들이 들어있는 ArrayList를 반환합니다.
	 */
	public static ArrayList<GradeDTO> getGrade(String seq){

		ArrayList<GradeDTO> list=new ArrayList<GradeDTO>();
		
		try {
			
			// 1. 데이터베이스 연결
			
			conn=DBUtil.open();
			
			// 2. 프로시저 실행
			
			String sql="{ call procShowGradeList(?, ?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			// 3. 쿼리 실행
			cstat.executeQuery();
		
			// 4. 프로시저로부터 결과값 반환.
			
			rs=(ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				
				GradeDTO dto=new GradeDTO();
				
				dto.setCourseSeq(rs.getString(1));
				dto.setSubjectName(rs.getString(2));
				dto.setStartDate(rs.getString(3));
				dto.setEndDate(rs.getString(4));
				dto.setBook(rs.getString(5));
				dto.setTeacherName(rs.getString(6));
				dto.setHandwritingAllot(Integer.parseInt(rs.getString(7)));
				dto.setPracticeAllot(Integer.parseInt(rs.getString(8)));
				dto.setAttendanceAllot(Integer.parseInt(rs.getString(9)));
				dto.setHandwritingScore(Integer.parseInt(rs.getString(10)));
				dto.setPracticeScore(Integer.parseInt(rs.getString(11)));
				dto.setAttendanceScore(Integer.parseInt(rs.getString(12)));
				dto.setExamDate(rs.getString(13));
				
				list.add(dto);
			}
			
			// 자원 해제.
			rs.close();
			cstat.close();
			return list;
			
		} catch (Exception e) {
			System.out.println("GradeDAO.getGrade()");
			e.printStackTrace();
		}
		return null;
	}
}
