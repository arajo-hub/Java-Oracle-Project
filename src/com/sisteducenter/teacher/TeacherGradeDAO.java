package com.sisteducenter.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.start.DBUtil;

import oracle.jdbc.OracleTypes;

/**
 * 교사 > 성적 입출력, 출결 관리 및 조회를 위한 DAO
 * @author 신지수
 *
 */
public class TeacherGradeDAO {
	
	private Connection conn;
	private Statement stat;	//매개변수X
	private PreparedStatement pstat; //매개변수O
	private CallableStatement cstat; //프로시저가 반환한 커서를 참조
	private ResultSet rs;
	private Scanner scan;

	public TeacherGradeDAO() {
		
		scan = new Scanner(System.in);
		
		try {
			
			conn = DBUtil.open();
			stat = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 교사 > 성적 확인을 위한 과목목록 조회
	 * 과목번호, 과정명, 과정기간(시작 끝), 강의실, 과목명, 과목기간(시작 끝), 교재명, 출결, 필기, 실기 배점, 성적 등록 여부
	 * @param seq 교사번호
	 * @return
	 */
	public ArrayList<GradeDTO> grade(String seq) {
		try {

			//System.out.println("교사번호 : " + seq);
			String proc = "{ call proc_CourseGrade(?, ?) }";
			cstat = conn.prepareCall(proc);
			
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			
			ArrayList<GradeDTO> list = new ArrayList<GradeDTO>();
			while (rs.next()) {
				GradeDTO dto = new GradeDTO();

				dto.setOpenSubjectSeq(rs.getString("openSubjectSeq"));
				dto.setOpenCourseSeq(rs.getString("openCourseSeq"));
				dto.setCourseName(rs.getString("courseName"));
				dto.setCourseStart(rs.getString("courseStart"));
				dto.setCourseEnd(rs.getString("courseEnd"));
				dto.setClassRoom(rs.getString("classRoom"));
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setSubjectStart(rs.getString("subjectStart"));
				dto.setSubjectEnd(rs.getString("subjectEnd"));
				dto.setBookTitle(rs.getString("bookTitle"));
				dto.setWritingAllot(rs.getString("writingAllot"));
				dto.setPracticeAllot(rs.getString("practiceAllot"));
				dto.setAttendanceAllot(rs.getString("attendanceAllot"));
				dto.setRegisterGrade(rs.getString("registerGrade"));
				
				list.add(dto);
			}
			
			System.out.println(list.size());
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 교사 > 강의가 끝난 과목의 학생 목록 조회
	 * 교육생 정보(이름, 전화번호, 수료 또는 중도탈락) 및 성적이 출결, 필기, 실기 점수
	 * @param seq 교사번호
	 * @param selsub 과목번호
	 * @return
	 */
	public ArrayList<StudentInfoDTO> studentList(String seq, String selsub) {
		try {
			
			String proc = "{ call proc_StudentscoreDetail(?, ?, ?) }";
			cstat = conn.prepareCall(proc);
			
			cstat.setString(1, seq);
			cstat.setString(2, selsub);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(3);
			
			ArrayList<StudentInfoDTO> silist = new ArrayList<StudentInfoDTO>();
			while (rs.next()) {
				StudentInfoDTO sidto = new StudentInfoDTO();
				
				sidto.setStudentSeq(rs.getString("studentSeq"));
				sidto.setStudentName(rs.getString("studentName"));
				sidto.setStudentTel(rs.getString("studentTel"));
				sidto.setRegistState(rs.getString("registState"));
				sidto.setStateFailDate(rs.getString("stateFailDate"));
				sidto.setWritingScore(rs.getString("writingScore"));
				sidto.setPracticeScore(rs.getString("practiceScore"));
				sidto.setAttendanceScore(rs.getString("attendanceScore"));
				sidto.setOpenSubjectSeq(rs.getString("openSubjectSeq"));
				
				silist.add(sidto);
			}
			
			return silist;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 교사 > 성적 등록하기
	 * @param sidto2 
	 * seq 교사번호
	 * selsub 과목번호
	 * selstu 학생번호
	 * write 입력받은 필기점수
	 * practice 입력받은 실기점수
	 * attendance 입력받은 출결점수 
	 * @return
	 */
	public int gradeUpdate(StudentInfoDTO sidto2) {
		try {
			
			String upproc = "{ call proc_ScoreUpdate(?,?,?,?,?,?) }";
			pstat = conn.prepareStatement(upproc);

			//System.out.println(sidto2);
			
			pstat.setString(1, sidto2.getAttendanceScore());
			pstat.setString(2, sidto2.getWritingScore());
			pstat.setString(3, sidto2.getPracticeScore());
			pstat.setString(4, sidto2.getTeacherSeq());
			pstat.setString(5, sidto2.getStudentSeq());
			pstat.setString(6, sidto2.getOpenSubjectSeq());
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}//gradeUpdate 성적 입력/수정 

	
	
//========================================================================
	
	/**
	 * 교사 > 출결 조회를 위한 과정 목록 조회 
	 * @param seq 교사번호
	 * @return
	 */
	public ArrayList<AttendanceDTO> AttendanceCourselist(String seq) {
		try {
			String proc = "{ call proc_AttendanceOutput(?, ?) }";
			cstat = conn.prepareCall(proc);
			
			cstat.setString(1, seq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<AttendanceDTO> attlist = new ArrayList<AttendanceDTO>();
			while (rs.next()) {
				AttendanceDTO attdto = new AttendanceDTO();
				
				attdto.setCourseSeq(rs.getString("CourseSeq"));
				attdto.setCourseName(rs.getString("courseName"));
				attdto.setCourseStart(rs.getString("attendanceIn"));
				attdto.setCourseEnd(rs.getString("attendanceOut"));
				
				attlist.add(attdto);
			}
			return attlist;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 교사 > 특정 과정의 전체 교육생 출결 조회
	 * @param selcour 과정번호
	 * @param seq 교사번호
	 * @return
	 */
	public ArrayList<AttendanceDTO> attendAllAll(String selcour, String seq) {
		try {
			String proc = "{ call procAllDateAttendance(?,?,?) }";
			cstat = conn.prepareCall(proc);
			
			cstat.setString(1, selcour);
			cstat.setString(2, seq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(3);
			
			ArrayList<AttendanceDTO> attlist = new ArrayList<AttendanceDTO>();
			while (rs.next()) {
				AttendanceDTO attdto = new AttendanceDTO();
				
				attdto.setStudentSeq(rs.getString("studentSeq"));
				attdto.setStudentName(rs.getString("studentName"));
				attdto.setInDate(rs.getString("inDate"));
				attdto.setOutDate(rs.getString("outDate"));
				attdto.setState(rs.getString("state"));
				
				attlist.add(attdto);
			}
			return attlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 교사 > 특정 과정의 특정 교육생 출결 조회
	 * @param selcour 과정번호
	 * @param seq 교사번호
	 * @param selstu 학생번호
	 * @return
	 */
	public ArrayList<AttendanceDTO> attendStudentAll(String selcour, String seq, String selstu) {
		try {
			String proc = "{ call procStudentDateAttendance(?,?,?,?) }";
			
			cstat = conn.prepareCall(proc);
			
			cstat.setString(1, selcour);
			cstat.setString(2, seq);
			cstat.setString(3, selstu);
			cstat.registerOutParameter(4, OracleTypes.CURSOR);			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(4);
			
			ArrayList<AttendanceDTO> attlist2 = new ArrayList<AttendanceDTO>();
			while (rs.next()) {
				AttendanceDTO attdto = new AttendanceDTO();
				
				attdto.setStudentSeq(rs.getString("studentSeq"));
				attdto.setStudentName(rs.getString("studentName"));
				attdto.setInDate(rs.getString("inDate"));
				attdto.setOutDate(rs.getString("outDate"));
				attdto.setState(rs.getString("state"));
				
				attlist2.add(attdto);
			}
			return attlist2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 교사 > 특정 교육생 선택을 위한 교육생 목록 조회
	 * @param selcour 과정번호
	 * @param seq 교사번호
	 * @return
	 */
	public ArrayList<AttendanceDTO> StudentAttList(String selcour, String seq) {
		try {
			String proc = "{ call procStudentAttList(?,?,?) }";
			cstat = conn.prepareCall(proc);
			
			cstat.setString(1, selcour);
			cstat.setString(2, seq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(3);
			
			ArrayList<AttendanceDTO> attlist = new ArrayList<AttendanceDTO>();
			while (rs.next()) {
				AttendanceDTO attdto = new AttendanceDTO();
				
				attdto.setStudentSeq(rs.getString("studentSeq"));
				attdto.setStudentName(rs.getString("studentName"));
				attdto.setCourseSeq(rs.getString("courseSeq"));
				attdto.setCourseName(rs.getString("courseName"));
				
				attlist.add(attdto);
			}
			return attlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 교사 > 특정 과정의 특정 기간별 전체 교육생 출결 조회
	 * @param selcour 과정번호
	 * @param seq 교사번호
	 * @param attdateStart 조회 시작 날짜
	 * @param attdateEnd 조회 종료 날짜
	 * @return
	 */
	public ArrayList<AttendanceDTO> procSelDateAttendance(String selcour, String seq, String attdateStart, String attdateEnd) {
		try {
			String proc = "{call procSelDateAttendance(?,?,?,?,?)}";
			cstat = conn.prepareCall(proc);
					
			cstat.setString(1, selcour);
			cstat.setString(2, seq);
			cstat.setString(3, attdateStart);
			cstat.setString(4, attdateEnd);
			cstat.registerOutParameter(5, OracleTypes.CURSOR);			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(5);
			
			ArrayList<AttendanceDTO> attlist = new ArrayList<AttendanceDTO>();
			while (rs.next()) {
				AttendanceDTO attdto = new AttendanceDTO();
				
				attdto.setStudentSeq(rs.getString("studentSeq"));
				attdto.setStudentName(rs.getString("studentName"));
				attdto.setCourseSeq(rs.getString("courseSeq"));
				attdto.setCourseName(rs.getString("courseName"));
				attdto.setInDate(rs.getString("inDate"));
				attdto.setOutDate(rs.getString("outDate"));
				attdto.setState(rs.getString("state"));
				
				attlist.add(attdto);

			}
			return attlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 교사 > 특정 과정 특정 기간별 특정 교육생 출결 조회 
	 * @param selcour 과정번호
	 * @param seq 교사번호
	 * @param attdateStart 조회 시작 날짜
	 * @param attdateEnd 조회 종료 날짜
	 * @param selstu 학생번호
	 * @return
	 */
	public ArrayList<AttendanceDTO> attendStudentPeriod(String selcour, String seq, String attdateStart, String selstu, String attdateEnd) {
		try {
			
			String proc = "{call procDateStuAttendance(?,?,?,?,?,?)}";
			cstat = conn.prepareCall(proc);
					
			cstat.setString(1, selcour);
			cstat.setString(2, seq);
			cstat.setString(3, attdateStart);
			cstat.setString(4, attdateEnd);
			cstat.setString(5, selstu);
			cstat.registerOutParameter(6, OracleTypes.CURSOR);			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(6);
			
			ArrayList<AttendanceDTO> attlist2 = new ArrayList<AttendanceDTO>();
			AttendanceDTO attdto = new AttendanceDTO();
			while (rs.next()) {
			attdto.setStudentSeq(rs.getString("studentSeq"));
			attdto.setStudentName(rs.getString("studentName"));
			attdto.setCourseSeq(rs.getString("courseSeq"));
			attdto.setCourseName(rs.getString("courseName"));
			attdto.setInDate(rs.getString("inDate"));
			attdto.setOutDate(rs.getString("outDate"));
			attdto.setState(rs.getString("state"));
			
			attlist2.add(attdto);
			}
			
		return attlist2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 교사 > 특정 과목의 특정 학생의 필기 점수 삭제
	 * @param selsub 과목번호
	 * @param selstu 학생번호
	 * @param seq 교사번호
	 * @return
	 */
	public int deleteWrite(String selsub, String selstu, String seq) {
		try {
			
			String proc = "{ call proc_DeleteHandWritingScore(?,?,?) }";
			pstat = conn.prepareStatement(proc);
			
			pstat.setString(1, seq);
			pstat.setString(2, selstu);
			pstat.setString(3, selsub);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 교사 > 특정 과목의 특정 학생의 실기 점수 삭제
	 * @param selsub 과목번호
	 * @param selstu 학생번호
	 * @param seq 교사번호
	 * @return
	 */
	public int deletePractice(String selsub, String selstu, String seq) {
		try {

			String proc = "{ call proc_DeletePracticalScore(?,?,?) }";
			pstat = conn.prepareStatement(proc);
			
			pstat.setString(1, seq);
			pstat.setString(2, selstu);
			pstat.setString(3, selsub);
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 교사 > 특정 과목의 특정 학생의 출결 점수 삭제
	 * @param selsub 과목번호
	 * @param selstu 학생번호
	 * @param seq 교사번호 
	 * @return
	 */
	public int deleteAttend(String selsub, String selstu, String seq) {
		try {
			
			String proc = "{ call proc_DeleteAttendScore(?,?,?) }";
			pstat = conn.prepareStatement(proc);
			
			pstat.setString(1, seq);
			pstat.setString(2, selstu);
			pstat.setString(3, selsub);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
