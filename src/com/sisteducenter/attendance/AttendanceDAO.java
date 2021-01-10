package com.sisteducenter.attendance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 출결정보를 다루기 위한 DAO클래스입니다.
 * 출결정보의 전체조회, 월별조회, 일별조회를 위한 메서드,
 * 출결정보 입력을 위한 메서드, 출결상태별 횟수 조회를 위한 메서드가 있습니다.
 * @author 조아라
 *
 */
public class AttendanceDAO {

	private static Connection conn = null;
	private static CallableStatement cstat=null;
	private static ResultSet rs=null;
	
	/**
	 * 출결정보 전체조회를 위한 데이터를 가져오는 메서드입니다.
	 * @param seq : 교육생번호입니다.
	 * @return 출결정보(AttendanceRecordDTO)들을 담고 있는 ArrayList를 반환합니다.
	 */
	public static ArrayList<AttendanceRecordDTO> getAllRecord(String seq) {
		
		ArrayList<AttendanceRecordDTO> list=new ArrayList<AttendanceRecordDTO>();
		
		try {
			
			// 1. 데이터베이스 연결
			
			conn=DBUtil.open();
			
			// 2. 프로시저 실행
			
			String sql="{ call procShowAttendanceAll(?, ?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			// 3. 쿼리 실행
			cstat.executeQuery();
		
			// 4. 프로시저로부터 결과값 반환.
			
			rs=(ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				
				AttendanceRecordDTO dto=new AttendanceRecordDTO();
				
				dto.setInTime(rs.getString(1));
				dto.setOutTime(rs.getString(2));
				dto.setState(rs.getString(3));
				
				list.add(dto);
			}
			
			// 자원 해제.
			rs.close();
			cstat.close();
			return list;
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.getInfo()");
			e.printStackTrace();
		}
		return null;
	} // showAllRecord
	
