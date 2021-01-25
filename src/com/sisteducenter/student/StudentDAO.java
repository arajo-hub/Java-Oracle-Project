package com.sisteducenter.student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 교육생에 관한 정보를 데이터베이스로부터 가져올 DAO 클래스입니다.
 * @author 조아라
 *
 */
public class StudentDAO {
	
	private static Connection conn = null;
	private static Statement stat=null;
	private static PreparedStatement pstat=null;
	private static CallableStatement cstat=null;
	private static ResultSet rs=null;

	/**
	 * 교육생 로그인 시 띄울 타이틀에 필요한 정보를 가져오는 메서드입니다.
	 * @param seq : 교육생번호
	 * @return 교육생이 수강한 과정의 간략한 정보를 담은 ArrayList를 반환합니다.
	 */
	public static ArrayList<String> getTitle(String seq) {
		
		ArrayList<String> list=new ArrayList<String>();
		
		try {
			
			// 1. 데이터베이스 연결
			
			conn=DBUtil.open();
			
			// 2. 입력받은 정보로 쿼리 작성
			// procShowTitle : 교육생이 수강중이거나, 수강했던 과정명의 간략한 정보를 타이틀로 출력하기 위해 가져오는 프로시저.
			String sql="{ call procShowTitle(?, ?, ?, ?, ?, ?) }";
			cstat=conn.prepareCall(sql);
			
			cstat.setString(1, seq); // 입력 : 교육생번호
			cstat.registerOutParameter(2, OracleTypes.VARCHAR); // 교육생명
			cstat.registerOutParameter(3, OracleTypes.VARCHAR); // 과정명
			cstat.registerOutParameter(4, OracleTypes.VARCHAR); // 과정시작일
			cstat.registerOutParameter(5, OracleTypes.VARCHAR); // 과정종료일
			cstat.registerOutParameter(6, OracleTypes.VARCHAR); // 강의실
			
			// 3. 쿼리 실행
			cstat.executeQuery();
		
			// 4. 프로시저로부터 결과값 반환.
			String studentName=cstat.getString(2); // 교육생명
			String courseName=cstat.getString(3); // 과정명
			String startDate=cstat.getString(4); // 과정시작일
			String endDate=cstat.getString(5); // 과정종료일
			String lectureRoom=cstat.getString(6); // 강의실
			
			// 5. ArrayList에 결과 넣기
			list.add(studentName);
			list.add(courseName);
			list.add(startDate);
			list.add(endDate);
			list.add(lectureRoom);
			
			// 자원 해제.
			cstat.close();
			return list;
			
		} catch (Exception e) {
			System.out.println("studentDAO.getTitle()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 교육생이 자신의 개인정보를 조회하는 메서드입니다.
	 * @param seq : 교육생번호
	 * @return 교육생정보를 나타내는 studentDTO를 반환합니다.
	 */
	public static StudentDTO getPersonalInfo(String seq) {
	
		StudentDTO dto=new StudentDTO();
		
		try {
			
			// 1. 데이터베이스 연결
			
			conn=DBUtil.open();
			
			// 2. 입력받은 정보로 쿼리 작성
			String sql=String.format("select * from tblStudent where seq=%s", seq);
			stat=conn.createStatement();
			
			// 3. 쿼리 실행
			rs=stat.executeQuery(sql);
			
			rs.next();
		
			// 4. 프로시저로부터 결과값 반환.
			dto.setSeq(rs.getString(1)); // 교육생명
			dto.setName(rs.getString(2)); // 과정명
			dto.setIdNum(rs.getString(3)); // 과정시작일
			dto.setTel(rs.getString(4)); // 과정종료일
			dto.setEmployment(rs.getString(5)); // 강의실
			
			// 자원 해제.
			stat.close();
			return dto;
			
		} catch (Exception e) {
			System.out.println("studentDAO.getPersonalInfo()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 교육생의 개인정보 수정을 수행하는 메서드입니다.
	 * @param dto : 교육생정보를 나타냅니다.
	 * @return 수행결과를 반환합니다.
	 */
	public static int editPersonalInfo(StudentDTO dto) {
		
		try {
			
		
			String sql=String.format("update tblStudent set name=?, tel=?, employment=? where seq=?");
			
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getTel());
			pstat.setString(3, dto.getEmployment());
			pstat.setString(4, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("studentDAO.editPersonalInfo()");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
}
