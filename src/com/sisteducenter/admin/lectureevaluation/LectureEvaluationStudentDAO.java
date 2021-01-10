package com.sisteducenter.admin.lectureevaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

/**
 * 강의평가를 다루기 위한 DAO입니다.
 * 강의평가목록을 가져오고, 강의평가를 입력할 수 있습니다.
 * @author 조아라
 *
 */
public class LectureEvaluationStudentDAO {

	static Connection conn=null;
	static Statement stat=null;
	static PreparedStatement pstat=null;
	static ResultSet rs=null;
			
	/**
	 * 교육생의 강의평가목록을 가져옵니다.
	 * @param seq : 교육생번호
	 * @return 강의평가정보(LectureEvaluationStudentDTO)가 담긴 ArrayList를 반환합니다.
	 */
	public static ArrayList<LectureEvaluationStudentDTO> getList(String seq) {
		
		ArrayList<LectureEvaluationStudentDTO> list=new ArrayList<LectureEvaluationStudentDTO>();
		try {
			
			// 1. 데이터베이스 연결
			conn=DBUtil.open();
			stat=conn.createStatement();
			
			// 2. sql 작성
			String sql=String.format("select"
					+ " os.seq as subjectseq," // 개설과목번호
					+ " s.subjectname as subjectname," // 개설과목명
					+ " s.period as period," // 과목기간
					+ " s.division as division," // 과목구분
					+ " lv.preparationscore as preparationscore," // 수업준비점수
				    + " lv.understandscore as understandscore," // 내용전달점수
				    + " lv.usefulscore as usefulscore," // 유용도점수
				    + " lv.totalscore as totalscore" // 평점
					+ " from tblOpenSubject os"
					+ " inner join tbllectureevaluation lv"
			        + " on lv.opensubseq=os.seq"
					+ " inner join tblSubject s"
					+ " on os.subjectseq=s.seq"
					+ " inner join tblOpenCourse oc"
					+ " on oc.seq=os.opencourseq"
					+ " inner join tblregistration r"
					+ " on r.opencourseq=oc.seq"
					+ " inner join tblStudent st"
					+ " on r.studentseq=st.seq"
					+ " inner join tblBook b"
					+ " on s.bookseq=b.seq"
					+ " where st.seq=%s and lv.studentseq=%s", seq, seq);
			
			
			// 3. 쿼리 실행
			rs=stat.executeQuery(sql);
			
			// 4. 결과값 DTO 인스턴스만들어서 저장
			while(rs.next()) {
				LectureEvaluationStudentDTO dto=new LectureEvaluationStudentDTO();
				dto.setSubjectseq(rs.getString("subjectseq"));
				dto.setSubjectName(rs.getString("subjectname"));
				dto.setPeriod(rs.getString("period"));
				dto.setDivision(rs.getString("division"));
				dto.setPreparationscore(rs.getString("preparationscore"));
				dto.setUnderstandscore(rs.getString("understandscore"));
				dto.setUsefulscore(rs.getString("usefulscore"));
				dto.setTotalscore(rs.getInt("totalscore"));
				list.add(dto);
			}
			
			// 5. 자원 해제
			rs.close();
			stat.close();
			
			return list;
			
		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.getList()");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 강의평가를 입력하는 메서드입니다.
	 * @param dto2 : 강의평가가 아직 입력되지 않은 과목정보를 나타냅니다.
	 * @param seq : 교육생번호입니다.
	 * @return 강의평가가 제대로 입력되었는지 아닌지를 반환합니다. 1이면 성공.
	 */
	public static int setEvaluation(LectureEvaluationStudentDTO dto2, String seq) {
		
		try {
				
				String sql=String.format("update tbllectureevaluation set"
										+ " preparationscore=?,"
										+ " understandscore=?,"
										+ " usefulscore=?,"
										+ " totalscore=?"
										+ " where opensubseq=?"
										+ " and studentseq=?");
				
				pstat=conn.prepareStatement(sql);
				pstat.setString(1, dto2.getPreparationscore());
				pstat.setString(2, dto2.getUnderstandscore());
				pstat.setString(3, dto2.getUsefulscore());
				pstat.setInt(4, dto2.getTotalscore());
				pstat.setString(5, dto2.getSubjectseq());
				pstat.setString(6, seq);
				
				return pstat.executeUpdate();
					
		} catch (Exception e) {
			System.out.println("SubjectEvaluationDAO.setEvaluation()");
			e.printStackTrace();
		}
		
		return 0;
		
	}

}
