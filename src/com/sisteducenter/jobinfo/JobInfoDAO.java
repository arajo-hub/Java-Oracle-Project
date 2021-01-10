package com.sisteducenter.jobinfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;

/**
 * 구직정보를 다루기 위한 DAO입니다.
 * 구직정보를 데이터베이스로부터 가져올 수 있습니다.
 * @author 조아라
 *
 */
public class JobInfoDAO {

	private static Connection conn = null;
	private static Statement stat=null;
	private static ResultSet rs=null;
	
	/**
	 * 현재 진행중인 구직정보를 데이터베이스로부터 가져오는 메서드입니다.
	 * @return 구직정보(JobInfoListDTO)가 담긴 ArrayList를 반환합니다.
	 */
	public static ArrayList<JobInfoListDTO> getInfo() {
		
		ArrayList<JobInfoListDTO> list=new ArrayList<JobInfoListDTO>();
		
		try {
			
			// 1. 데이터베이스 연결
			
			conn=DBUtil.open();
			stat=conn.createStatement();
			
			// 2. 프로시저 실행
			
			String sql="select * from vwShowJobInfo order by vwShowJobInfo.startDate desc";
			
			// 3. 쿼리 실행
			rs=stat.executeQuery(sql);
		
			// 4. 프로시저로부터 결과값 반환.
			while (rs.next()) {
				
				JobInfoListDTO dto=new JobInfoListDTO();
				
				dto.setCompany(rs.getString(1));
				dto.setIndustryName(rs.getString(2));
				dto.setSales(rs.getString(3));
				dto.setJobDivision(rs.getString(4));
				dto.setJobType(rs.getString(5));
				dto.setAnnualIncome(rs.getInt(6));
				dto.setRecruitStep(rs.getString(7));
				dto.setDetail(rs.getString(8));
				dto.setStartDate(rs.getString(9));
				dto.setEndDate(rs.getString(10));
				
				list.add(dto);
			}
			
			// 자원 해제.
			rs.close();
			stat.close();
			return list;
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.getInfo()");
			e.printStackTrace();
		}
		return null;
		
	}
}
