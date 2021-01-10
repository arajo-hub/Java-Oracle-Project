package com.sisteducenter.admin.opencourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import com.sisteducenter.start.DBUtil;

public class OpenCourseDAO {

	private Connection conn;
	private Statement stat;	//매개변수 X
	private PreparedStatement pstat; // 매개변수 O=
	private ResultSet rs;

	//데이터베이스를 연결한다.
	public OpenCourseDAO() {

		try {		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();		
		} catch (Exception e) {
			System.out.println("AddressDAO.AddressDAO()");
			e.printStackTrace();
		}
	}

	/**
	 * 개설과정을 조회하는 메서드입니다.
	 * @return 종료되지 않은 개설과정을 반환한다.
	 */
	public ArrayList<OpenCourseListDTO> ocList() {

		try {

			String sql = "select"
					+ " oc.seq as seq,"
					+ " c.name as coursename,"
					+ " to_char(oc.startdate, 'yy/mm/dd') as startdate,"
					+ " to_char(oc.enddate, 'yy/mm/dd') as enddate,"
					+ " oc.lectureroomseq as lectureroom,"
					+ " oc.courseseq as courseseq,"
				    + " case" 
				        + " when r.rs is null then 0"
				        + " when r.rs is not null then r.rs"
			    	+ " end as regstudentnumber"
					+ " from tblOpencourse oc"
						+ " inner join tblCourse c "
							+ " on oc.courseseq = c.seq"
								+ " inner join tbllectureroom l"
									+ " on oc.lectureroomseq = l.seq"
										+ " left outer join (select opencourseq, count(opencourseq)as rs "
														+ " from tblRegistration"
														+ " group by opencourseq) r"
					+ " on c.seq = r.opencourseq"
					//+ " where oc.enddate > sysdate " -> 종료되지 않은 개설과정을 반환한다.
					+ " order by oc.startdate";

			rs = stat.executeQuery(sql);

			ArrayList <OpenCourseListDTO> list = new ArrayList<OpenCourseListDTO>();

			//개설 과정 정보 출력시 개설 과정명, 개설 과정기간(시작 년월일, 끝 년월일), 강의실명, 개설 과목 등록 여부, 교육생 등록 인원
			while(rs.next()) {
				OpenCourseListDTO dto = new OpenCourseListDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setCoursename(rs.getString("coursename"));
				dto.setStartdate(rs.getString("startdate"));
				dto.setEnddate(rs.getString("enddate"));
				dto.setLectureroom(rs.getString("lectureroom"));
				dto.setCourseseq(rs.getString("courseseq"));
				dto.setRegstudentnumber(rs.getString("regstudentnumber"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("AddressDAO.list()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 특정개설과정을 조회하는 메서드입니다.
	 * @param seq 특정개설과정의 시퀀스번호
	 * @return 특정개설과정을 반환합니다.
	 */
	public ArrayList<SpecificOpenCourseDTO> socList(String seq) {

		try {

			String sql = "select"
					+ " os.seq as seq,"
					+ " s.subjectname as subjectname,"
					+ " to_char(os.startdate, 'yy/mm/dd') as startdate,"
					+ " to_char(os.enddate, 'yy/mm/dd') as enddate,"
					+ " b.title as title,"
					+ " t.name as tName"
					+ " from tblOpenCourse oc"
					+ " inner join tblOpenSubject os"
					+ " on oc.seq = os.opencourseq"
					+ " inner join tblSubject s"
					+ " on s.seq = os.subjectseq"
					+ " inner join tblBook b"
					+ " on s.bookseq = b.seq"
					+ " inner join tblTeacher t"
					+ " on os.teacherseq = t.seq"
					+ " where oc.seq = ?";	//특정 개설 과정

			//쿼리 준비
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			//쿼리 실행
			rs = pstat.executeQuery();

			ArrayList<SpecificOpenCourseDTO> list = new ArrayList<SpecificOpenCourseDTO>();

			while(rs.next()) {
				SpecificOpenCourseDTO dto = new SpecificOpenCourseDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setSubjectname(rs.getString("subjectname"));
				dto.setStartdate(rs.getString("startdate"));
				dto.setEnddate(rs.getString("enddate"));
				dto.setTitle(rs.getString("title"));
				dto.settName(rs.getString("tName"));

				list.add(dto);
			}

			rs.close();


			return list;


		} catch (Exception e) {
			System.out.println("OpenCourseDAO.list()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 특정개설과정을 듣는 학생들을 조회하는 메서드입니다.
	 * @param seq 특정개설과정의 시퀀스번호
	 * @return 특정개설과정을 듣는 학생들을 반환합니다.
	 */
	public ArrayList<SpecificStudentDTO> ssList(String seq) {

		try {
			String sql = "select"
					+ " s.seq as seq,"
					+ " s.name as sName,"
					+ " s.idnum as ssn,"
					+ " s.tel as tel,"
					+ " to_char(r.regdate, 'yy/mm/dd') as regdate,"
					+ " r.regstate as regstate" //Y진행중 P수료완료 G중도포기
					+ " from tblregistration r"
					+ " inner join tblstudent s"
					+ " on r.studentseq = s.seq"
					+ " where r.opencourseq = ?";//특정 개설 과정

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<SpecificStudentDTO> list = new ArrayList<SpecificStudentDTO>();

			while(rs.next()) {
				SpecificStudentDTO dto = new SpecificStudentDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setsName(rs.getString("sName"));
				dto.setSsn(rs.getString("ssn"));
				dto.setTel(rs.getString("tel"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setRegstate(rs.getString("regstate"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("OpenCourseDAO.list()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 과정 정보를 조회하는 메서드 입니다.
	 * @return 과정 정보를 반환합니다.
	 */
	public ArrayList<CourseListDTO> cList() {

		try {
			String sql = "select seq, name, period from tblCourse";

			rs = stat.executeQuery(sql);			
			ArrayList<CourseListDTO> list = new ArrayList<CourseListDTO>();

			while(rs.next()) {
				CourseListDTO dto = new CourseListDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setPeriod(rs.getString("period"));

				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println("OpenCourseDAO.list()");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 과정 등록의 종료 날짜를 계산하는 메서드 입니다.
	 * @param startdate 시작날짜
	 * @param Period 기간(주)
	 * @return enddate 종료날짜
	 */
	public String retEndDate(String year, String month, String date, String Period) {

		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
		//System.out.println(c.get(Calendar.YEAR) + " + " + c.get(Calendar.MONTH)+ " + " + c.get(Calendar.DATE));
		c.add(Calendar.DATE, Integer.parseInt(Period) * 7);

		//System.out.println(c.get(Calendar.YEAR) + " + " + c.get(Calendar.MONTH)+ " + " + c.get(Calendar.DATE));
		
		year =  c.get(Calendar.YEAR) + "";
		month = c.get(Calendar.MONTH) < 10 ? "0"+c.get(Calendar.MONTH) : c.get(Calendar.MONTH) + "";
		date = c.get(Calendar.DATE) < 10 ? "0"+c.get(Calendar.DATE) : c.get(Calendar.DATE) + "";
		
		String enddate = year+month+date;
		
		
		return enddate;
	}

	/**
	 * 빈 강의실 목록을 반환하는 메서드입니다.
	 * @param startdate 개설하고자 하는 과정의 시작날짜
	 * @return elrList 빈 강의실을 반환합니다.
	 */
	public ArrayList<EmptyLectureRoomDTO> elrList(String startdate) {
		
		try {
			
			String sql = "select seq as room, capacity as capacity"
						+ " from tblLectureRoom"
						+ " where seq not in (select room.seq"
											+ " from (select lectureroomseq from tblOpenCourse where to_date(?) between startdate and enddate) ingOpenCourse"
												+ " inner join tblLectureRoom room"
													+ " on ingOpenCourse.lectureroomseq = room.seq)"
						+ "order by seq";
			
			pstat = conn.prepareStatement(sql);	
			pstat.setString(1, startdate);
			
			rs = pstat.executeQuery();
			
			ArrayList<EmptyLectureRoomDTO> list = new ArrayList<EmptyLectureRoomDTO>();
			while(rs.next()) {
				EmptyLectureRoomDTO dto = new EmptyLectureRoomDTO();
				dto.setroom(rs.getString("room"));
				dto.setCapacity(rs.getString("capacity"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("OpenCourseDAO.elrList()");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 새로운 강좌를 개설하는 메서드입니다.
	 * @param startdate 시작날짜
	 * @param enddate 종료날짜
	 * @param rectureroomseq 강의실 시퀀스
	 * @param courseseq 강좌 시퀀스
	 * @return result 결과반환
	 */
	public int add(String startdate, String enddate, int rectureroomseq, int courseseq) {
		
		try {
			
			//개설과정 시퀀스, 시작날짜, 종료날짜, 강의실, 과정시퀀스)
			String sql = "insert into tblOpenCourse values(seqTblOpenCourse.nextVal, to_date(?), to_date(?), ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, startdate);
			pstat.setString(2, enddate);
			pstat.setInt(3, rectureroomseq);
			pstat.setInt(4, courseseq);
			
			int result = pstat.executeUpdate();
			
			return result;
					
		} catch (Exception e) {
			System.out.println("OpenCourseDAO.add()");
			e.printStackTrace();
		}
		return 0;
	}
		
	/**
	 * 개설된 강좌를 삭제하는 메서드입니다.
	 * @param seq 삭제할 강좌의 시퀀스 번호
	 * @param list 
	 * @return 성공유무
	 */
	public int delete(String seq) {
		
		try {
			
			//tblOpenCourse 테이블의 자식테이블			
			String sql = "delete from tblRegistration where openCourseq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, Integer.parseInt(seq));
			pstat.executeUpdate();
			pstat.close();
			
			sql = "delete from tblopensubject where openCourseq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, Integer.parseInt(seq));
			pstat.executeUpdate();
			pstat.close();
			
			//tblOpenCourse 삭제
			sql = "delete from tblopenCourse where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, Integer.parseInt(seq));
			int result = pstat.executeUpdate();

			return result;
			
		} catch (Exception e) {
			System.out.println("OpenCourseDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 개설과정을 수정하는 메서드입니다.
	 * @param seq 개설과정의 번호
	 * @param editOC 수정될 내용
	 * @return 성공유무
	 */
	public int edit(String seq, TblOpenCourseDTO editOC) {
		
		try {
			
			String sql = "update tblOpenCourse set startdate = to_date(?), enddate = to_date(?), lectureroomseq = ?, courseseq = ? where seq = ?";
			

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, editOC.getStartdate());
			pstat.setString(2, editOC.getEnddate());
			pstat.setInt(3, Integer.parseInt(editOC.getLectureroomseq()));
			pstat.setInt(4, Integer.parseInt(editOC.getCourseseq()));
			pstat.setInt(5, Integer.parseInt(editOC.getSeq()));
			
			int result = pstat.executeUpdate();
			
			
			return result;
			
			
			
		} catch (Exception e) {
			System.out.println("OpenCourseDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}


}
