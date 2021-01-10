package com.sisteducenter.admin.lectureevaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.admin.opencourse.OpenCourseListDTO;
import com.sisteducenter.admin.opencourse.SpecificOpenCourseDTO;
import com.sisteducenter.admin.opencourse.SpecificStudentDTO;
import com.sisteducenter.start.DBUtil;

public class LectureEvaluationAdminDAO {

	private Connection conn;
	private Statement stat;	//매개변수 X
	private PreparedStatement pstat; // 매개변수 O=
	private ResultSet rs;

	//데이터베이스를 연결한다.
	public LectureEvaluationAdminDAO() {

		try {		
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();		
		} catch (Exception e) {
			System.out.println("AddressDAO.AddressDAO()");
			e.printStackTrace();
		}
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
	 * 강의평가를 조회하는 메서드입니다.
	 * @param seq 개설된 과목의 시퀀스 번호
	 * @return 강의평가 반환
	 */
	public ArrayList<LectureEvaluationAdminDTO> leList(String seq) {
		
		try {
			
			String sql = "select"
						+ " sub.subjectname as subjectName,"
						+ " tle.preparationscore as preparationscore,"
						+ " tle.understandscore as understandscore,"
						+ " tle.usefulscore as usefulScore,"
						+ " tle.totalscore as totalscore,"
						+ " stu.name as sName"
						+ " from tblLectureEvaluation tle"
							+ " inner join tblopensubject osub"
								+ " on tle.opensubseq = osub.seq"
									+ " inner join tblSubject sub"
										+ " on osub.subjectseq = sub.seq"
											+ " inner join tblstudent stu"
												+ " on tle.studentseq = stu.seq"
						+ " where tle.opensubseq = ?"
						+ " order by tle.seq";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, Integer.parseInt(seq));
			
			rs = pstat.executeQuery();
			
			
			ArrayList <LectureEvaluationAdminDTO> list = new ArrayList<LectureEvaluationAdminDTO>();
			while(rs.next()) {
				LectureEvaluationAdminDTO dto = new LectureEvaluationAdminDTO();
				
				dto.setSubjectName(rs.getString("subjectName"));
				dto.setPrepareationScore(rs.getString("preparationscore"));
				dto.setUnderstandScore(rs.getString("understandscore"));
				dto.setUsefulScore(rs.getString("usefulScore"));
				dto.setTotalScore(rs.getString("totalscore"));
				dto.setsName(rs.getString("sName"));
				
				list.add(dto);
			}
		
			return list;
		} catch (Exception e) {
			System.out.println("LectureEvalutationDAO.leList()");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 특정개설과정을 듣는 학생들을 조회하는 메서드입니다.
	 * @param seq 특정개설과정의 시퀀스번호
	 * @return 특정개설과정을 듣는 학생들을 반환합니다.
	 */
	public ArrayList<SpecificStudentDTO> ssList(String opencourseq) {
		
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
			pstat.setString(1, opencourseq);

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
	 * 강의평가를 등록하는 메서드입니다.
	 * @param socList 특정 개설과정의 개설 과목
	 * @param ssList 개설 과목을 듣는 학생들
	 * @return
	 */
	public int add(ArrayList<SpecificOpenCourseDTO> socList, ArrayList<SpecificStudentDTO> ssList) {
		
		
		try {
			
			//opensubseq, studentseq
			String sql = "insert into tbllectureevaluation values (seqTblLectureEvaluation.nextVal, 0, 0, 0, 0, ?, ?)";
			
			
			for(int i=0; i<socList.size(); i++) {
				pstat = conn.prepareStatement(sql);
				pstat.setInt(1, Integer.parseInt(socList.get(i).getSeq()));
				
				for(int j=0; j<ssList.size(); j++) {
					pstat.setInt(2, Integer.parseInt(ssList.get(j).getSeq()));
					
					pstat.execute();
				}
			}
			
		} catch (Exception e) {
			System.out.println("LectureEvalutationDAO.add()");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 특정 개설과목의 강의 평가를 삭제하는 메서드입니다.
	 * @param ssList 특정과정을 듣는 학생 리스트
	 * @return 성공유무
	 */
	public int delete(String opensubseq) {
		
		try {
			
			String sql = "delete from tbllectureevaluation where opensubseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, Integer.parseInt(opensubseq));
			
			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("LectureEvalutationDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}

	
}
