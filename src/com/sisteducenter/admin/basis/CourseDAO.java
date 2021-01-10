package com.sisteducenter.admin.basis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;


/**
 * 과정관리에 관련된 기능을 수행하는 클래스 입니다.
 * 전체 과정 조회, 과정 검색, 등록, 수정, 삭제 기능을 수행하여 DB에 반영합니다.
 * @author 권주홍
 */
// 과정 관리 Data Access Object
public class CourseDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	/**
	 * 과정 DAO의 기본 생성자 입니다.
	 */
	public CourseDAO() {
		
		try {

			this.conn = DBUtil.open();
			this.stat = conn.createStatement();	
			
		} catch (Exception e) {
			System.out.println("CourseDAO.CourseDAO()");
			e.printStackTrace();
		}
		
	}

	
	
	/**
	 * 과정 등록 메소드 입니다.
	 * 과정 정보 데이터를 받아 DB에 등록하고 성공 여부를 반환합니다.
	 * @param 과정정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int add(CourseDTO dto) {
		
		try {
			
			String sql = "insert into tblCourse (seq, name, period) values (seqtblCourse.nextVal, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPeriod());
			
			return pstat.executeUpdate(); // 적용된 행의 갯수 반환됨 -> 1
			
		} catch (Exception e) {
			System.out.println("CourseDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	
	/**
	 * 과정 조회 / 검색 메소드 입니다.
	 * DB의 과정 데이터 전체목록 또는 특정 단어가 포함된 데이터를 ArrayList로 저장하여 반환 합니다.
	 * @param null(조회기능) / word(검색기능)
	 * @return 과정정보 객체의 배열
	 */
	public ArrayList<CourseDTO> list(String word) {
		
		//word -> null -> 목록보기
		//word -> "???" -> 검색하기
		
		try {
			
			String where = "";
			
			// 이름에 word가 포함된 레코드 검색하기
			if(word != null) {
				where = String.format("where name like '%%%s%%'", word);	
			}
			
			// 과정 전체 목록 조회
			String sql = String.format("select * from tblCourse %s order by seq", where);
			
			rs = stat.executeQuery(sql);
			
			ArrayList<CourseDTO> list = new ArrayList<CourseDTO>();
			
			while (rs.next()) {
				
				CourseDTO dto = new CourseDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setPeriod(rs.getString("period"));
				
				list.add(dto);
			} 
			
			return list;
			
		} catch (Exception e) {
			System.out.println("CourseDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	

	
	
	/**
	 * 특정 과정 정보를 출력하는 메소드 입니다.
	 * 과정번호를 받아 해당하는 과정 정보를 반환한다. 
	 * @param 과정 번호
	 * @return 과정정보 객체
	 */
	public CourseDTO get(String seq) {
		
		try {
			
			// 입력받은 seq에 해당하는 과정을 출력
			String sql = "select * from tblCourse where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {

				CourseDTO dto = new CourseDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setPeriod(rs.getString("period"));
	
				return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("CourseDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	/**
	 * 과정정보 수정 메소드 입니다.
	 * 수정할 과정정보 데이터를 받아 DB에서 수정하고 성공여부를 반환 합니다.
	 * @param 과정정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int edit(CourseDTO dto2) {
		
		try {
			
			String sql = "update tblCourse set name=?, period=? where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getName());
			pstat.setString(2, dto2.getPeriod());
			pstat.setString(3, dto2.getSeq());
			
			return pstat.executeUpdate(); //적용된 행의갯수(1) 반환
			
			
		} catch (Exception e) {
			System.out.println("CourseDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	
	/**
	 * 과정 삭제 메소드 입니다.
	 * 과정 번호를 받아 해당하는 과정 데이터를 DB에서 삭제하고 성공여부를 반환합니다. 
	 * @param 과정번호
	 * @return 1(성공) / 0(실패)
	 */
	public int delete(String seq) {
		
		try {
			
			String sql = "delete from tblCourse where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
					
			return pstat.executeUpdate(); // 1 or 0		
					
		} catch (Exception e) {
			System.out.println("CourseDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}
	
}
