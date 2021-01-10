package com.sisteducenter.admin.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

import oracle.jdbc.OracleTypes;


/**
 * 교사관리에 관련된 기능을 수행하는 클래스 입니다.
 * 전체 교사 조회, 교사 검색, 등록, 수정, 삭제 기능 및
 * 교사의 강의가능과목 조회, 등록, 수정, 삭제, 담당과정 조회 기능을 수행하여 DB에 반영합니다.
 * @author 권주홍
 */
public class TeacherDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	/**
	 * 교사 DAO의 기본 생성자 입니다.
	 */
	public TeacherDAO() {
		
		try {

			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.TeacherDAO()");
			e.printStackTrace();
		}
		
	}

	
	
	/**
	 * 교사 등록 메소드 입니다.
	 * 교사 정보 데이터를 받아 DB에 등록하고 성공 여부를 반환합니다.
	 * @param 교사정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int add(TeacherDTO dto) {
		
		try {
			
			String sql = "insert into tblTeacher (seq, name, idNum, tel) values (seqtblTeacher.nextVal, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getIdNum());
			pstat.setString(3, dto.getTel());
			
			return pstat.executeUpdate(); // 적용된 행의 갯수 반환됨 -> 1
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	/**
	 * 강의가능과목 등록 메소드 입니다.
	 * 강의가능과목 정보 데이터를 받아 DB에 등록하고 성공 여부를 반환합니다.
	 * @param 강의가능과목정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int addPossibleSubject(ArrayList<PossibleSubjectDTO> plist) {
		
		int result=0;
		
		for(int i =0; i < plist.size(); i++) {
			
			try {
				
				String sql = "insert into tblPossibleSubject (teacherSeq, subjectSeq) values (?, ?)";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, plist.get(i).getTeacherSeq());
				pstat.setString(2, plist.get(i).getSubjectSeq());
				
				pstat.executeUpdate();
				
				result ++;
				
			} catch (Exception e) {
				System.out.println("TeacherDAO.addPossibleSubject()");
				e.printStackTrace();
			}
			
		}
		
		return result;
	
	}
	
	
	
	
	
	/**
	 * 교사 조회 / 검색 메소드 입니다.
	 * DB의 교사 데이터 전체목록 또는 특정 단어가 포함된 데이터를 ArrayList로 저장하여 반환 합니다.
	 * @param null(조회기능) / word(검색기능)
	 * @return 교사정보 객체 배열
	 */
	public ArrayList<TeacherDTO> list(String word) {
		
		//word -> null -> 목록보기
		//word -> "???" -> 검색하기
		
		try {
			
			String where = "";
			
			// 이름에 word가 포함된 교사 검색하기
			if(word != null) {
				where = String.format("where name like '%%%s%%'", word);	
			}
			
			// 교사 전체 목록 조회
			String sql = String.format("select * from tblTeacher %s order by seq", where);
			
			rs = stat.executeQuery(sql);
			
			ArrayList<TeacherDTO> list = new ArrayList<TeacherDTO>();
			
			while (rs.next()) {
				
				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setIdNum(rs.getString("idNum"));
				dto.setTel(rs.getString("tel"));
				
				list.add(dto);
			} 
			
			return list;
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 특정교사의 강의가능과목 조회 메소드 입니다.
	 * 교사번호를 받아 해당교사의 강의가능과목을 ArrayList로 저장하여 반환 합니다.
	 * @param 교사번호 
	 * @return 강의가능과목객체 배열
	 */
	public ArrayList<PossibleSubjectDTO> getPossibleSubject(String seq) {
		
		try {
			
			// 선택한 교사의 강의가능과목을 출력하는 프로시저
			String sql = "{ call procPossibleSubjectList(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq); // 입력 받은 교사번호
			cstat.registerOutParameter(2, OracleTypes.CURSOR); //커서 타입
			
			cstat.executeQuery();
			
			//Oracle(cursor) -> JDBC(ResultSet)
			ResultSet rs = (ResultSet)cstat.getObject(2); //다운캐스팅
			
			ArrayList<PossibleSubjectDTO> list = new ArrayList<PossibleSubjectDTO>();
			
			while (rs.next()) {
				
				PossibleSubjectDTO dto = new PossibleSubjectDTO();
				
				dto.setTeacherName(rs.getString("교사"));
				dto.setSubjectName(rs.getString("강의가능과목"));
	
				
				list.add(dto);
			} 
			
			return list;
				
		} catch (Exception e) {
			System.out.println("TeacherDAO.getPossibleSubject()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	/**
	 * 특정교사의 담당과정정보 조회 메소드 입니다.
	 * 교사번호를 받아 해당교사의 과정 관련 정보들을 ArrayList로 저장하여 반환 합니다.
	 * 과정, 과목, 강의일자, 강의실 교재 등의 정보를 포함합니다.
	 * @param 교사번호 
	 * @return 교사정보객체 배열
	 */
	public ArrayList<TeacherDTO> getDetail(String seq) {
		
		try {
			
			// 입력받은 seq에 해당하는 교사를 출력하는 프로시저
			String sql = "{ call procShowTeacherDetail(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, seq); // 입력 받은 교사번호
			cstat.registerOutParameter(2, OracleTypes.CURSOR); //커서 타입
			
			cstat.executeQuery();
			
			//Oracle(cursor) -> JDBC(ResultSet)
			ResultSet rs = (ResultSet)cstat.getObject(2); //다운캐스팅
			
			ArrayList<TeacherDTO> list = new ArrayList<TeacherDTO>();
			
			while (rs.next()) {
				
				TeacherDTO dto = new TeacherDTO();
				
				dto.setName(rs.getString("교사명"));
				dto.setCourse(rs.getString("과정명"));
				dto.setSubject(rs.getString("과목명"));
				dto.setStartDate(rs.getString("시작일"));
				dto.setEndDate(rs.getString("종료일"));
				dto.setState(rs.getString("진행상태"));
				dto.setLectureroom(rs.getString("강의실"));
				dto.setBook(rs.getString("교재"));
				
				list.add(dto);
			} 
			
			return list;
				
		} catch (Exception e) {
			System.out.println("TeacherDAO.getDetail()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	

	
	/**
	 * 특정 교사 정보를 출력하는 메소드 입니다.
	 * 교사번호를 받아 해당하는 교사 정보를 반환합니다.
	 * @param 교사 번호
	 * @return 교사정보 객체
	 */
	public TeacherDTO get(String seq) {
		
		try {
			
			// 입력받은 seq에 해당하는 교사를 출력
			String sql = "select * from tblTeacher where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {

				TeacherDTO dto = new TeacherDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setIdNum(rs.getString("idNum"));
				dto.setTel(rs.getString("tel"));
	
				return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 교사정보 수정 메소드 입니다.
	 * 수정할 교사정보 데이터를 받아 DB에서 수정하고 성공여부를 반환 합니다.
	 * @param 교사정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int edit(TeacherDTO dto2) {
		
		try {
			
			String sql = "update tblTeacher set name=?, idNum=?, tel=? where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getIdNum());
			pstat.setString(3, dto2.getTel());
			pstat.setString(4, dto2.getSeq());
			
			return pstat.executeUpdate(); //적용된 행의갯수(1) 반환
			
			
		} catch (Exception e) {
			System.out.println("TeacherDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}


	

	/**
	 * 교사 삭제 메소드 입니다.
	 * 교사 번호를 받아 해당하는 교사 데이터를 DB에서 삭제하고 성공여부를 반환합니다. 
	 * @param 교사번호
	 * @return 1(성공) / 0(실패)
	 */
	public int delete(String seq) {
		
		try {
			
			String sql = "delete from tblTeacher where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
					
			return pstat.executeUpdate(); // 1 or 0		
					
		} catch (Exception e) {
			System.out.println("TeacherDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	/**
	 * 강의가능과목 삭제 메소드 입니다.
	 * 교사 번호를 받아 해당하는 교사의 강의가능과목 데이터를 DB에서 삭제하고 성공여부를 반환합니다. 
	 * @param 교사번호
	 * @return 1(성공) / 0(실패)
	 */
	public int deletePossibleSubject(String seq) {
		
		try {
			
			String sql = "delete from tblPossibleSubject where teacherSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
					
			return pstat.executeUpdate(); // 삭제된 레코드 갯수 반환	
					
		} catch (Exception e) {
			System.out.println("TeacherDAO.deletePossibleSubject()");
			e.printStackTrace();
		}
		return 0;
	}
	




}
