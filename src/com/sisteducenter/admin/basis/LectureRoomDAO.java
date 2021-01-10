package com.sisteducenter.admin.basis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;


/**
 * 강의실관리에 관련된 기능을 수행하는 클래스 입니다.
 * 전체 강의실 조회, 강의실 검색, 등록, 수정, 삭제 기능을 수행하여 DB에 반영합니다.
 * @author 권주홍
 */
public class LectureRoomDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;

	
	
	/**
	 * 강의실 DAO의 기본 생성자 입니다.
	 */

	public LectureRoomDAO() {
		
		try {

			this.conn = DBUtil.open();
			this.stat = conn.createStatement();	
			
		} catch (Exception e) {
			System.out.println("LectureRoomDAO.LectureRoomDAO()");
			e.printStackTrace();
		}
		
	}

	
	
	/**
	 * 강의실 등록 메소드 입니다.
	 * 강의실 정보 데이터를 받아 DB에 등록하고 성공 여부를 반환합니다.
	 * @param 강의실정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int add(LectureRoomDTO dto) {
		
		try {
			
			String sql = "insert into tblLectureRoom (seq, capacity) values (seqtblLectureRoom.nextVal, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getCapacity());
			
			return pstat.executeUpdate(); // 적용된 행의 갯수 반환됨 -> 1
			
		} catch (Exception e) {
			System.out.println("LectureRoomDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
	
	/**
	 * 강의실 조회 / 검색 메소드 입니다.
	 * DB의 강의실 데이터 전체목록 또는 특정 단어가 포함된 데이터를 ArrayList로 저장하여 반환 합니다.
	 * @param null(조회기능) / word(검색기능)
	 * @return 강의실정보 객체의 배열
	 */
	public ArrayList<LectureRoomDTO> list(String word) {
		
		//word -> null -> 목록보기
		//word -> "???" -> 검색하기
		
		try {
			
			String where = "";
			
			// 정원이 word인 레코드 검색하기
			if(word != null) {
				where = String.format("where capacity = %s", word);	
			}
			
			// 강의실 전체 목록 조회
			String sql = String.format("select * from tblLectureRoom %s order by seq", where);
			
			rs = stat.executeQuery(sql);
			
			ArrayList<LectureRoomDTO> list = new ArrayList<LectureRoomDTO>();
			
			while (rs.next()) {
				
				LectureRoomDTO dto = new LectureRoomDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setCapacity(rs.getString("capacity"));
				
				list.add(dto);
			} 
			
			return list;
			
		} catch (Exception e) {
			System.out.println("LectureRoomDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	

	

	
	/**
	 * 특정 강의실 정보를 출력하는 메소드 입니다.
	 * 강의실번호를 받아 해당하는 강의실 정보를 반환합니다.
	 * @param 강의실 번호
	 * @return 강의실정보 객체
	 */
	public LectureRoomDTO get(String seq) {
		
		try {
			
			// 입력받은 seq에 해당하는 강의실를 출력
			String sql = "select * from tblLectureRoom where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {

				LectureRoomDTO dto = new LectureRoomDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setCapacity(rs.getString("capacity"));
	
				return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("LectureRoomDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	/**
	 * 강의실정보 수정 메소드 입니다.
	 * 수정할 강의실정보 데이터를 받아 DB에서 수정하고 성공여부를 반환 합니다.
	 * @param 강의실정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int edit(LectureRoomDTO dto2) {
		
		try {
			
			String sql = "update tblLectureRoom set capacity=? where seq=?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto2.getCapacity());
			pstat.setString(2, dto2.getSeq());
			
			return pstat.executeUpdate(); //적용된 행의갯수(1) 반환
			
			
		} catch (Exception e) {
			System.out.println("LectureRoomDAO.edit()");
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	
	/**
	 * 강의실 삭제 메소드 입니다.
	 * 강의실 번호를 받아 해당하는 강의실 데이터를 DB에서 삭제하고 성공여부를 반환합니다. 
	 * @param 강의실번호
	 * @return 1(성공) / 0(실패)
	 */
	public int delete(String seq) {
		
		try {
			
			String sql = "delete from tblLectureRoom where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
					
			return pstat.executeUpdate(); // 1 or 0		
					
		} catch (Exception e) {
			System.out.println("LectureRoomDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
}
