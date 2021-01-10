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
 * 교사의 강의스케줄 조회를 담당하는 DAO 클래스입니다.
 * @author 황원준
 *
 */
public class TeacherSheduleDAO {
	
	private Connection conn;
	private Statement stat; 		 //매개변수 X
	private PreparedStatement pstat; //매개변수 O
	private CallableStatement cstat;
	private ResultSet rs;
	
	public TeacherSheduleDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 교사의 강의스케줄 정보를 가져오는 메서드입니다.
	 * @param teacherSeq : 교사번호
	 * @return 강의스케줄 정보들이 담긴 ArrayList를 반환합니다.
	 */
	public ArrayList<TeacherSheduleDTO> list(String teacherSeq) {
		
		
		try {
			conn = DBUtil.open();
			
			String sql = "{ call procScheduleList(?, ?)}";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setInt(1, Integer.parseInt(teacherSeq));
			
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
	
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
		
			ArrayList<TeacherSheduleDTO> list = new ArrayList<TeacherSheduleDTO>();
			
			while(rs.next()) {
				
				TeacherSheduleDTO dto = new TeacherSheduleDTO();
				
				dto.setOs_seq(rs.getString("os_seq")); //과목 번호
				dto.setC_name(rs.getString("c_name")); //과정명
				dto.setOc_startDate(rs.getString("oc_startdate")); //과정 시작일
				dto.setOc_endDate(rs.getString("oc_enddate")); //과정 종료일
				dto.setStateCourse(rs.getString("state")); //과정상태
				dto.setOc_lectureRoom(rs.getString("oc_lectureroomseq")); //강의실
				dto.setS_subjectName(rs.getString("s_subjectname")); //과목명
				dto.setOs_startDate(rs.getString("os_startdate")); //과목 시작일
				dto.setOs_endDate(rs.getString("os_enddate")); //과목 종료일
				dto.setB_title(rs.getString("b_title")); //교재명
				dto.setCountStudent(rs.getString("stu_count")); //교육생 등록 인원
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("TeacherSheduleDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}

}
