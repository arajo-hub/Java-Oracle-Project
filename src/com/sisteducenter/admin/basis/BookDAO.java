package com.sisteducenter.admin.basis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

/**
 * 교재관리에 관련된 기능을 수행하는 클래스 입니다.
 * 전체 교재 조회, 교재 검색, 등록, 수정, 삭제 기능을 수행하여 DB에 반영합니다.
 * @author 권주홍
 */
public class BookDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	/**
	 * 교재 DAO의 기본 생성자 입니다.
	 */
	public BookDAO() {
		
		try {
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();	
		} catch (Exception e) {
			System.out.println("BookDAO.BookDAO()");
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 교재 등록 메소드 입니다.
	 * 교재 정보 데이터를 받아 DB에 등록하고 성공 여부를 반환합니다.
	 * @param 교재정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int add(BookDTO dto) {
		
		try {
			
			String sql = "insert into tblBook (seq, title, publisher) values (seqtblBook.nextVal, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getPublisher());
			
			return pstat.executeUpdate(); // 적용된 행의 갯수 반환됨 -> 1
			
		} catch (Exception e) {
			System.out.println("BookDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	/**
	 * 교재 조회 / 검색 메소드 입니다.
	 * DB의 교재 데이터 전체목록 또는 특정 단어가 포함된 데이터를 ArrayList로 저장하여 반환 합니다.
	 * @param null(조회기능) / word(검색기능)
	 * @return 교재정보 객체의 배열
	 */
	public ArrayList<BookDTO> list(String word) {
		
		//word -> null -> 목록보기
		//word -> "???" -> 검색하기
		
		try {
			
			String where = "";
			
			// 제목에 word가 포함된 레코드 검색하기
			if(word != null) {
				where = String.format("where title like '%%%s%%'", word);	
			}
			
			// 교재 전체 목록 조회
			String sql = String.format("select * from tblBook %s order by seq", where);
			
			rs = stat.executeQuery(sql);
			
			ArrayList<BookDTO> list = new ArrayList<BookDTO>();
			
			while (rs.next()) {
				
				BookDTO dto = new BookDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setPublisher(rs.getString("publisher"));
				
				list.add(dto);
			} 
			
			return list;
			
		} catch (Exception e) {
			System.out.println("BookDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 특정 교재 정보를 출력하는 메소드 입니다.
	 * 교재번호를 받아 해당하는 교재 정보를 반환합니다.
	 * @param 교재 번호
	 * @return 교재정보 객체
	 */
	public BookDTO get(String seq) {
		
		try {
			
			// 입력받은 seq에 해당하는 교재를 출력
			String sql = "select * from tblBook where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {

				BookDTO dto = new BookDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setPublisher(rs.getString("publisher"));
	
				return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("BookDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	/**
	 * 교재정보 수정 메소드 입니다.
	 * 수정할 교재정보 데이터를 받아 DB에서 수정하고 성공여부를 반환 합니다.
	 * @param 교재정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int edit(BookDTO dto) {
		
		try {
			
			String sql = "update tblBook set title=?, publisher=? where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getPublisher());
			pstat.setString(3, dto.getSeq());
			
			return pstat.executeUpdate(); //적용된 행의갯수(1) 반환
			
			
		} catch (Exception e) {
			System.out.println("BookDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	
	/**
	 * 교재 삭제 메소드 입니다.
	 * 교재 번호를 받아 해당하는 교재 데이터를 DB에서 삭제하고 성공여부를 반환합니다. 
	 * @param 교재번호
	 * @return 1(성공) / 0(실패)
	 */
	public int delete(String seq) {
		
		try {
			
			String sql = "delete from tblBook where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
					
			return pstat.executeUpdate(); // 1 or 0		
					
		} catch (Exception e) {
			System.out.println("BookDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
}
