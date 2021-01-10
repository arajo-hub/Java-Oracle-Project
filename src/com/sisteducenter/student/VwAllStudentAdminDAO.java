package com.sisteducenter.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

/**
 * 
 * @author 이준오
 *
 */
public class VwAllStudentAdminDAO {


	private Connection conn;
	private Statement stat; //매개변수X
	private PreparedStatement pstat; //매개변수O
	private ResultSet rs;
	
	public VwAllStudentAdminDAO() {
		
		try {
			
			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @return View(vwAllStudent)를 통한 교육생 정보 list로 반환
	 * 
	 */
	public ArrayList<VwAllStudentAdminDTO> list() {
		
		try {

			String sql = "select studentSeq, studentName, idNumber, telNumber, regDate, count from vwAllStudent";
			
			rs = stat.executeQuery(sql);
			
			ArrayList<VwAllStudentAdminDTO> list = new ArrayList<VwAllStudentAdminDTO>();
			while (rs.next()) {
				VwAllStudentAdminDTO dto = new VwAllStudentAdminDTO();
				
				
				dto.setStudentSeq(rs.getString("studentSeq"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setIdNumber(rs.getString("idNumber"));
				dto.setTelNumber(rs.getString("telNumber"));
				dto.setRegDate(rs.getString("regDate"));
				dto.setCount(rs.getString("count"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			System.out.println("vwAllStudentDAO.list()");
			e.printStackTrace();
		}
		
		
		return null;
	}

	/**
	 * 
	 * @param seq 검색할 교육생 번호
	 * @return View(vwAllStudent)를 통한 해당 교육생 list로 반환
	 */
	public ArrayList<VwAllStudentAdminDTO> getSeq(String seq) {
		try {

			String sql = "select studentSeq, studentName, idNumber, telNumber, regDate, count from vwAllStudent where studentSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			ArrayList<VwAllStudentAdminDTO> list1 = new ArrayList<VwAllStudentAdminDTO>();
			while(rs.next()) {
				VwAllStudentAdminDTO dto1 = new VwAllStudentAdminDTO();
				
								dto1.setStudentSeq(rs.getString("studentSeq"));
				dto1.setStudentName(rs.getString("studentName"));
				dto1.setIdNumber(rs.getString("idNumber"));
				dto1.setTelNumber(rs.getString("telNumber"));
				dto1.setRegDate(rs.getString("regDate"));
				dto1.setCount(rs.getString("count"));
				
				list1.add(dto1);
				
			}
			return list1;
			

		} catch (Exception e) {
			System.out.println("VwAllStudentDAO.get()");
			e.printStackTrace();
		}
		
		return null;
				
	}

	/**
	 * 
	 * @param name 검색할 교육생 이름
	 * @return View(vwAllStudent)를 통한 해당 교육생 list로 반환
	 */
	public ArrayList<VwAllStudentAdminDTO> getName(String name) {
		try {

			String sql = "select studentSeq, studentName, idNumber, telNumber, regDate, count from vwAllStudent where studentName = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			rs = pstat.executeQuery();
			ArrayList<VwAllStudentAdminDTO> list1 = new ArrayList<VwAllStudentAdminDTO>();
			while(rs.next()) {
				VwAllStudentAdminDTO dto1 = new VwAllStudentAdminDTO();
				
				dto1.setStudentSeq(rs.getString("studentSeq"));
				dto1.setStudentName(rs.getString("studentName"));
				dto1.setIdNumber(rs.getString("idNumber"));
				dto1.setTelNumber(rs.getString("telNumber"));
				dto1.setRegDate(rs.getString("regDate"));
				dto1.setCount(rs.getString("count"));
				
				list1.add(dto1);
				
			}
			return list1;
			

		} catch (Exception e) {
			System.out.println("VwAllStudentDAO.get()");
			e.printStackTrace();
		}
		
		return null;
				
	}

	/**
	 * 
	 * @param idNum 검색할 교육생의 주민번호 뒷자리
	 * @return View(vwAllStudent)를 통한 해당 교육생 list로 반환
	 */
	public ArrayList<VwAllStudentAdminDTO> getIdNum(String idNum) {
		try {

			String sql = "select studentSeq, studentName, idNumber, telNumber, regDate, count from vwAllStudent where idNumber = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, idNum);
			rs = pstat.executeQuery();
			ArrayList<VwAllStudentAdminDTO> list1 = new ArrayList<VwAllStudentAdminDTO>();
			while(rs.next()) {
				VwAllStudentAdminDTO dto1 = new VwAllStudentAdminDTO();
				
				dto1.setStudentSeq(rs.getString("studentSeq"));
				dto1.setStudentName(rs.getString("studentName"));
				dto1.setIdNumber(rs.getString("idNumber"));
				dto1.setTelNumber(rs.getString("telNumber"));
				dto1.setRegDate(rs.getString("regDate"));
				dto1.setCount(rs.getString("count"));
				
				list1.add(dto1);
				
			}
			return list1;
			

		} catch (Exception e) {
			System.out.println("VwAllStudentDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @param tel 검색할 교육생의 전화번호
	 * @return View(vwAllStudent)를 통한 해당 교육생 list로 반환
	 */
	public ArrayList<VwAllStudentAdminDTO> getTel(String tel) {
		try {

			String sql = "select studentSeq, studentName, idNumber, telNumber, regDate, count from vwAllStudent where telNumber = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, tel);
			rs = pstat.executeQuery();
			ArrayList<VwAllStudentAdminDTO> list1 = new ArrayList<VwAllStudentAdminDTO>();
			while(rs.next()) {
				VwAllStudentAdminDTO dto1 = new VwAllStudentAdminDTO();
				
				dto1.setStudentSeq(rs.getString("studentSeq"));
				dto1.setStudentName(rs.getString("studentName"));
				dto1.setIdNumber(rs.getString("idNumber"));
				dto1.setTelNumber(rs.getString("telNumber"));
				dto1.setRegDate(rs.getString("regDate"));
				dto1.setCount(rs.getString("count"));
				
				list1.add(dto1);
				
			}
			return list1;
			

		} catch (Exception e) {
			System.out.println("VwAllStudentDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @param regDate 검색할 교육생의 등록일
	 * @return View(vwAllStudent)를 통한 해당 교육생 list로 반환
	 */
	public ArrayList<VwAllStudentAdminDTO> getRegDate(String regDate) {
		try {

			String sql = "select studentSeq, studentName, idNumber, telNumber, regDate, count from vwAllStudent where to_char(regDate,'YYYY-MM-DD') = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, regDate);
			rs = pstat.executeQuery();
			ArrayList<VwAllStudentAdminDTO> list1 = new ArrayList<VwAllStudentAdminDTO>();
			while(rs.next()) {
				VwAllStudentAdminDTO dto1 = new VwAllStudentAdminDTO();
				
				dto1.setStudentSeq(rs.getString("studentSeq"));
				dto1.setStudentName(rs.getString("studentName"));
				dto1.setIdNumber(rs.getString("idNumber"));
				dto1.setTelNumber(rs.getString("telNumber"));
				dto1.setRegDate(rs.getString("regDate"));
				dto1.setCount(rs.getString("count"));
				
				list1.add(dto1);
				
			}
			return list1;
			

		} catch (Exception e) {
			System.out.println("VwAllStudentDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 
	 * @param count 검색할 교육생의 수강(신청) 횟수
	 * @return View(vwAllStudent)를 통한 해당 교육생 list로 반환
	 */
	public ArrayList<VwAllStudentAdminDTO> getCount(String count) {
		try {

			String sql = "select studentSeq, studentName, idNumber, telNumber, regDate, count from vwAllStudent where count = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, count);
			rs = pstat.executeQuery();
			ArrayList<VwAllStudentAdminDTO> list1 = new ArrayList<VwAllStudentAdminDTO>();
			while(rs.next()) {
				VwAllStudentAdminDTO dto1 = new VwAllStudentAdminDTO();
				
				dto1.setStudentSeq(rs.getString("studentSeq"));
				dto1.setStudentName(rs.getString("studentName"));
				dto1.setIdNumber(rs.getString("idNumber"));
				dto1.setTelNumber(rs.getString("telNumber"));
				dto1.setRegDate(rs.getString("regDate"));
				dto1.setCount(rs.getString("count"));
				
				list1.add(dto1);
				
			}
			return list1;
			

		} catch (Exception e) {
			System.out.println("VwAllStudentDAO.get()");
			e.printStackTrace();
		}
		
		return null;
	}


	

}
