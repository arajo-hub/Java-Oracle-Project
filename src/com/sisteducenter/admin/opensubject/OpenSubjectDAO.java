package com.sisteducenter.admin.opensubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import com.sisteducenter.admin.opencourse.OpenCourseListDTO;
import com.sisteducenter.admin.opencourse.PossibleTeacherDTO;
import com.sisteducenter.admin.opencourse.SpecificOpenCourseDTO;
import com.sisteducenter.start.DBUtil;

public class OpenSubjectDAO {

	private Connection conn;
	private Statement stat;	//매개변수 X
	private PreparedStatement pstat; // 매개변수 O=
	private ResultSet rs;

	//데이터베이스를 연결한다.
	public OpenSubjectDAO() {

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
					+ " s.period as period,"
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
				dto.setPeriod(rs.getString("period"));
				dto.setStartdate(rs.getString("startdate"));
				dto.setEnddate(rs.getString("enddate"));
				dto.setTitle(rs.getString("title"));
				dto.settName(rs.getString("tName"));

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
	 * 등록할수 있는 과목을 조회하는 메서드입니다.
	 * @return 등록할수 있는 과목을 리턴합니다.
	 */
	public ArrayList<SubjectListDTO> sList() {
		
		try {
			
			String sql = "select seq, subjectname, division, period from tblSubject";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<SubjectListDTO> list = new ArrayList<SubjectListDTO>();
			while(rs.next()) {
				SubjectListDTO dto = new SubjectListDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSubjectName(rs.getString("subjectname"));
				dto.setDivision(rs.getString("division"));
				dto.setPeriod(rs.getString("period"));
				
				list.add(dto);
			}
			
			return list;
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.sList()");
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
	 * 강의 가능한 교사의 목록을 반환하는 메서드입니다.
	 * @param subjectseq 과목번호
	 * @param startdate 시작날짜
	 * @return 강의 가능한 교사
	 */
	public ArrayList<PossibleTeacherDTO> ptList(String startdate, String subjectseq) {
		
		try {

			String sql = "select t.seq as seq, t.name as name"
						+ " from tblpossiblesubject ps"
							+ " inner join tblteacher t"
								+ " on ps.teacherseq = t.seq"
									+ " inner join tblsubject s"
										+ " on ps.subjectseq = s.seq"
					    + " where t.seq not in (select teacherseq"
					    					+ " from tblopensubject"
					    					+ " where to_date(?) between startdate and enddate) and"	//시작날짜
					    + " s.seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, startdate);
			pstat.setInt(2, Integer.parseInt(subjectseq));
			
			rs = pstat.executeQuery();
			
			ArrayList<PossibleTeacherDTO> list = new ArrayList<PossibleTeacherDTO>();
			while(rs.next()) {
				PossibleTeacherDTO dto = new PossibleTeacherDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				
				list.add(dto);
			}
			
			
			return list;
					
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.ptList()");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 개설 과목을 등록하는 메서드입니다.
	 * @param startdate 시작날짜
	 * @param enddate 종료날짜
	 * @param opencourseq 개설과정
	 * @param subjectseq 과목
	 * @param teacherseq 
	 * @return 성공유무
	 */
	public int add(String startdate, String enddate, String opencourseq, String subjectseq, String teacherseq) {
		
		try {

			//시작날짜 종료날짜 개설과정 과목번호 교사번호
			String sql = "insert into tblOpenSubject values (seqTblOpenSubject.nextVal, to_date(?), to_date(?), ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, startdate);
			pstat.setString(2, enddate);
			pstat.setInt(3, Integer.parseInt(opencourseq));
			pstat.setInt(4, Integer.parseInt(subjectseq));
			pstat.setInt(5, Integer.parseInt(teacherseq));
			
			
			int result = pstat.executeUpdate();
			
			return result;
	
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.add()");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 개설 과목을 삭제하는 메서드입니다.
	 * @param seq 개설 과목 번호
	 * @return 성공유무
	 */
	public int delete(String seq) {
		
		try {
			
			
			String sql = "delete from tblopensubject where seq= ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, Integer.parseInt(seq));
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}

	
	/**
	 * 개설 과목을 수정하는 메서드입니다.
	 * @param editDTO 개설과목 수정내용
	 * @return 성공유무
	 */
	public int edit(TblOpenSubjectDTO editDTO) {
		
		try {
			String sql = "update tblOpenSubject set startdate = to_date(?), enddate = to_date(?), opencourseq = ?, subjectseq = ?, teacherseq = ? where seq = ?";
			
			
			System.out.println(editDTO.getStartdate() + " " + 
							editDTO.getEnddate() + " " + editDTO.getOpenCourseq() + " " + editDTO.getSubjectseq() + " " + editDTO.getTeacherseq() + " " + editDTO.getSeq());
			
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, editDTO.getStartdate());
			pstat.setString(2, editDTO.getEnddate());
			pstat.setInt(3, Integer.parseInt(editDTO.getOpenCourseq()));
			pstat.setInt(4, Integer.parseInt(editDTO.getSubjectseq()));
			pstat.setInt(5, Integer.parseInt(editDTO.getTeacherseq()));
			pstat.setInt(6, Integer.parseInt(editDTO.getSeq()));
			
			int result = pstat.executeUpdate();
			
			return result;
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.edit()");
			e.printStackTrace();
		}
		return 0;
	}



}
