package com.sisteducenter.admin.basis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;


/**
 * 과목관리에 관련된 기능을 수행하는 클래스 입니다.
 * 전체 과목 조회, 과목 검색, 등록, 수정, 삭제 기능을 수행하여 DB에 반영합니다.
 * @author 권주홍
 */
public class SubjectDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	/**
	 * 과목 DAO의 기본 생성자 입니다.
	 */
	public SubjectDAO() {
		
		try {

			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("SubjectDAO.SubjectDAO()");
			e.printStackTrace();
		}
		
	}

	
	
	/**
	 * 과목 등록 메소드 입니다.
	 * 과목 정보 데이터를 받아 DB에 등록하고 성공 여부를 반환합니다.
	 * @param 과목정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int add(SubjectDTO dto) {
		
		try {
			
			String sql = "insert into tblSubject (seq, subjectname, division, period, bookseq) values (seqtblSubject.nextVal, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getDivision());			
			pstat.setString(3, dto.getPeriod());
			pstat.setString(4, dto.getBookSeq());
			
			return pstat.executeUpdate(); // 적용된 행의 갯수 반환됨 -> 1
			
		} catch (Exception e) {
			System.out.println("SubjectDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	
	/**
	 * 과목 조회 / 검색 메소드 입니다.
	 * DB의 과목 데이터 전체목록 또는 특정 단어가 포함된 데이터를 ArrayList로 저장하여 반환 합니다.
	 * @param null(조회기능) / word(검색기능)
	 * @return 과목정보 객체의 배열
	 */
	public ArrayList<SubjectDTO> list(String word) {
		
		//word -> null -> 목록보기
		//word -> "???" -> 검색하기
		
		try {
			
			String where = "";
			
			// 이름에 word가 포함된 레코드 검색하기
			if(word != null) {
				where = String.format("where subjectName like '%%%s%%'", word);	
			}
			
			// 과목 전체 목록 조회
			String sql = String.format("select s.seq as seq, subjectName, division, period, b.seq as bookSeq, b.title as bookTitle from tblSubject s" + 
					"    left outer join tblBook b" + 
					"        on s.Bookseq = b.seq" +
					" %s" +
					" order by s.seq", where);
			
			rs = stat.executeQuery(sql);
			
			ArrayList<SubjectDTO> list = new ArrayList<SubjectDTO>();
			
			while (rs.next()) {
				
				SubjectDTO dto = new SubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("subjectName"));
				dto.setDivision(rs.getString("division"));
				dto.setPeriod(rs.getString("period"));
//				dto.setBookSeq(rs.getString("bookSeq"));
				dto.setBookTitle(rs.getString("bookTitle")); // 사용 교재제목 출력
				
				list.add(dto);
			} 
			
			return list;
			
		} catch (Exception e) {
			System.out.println("SubjectDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	

	

	
	
	/**
	 * 특정 과목 정보를 출력하는 메소드 입니다.
	 * 과목번호를 받아 해당하는 과목 정보를 반환합니다.
	 * @param 과목 번호
	 * @return 과목정보 객체
	 */
	public SubjectDTO get(String seq) {
		
		try {
			
			// 입력받은 seq에 해당하는 과목을 출력
			String sql = "select s.seq as seq, subjectName, division, period, b.seq as bookSeq, b.title as bookTitle from tblSubject s" + 
					"    left outer join tblBook b" + 
					"        on s.bookseq = b.seq" +
					"  where s.seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {

				SubjectDTO dto = new SubjectDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("subjectName"));
				dto.setDivision(rs.getString("division"));
				dto.setPeriod(rs.getString("period"));
				dto.setBookSeq(rs.getString("bookSeq"));
				dto.setBookTitle(rs.getString("bookTitle"));
	
				return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("SubjectDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	/**
	 * 과목정보 수정 메소드 입니다.
	 * 수정할 과목정보 데이터를 받아 DB에서 수정하고 성공여부를 반환 합니다.
	 * @param 과목정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int edit(SubjectDTO dto2) {
		
		try {
			
			String sql = "update tblSubject set subjectname=?, division=?, period=?, bookseq=? where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getDivision());
			pstat.setString(3, dto2.getPeriod());
			pstat.setString(4, dto2.getBookSeq());
			pstat.setString(5, dto2.getSeq());
			
			return pstat.executeUpdate(); //적용된 행의갯수(1) 반환
			
			
		} catch (Exception e) {
			System.out.println("SubjectDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	
	
	/**
	 * 과목 삭제 메소드 입니다.
	 * 과목 번호를 받아 해당하는 과목 데이터를 DB에서 삭제하고 성공여부를 반환합니다. 
	 * @param 과목번호
	 * @return 1(성공) / 0(실패)
	 */
	public int delete(String seq) {
		
		try {
			
			String sql = "delete from tblSubject where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
					
			return pstat.executeUpdate(); // 1 or 0		
					
		} catch (Exception e) {
			System.out.println("SubjectDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