	/**
	 * 출석률을 나타내기 위한 메서드입니다.
	 * @param seq : 교육생번호입니다.
	 * @return 출석률(rate)과 프로시저 실행결과(result)를 변수로 하는 AttendanceRateDTO를 반환합니다.
	 * result는 프로시저 실행결과 정상적으로 출석률을 구했을 경우 0,
	 * 수강한 과정이 없는 경우 1을 나타냅니다. 
	 */
	public static AttendanceRateDTO getAttendanceRate(String seq) {

		ArrayList<AttendanceRateDTO> list=new ArrayList<AttendanceRateDTO>();
		
		try {
			conn = DBUtil.open();
			cstat = conn.prepareCall("{call procShowAttendRate(?, ?, ?)}");
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.NUMBER); // 출석률
			cstat.registerOutParameter(3, OracleTypes.NUMBER); // 결과 (0. 성공, 1. 수강한 과정 없음)
			
			cstat.executeQuery();
			double rate=cstat.getDouble(2);
			int result=cstat.getInt(3);
			
			AttendanceRateDTO dto=new AttendanceRateDTO();
			dto.setRate(rate);
			dto.setResult(result);
			
			cstat.close();
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("test2.main()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 출결정보 월별조회를 위한 데이터를 가져오는 메서드입니다.
	 * @param seq : 교육생번호입니다.
	 * @param year : 조회를 원하는 년도입니다.
	 * @param month : 조회를 원하는 월입니다.
	 * @return 출결정보(AttendanceRecordDTO)들을 담고 있는 ArrayList를 반환합니다.
	 */
	public static ArrayList<AttendanceRecordDTO> getMonthRecord(String seq, String year, String month) {
		
		ArrayList<AttendanceRecordDTO> list=new ArrayList<AttendanceRecordDTO>();
		
		try {
			
			// 1. 데이터베이스 연결
			
			conn=DBUtil.open();
			
			// 2. 프로시저 실행
			
			String sql="{ call procShowAttendanceMonth(?, ?, ?, ?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.setString(2, year);
			cstat.setString(3, month);
			cstat.registerOutParameter(4, OracleTypes.CURSOR);
			
			// 3. 쿼리 실행
			cstat.executeQuery();
		
			// 4. 프로시저로부터 결과값 반환.
			
			rs=(ResultSet)cstat.getObject(4);
			
			while (rs.next()) {
				
				AttendanceRecordDTO dto=new AttendanceRecordDTO();
				
				dto.setInTime(rs.getString(1));
				dto.setOutTime(rs.getString(2));
				dto.setState(rs.getString(3));
				
				list.add(dto);
			}
			
			// 자원 해제.
			rs.close();
			cstat.close();
			return list;
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.getInfo()");
			e.printStackTrace();
		}
		return null;
	} // getMonthRecord
	
	/**
	 * 출결정보 일별조회를 위한 데이터를 가져오는 메서드입니다.
	 * @param seq : 교육생번호입니다.
	 * @param year : 조회를 원하는 년도입니다.
	 * @param month : 조회를 원하는 달입니다.
	 * @param date : 조회를 원하는 일입니다.
	 * @return 입력된 날짜로 찾은 출결정보(AttendanceRecordDTO)를 모은 ArrayList를 반환합니다.
	 */
	public static ArrayList<AttendanceRecordDTO> getDateRecord(String seq, String year, String month, String date) {
		
		ArrayList<AttendanceRecordDTO> list=new ArrayList<AttendanceRecordDTO>();
	
		try {
			
			// 1. 데이터베이스 연결
			
			conn=DBUtil.open();
			
			// 2. 프로시저 실행
			
			String sql="{ call procShowAttendanceDate(?, ?, ?, ?, ?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.setString(2, year);
			cstat.setString(3, month);
			cstat.setString(4, date);
			cstat.registerOutParameter(5, OracleTypes.CURSOR);
			
			// 3. 쿼리 실행
			cstat.executeQuery();
		
			// 4. 프로시저로부터 결과값 반환.
			
			rs=(ResultSet)cstat.getObject(5);
			while (rs.next()) {
				AttendanceRecordDTO dto=new AttendanceRecordDTO();
				
				dto.setInTime(rs.getString(1));
				dto.setOutTime(rs.getString(2));
				dto.setState(rs.getString(3));
				
				list.add(dto);
			}
			// 자원 해제.
			
			rs.close();
			cstat.close();
			return list;
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.getInfo()");
			e.printStackTrace();
		}
		return null;
	} // getDateRecord
	
	/**
	 * 입실체크하는 메서드입니다.
	 * @param seq : 교육생번호입니다.
	 * @return 프로시저 실행결과(result)를 반환합니다.
	 * result가 0이면 정상적으로 입실체크가 이루어졌다는 것을 의미합니다.
	 * result가 1이면 이미 입실체크되어있음을 의미합니다.
	 * result가 2이면 입실체크 가능한 시간(오전 8시 30분 ~ 17시59분)이 아님을 의미합니다.
	 * 그 이외의 값이면 입실체크에 실패했다는 것을 의미합니다.
	 */
	public static int insertInRecord(String seq) {
		
		try {
			
			conn=DBUtil.open();
			
			
			String sql="{ call procInsertInTime(?, ?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.NUMBER);
			
			// 3. 쿼리 실행
			cstat.executeQuery();
		
			// 4. 프로시저로부터 결과값 반환.
			
			int result=cstat.getInt(2);
			
			// 자원 해제.
			cstat.close();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("AttendanceDAO.insertInRecord()");
			e.printStackTrace();
		}
		
		return -1;
		
	}

	/**
	 * 퇴실체크하는 메서드입니다.
	 * @param seq : 교육생번호입니다.
	 * @return 프로시저 실행결과(result)를 반환합니다.
	 * result가 0이면 정상적으로 퇴실체크가 이루어졌다는 것을 의미합니다.
	 * result가 1이면 이미 퇴실체크되어있음을 의미합니다.
	 * result가 2이면 퇴실체크 가능한 시간(오전 9시 ~ 오후 6시 30분)이 아님을 의미합니다.
	 * 그 이외의 값이면 퇴실체크에 실패했다는 것을 의미합니다.
	 */
	public static int insertOutRecord(String seq, String state) {
		// 정상, 지각은 state가 null
		// 조퇴, 외출, 병가, 기타는 각각 3, 4, 5, 6
		
		try {
					
			conn=DBUtil.open();
			
			String sql="{ call procInsertOutTime(?, ?, ?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.setString(2, state);
			cstat.registerOutParameter(3, OracleTypes.NUMBER);
			
			// 3. 쿼리 실행
			cstat.executeQuery();
		
			// 4. 프로시저로부터 결과값 반환.
			
			int result=cstat.getInt(3);
			
			// 자원 해제.
			cstat.close();
			
			return result;
					
		} catch (Exception e) {
			System.out.println("AttendanceDAO.insertInRecord()");
			e.printStackTrace();
		}
				
		return -1;
	}

	/**
	 * 출결상태별 횟수를 가져오는 메서드입니다.
	 * @param seq : 교육생번호입니다.
	 * @return 출결상태별 횟수가 담긴 ArrayList를 반환합니다.
	 * 순서대로 정상, 지각, 조퇴, 외출, 병가, 기타를 나타냅니다.
	 */
	public static ArrayList<Integer> getStateCount(String seq) {
		ArrayList<Integer> list=new ArrayList<Integer>();
		
		try {
			
			conn=DBUtil.open();
			
			String sql="{ call procShowStateCount(?, ?, ?, ?, ?, ?, ?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.NUMBER);
			cstat.registerOutParameter(3, OracleTypes.NUMBER);
			cstat.registerOutParameter(4, OracleTypes.NUMBER);
			cstat.registerOutParameter(5, OracleTypes.NUMBER);
			cstat.registerOutParameter(6, OracleTypes.NUMBER);
			cstat.registerOutParameter(7, OracleTypes.NUMBER);
			
			// 3. 쿼리 실행
			cstat.executeQuery();
		
			// 4. 프로시저로부터 결과값 반환.
			
			int normal=cstat.getInt(2);
			int late=cstat.getInt(3);
			int early=cstat.getInt(4);
			int goout=cstat.getInt(5);
			int sick=cstat.getInt(6);
			int etc=cstat.getInt(7);
			
			list.add(normal);
			list.add(late);
			list.add(early);
			list.add(goout);
			list.add(sick);
			list.add(etc);
			
			// 자원 해제.
			cstat.close();
			
			return list;
					
		} catch (Exception e) {
			System.out.println("AttendanceDAO.insertInRecord()");
			e.printStackTrace();
		}
				
		return null;
	}
}