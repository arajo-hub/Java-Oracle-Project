package com.sisteducenter.admin.jobinfo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.sisteducenter.start.DBUtil;


/**
 * 구인공고관리에 관련된 기능을 수행하는 클래스 입니다.
 * 전체 구인공고 조회, 상세내용 조회, 공고 검색, 등록, 수정, 삭제 기능을 수행하여 DB에 반영합니다.
 * @author 권주홍
 */
public class JobInfoDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private CallableStatement cstat;
	private ResultSet rs;
	
	
	/**
	 * 구인공고 DAO의 기본 생성자 입니다.
	 */
	public JobInfoDAO() {
		
		try {

			this.conn = DBUtil.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.JobInfoDAO()");
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 구인공고 조회 / 검색 메소드 입니다.
	 * DB의 구인공고 데이터 전체목록 또는 특정 단어가 포함된 데이터를 ArrayList로 저장하여 반환 합니다.
	 * @param null(조회기능) / word(검색기능)
	 * @return 구인공고정보 객체의 배열
	 */
	public ArrayList<JobInfoDTO> list(String word) {
		
		//word -> null -> 목록보기
		//word -> "???" -> 검색하기
		
		try {
			
			String where = "";
			
			// 회사명에 word가 포함된 구인공고 검색하기
			if(word != null) {
				where = String.format("where 회사 like '%%%s%%'", word);	
			}
			
			// 구인공고 전체 목록 출력 (간단조회)
			String sql = String.format("select * from vwJobInfoList %s", where);
			
			rs = stat.executeQuery(sql);
			
			ArrayList<JobInfoDTO> list = new ArrayList<JobInfoDTO>();
			
			while (rs.next()) {
				
				JobInfoDTO dto = new JobInfoDTO();
				
				dto.setSeq(rs.getString("No"));
				dto.setCompany(rs.getString("회사"));
				dto.setIndustry(rs.getString("업종"));
				dto.setJobDivision(rs.getString("모집직군"));
				dto.setJobType(rs.getString("근무형태"));
				dto.setAnnualIncome(rs.getString("연봉"));
//				dto.setStartDate(rs.getString("공고시작일"));
//				dto.setEndDate(rs.getString("공고마감일"));
				dto.setState(rs.getString("진행상태"));
//				dto.setRecruitStep(rs.getString("채용단계"));
//				dto.setDetail(rs.getString("내용"));
				
				list.add(dto);
			} 
			
			return list;
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.list()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	/**
	 * 특정 구인공고 정보를 출력하는 메소드 입니다.
	 * 구인공고번호를 받아 해당하는 구인공고 정보를 반환합니다.
	 * @param 구인공고 번호
	 * @return 구인공고정보 객체
	 */
	public JobInfoDTO getDetail(String seq) {
		
		try {
			
			// 입력받은 seq에 해당하는 구인공고를 출력
			String sql = "select * from vwJobInfoList where No = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {

				JobInfoDTO dto = new JobInfoDTO();
				
				dto.setSeq(rs.getString("No"));
				dto.setCompanySeq(rs.getString("cseq")); //출력은 안함
				dto.setCompany(rs.getString("회사"));
				dto.setIndustry(rs.getString("업종"));
				dto.setJobDivision(rs.getString("모집직군"));
				dto.setJobType(rs.getString("근무형태"));
				dto.setAnnualIncome(rs.getString("연봉"));
				dto.setStartDate(rs.getString("공고시작일"));
				dto.setEndDate(rs.getString("공고마감일"));
				dto.setState(rs.getString("진행상태"));
				dto.setRecruitStep(rs.getString("채용단계"));
				dto.setDetail(rs.getString("내용"));
	
				return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.getDetail()");
			e.printStackTrace();
		}
		
		return null;
	}

	
		
	/**
	 * 회사 목록 출력 메소드 입니다.
	 * DB의 회사 데이터 전체목록을 반환합니다.
	 * @return 회사정보 객체 배열
	 */
	public ArrayList<CompanyDTO> listCompany() {

		try {
			
			// 회사 전체 목록 출력
			String sql = String.format("select * from vwCompanyList");
			
			rs = stat.executeQuery(sql);
			
			ArrayList<CompanyDTO> clist = new ArrayList<CompanyDTO>();
			
			while (rs.next()) {
				
				CompanyDTO dto = new CompanyDTO();
				
				dto.setSeq(rs.getString("No"));
				dto.setIndustryName(rs.getString("업종"));
				dto.setName(rs.getString("회사명"));
				dto.setCeo(rs.getString("대표"));
				dto.setEstablishDate(rs.getString("설립일"));
				dto.setEmployeeCount(rs.getString("사원수"));
				dto.setSales(rs.getString("매출액"));
				dto.setTel(rs.getString("연락처"));
				dto.setAddress(rs.getString("주소"));
				
				clist.add(dto);
			} 
			
			return clist;
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.listCompany()");
			e.printStackTrace();
		}
		
		return null;
	}


		
	
	
	
	/**
	 * 구인공고 등록 메소드 입니다.
	 * 구인공고 정보 데이터를 받아 DB에 등록하고 성공 여부를 반환합니다.
	 * @param 구인공고정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int add(JobInfoDTO dto) {
		
		try {
			
			String sql = "insert into tblJobInfo (seq, jobDivision, jobType, annualIncome, recruitStep, detail, companySeq)" + 
					"    values (seqTblJobInfo.nextVal, ?, ?, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getJobDivision());
			pstat.setString(2, dto.getJobType());
			pstat.setString(3, dto.getAnnualIncome());
			pstat.setString(4, dto.getRecruitStep());
			pstat.setString(5, dto.getDetail());
			pstat.setString(6, dto.getCompanySeq());
			
			int result = pstat.executeUpdate(); // 적용된 행의 갯수 반환됨 -> 1

			
			return result;
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.add()");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	/**
	 * 구인공고기간 등록 메소드 입니다.
	 * 구인공고기간 정보 데이터를 받아 DB에 등록하고 성공 여부를 반환합니다.
	 * @param 구인공고정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int addJobNoticePeriod(JobInfoDTO dto) {
		
		try {
			
			String sql = "insert into tblJobNoticePeriod (seq, startdate, enddate, jobinfoseq)" + 
					"    values (seqTblJobNoticePeriod.nextVal, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getStartDate());
			pstat.setString(2, dto.getEndDate());
			
			//공고 마지막등록된 seq값 출력
			sql = "select max(seq) as maxSeq from tblJobInfo";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				pstat.setString(3, rs.getString("maxSeq"));				
			}


			return pstat.executeUpdate(); // 적용된 행의 갯수 반환됨 -> 1
			
	
			
		} catch (Exception e) {
			System.out.println("JobInfoDAO.addJobNoticePeriod()");
			e.printStackTrace();
		}
		
		return 0;
	}



		
	/**
	 * 구인공고정보 수정 메소드 입니다.
	 * 수정할 구인공고정보 데이터를 받아 DB에서 기존공고 데이터 삭제 후 수정내용으로 재등록하고 성공여부를 반환 합니다.
	 * @param 구인공고정보 객체
	 * @return 1(성공) / 0(실패)
	 */
	public int edit(JobInfoDTO dto) {
		
		//공고번호
		String seq = dto.getSeq();

		//해당공고 삭제
		delete(seq);
		
		//수정된 내용으로 공고 재등록
		int result = add(dto);
		int result2 = addJobNoticePeriod(dto);
		
		return result * result2;
	

	}
	

	
	/**
	 * 구인공고 삭제 메소드 입니다.
	 * 구인공고 번호를 받아 해당하는 구인공고기간 데이터를 DB에서 삭제하고 성공여부를 반환합니다. 
	 * @param 구인공고번호
	 * @return 1(성공) / 0(실패)
	 */
	public int delete(String seq) {
		
		try {
			
			// 입력된 공고번호에 해당하는 공고기간 레코드 삭제
			String sql = String.format("delete from tblJobNoticePeriod" + 
					"	where seq =" + 
					"	(select seq from tblJobNoticePeriod" + 
					"    where jobinfoseq = (select seq from vwJobInfolist where No = %s)" + 
					"    and startdate = (select 공고시작일 from vwJobinfolist where No = %s)" + 
					"    and enddate = (select 공고마감일 from vwJobinfolist where No = %s))", seq, seq, seq); 
			
			stat = conn.createStatement();
				
			return stat.executeUpdate(sql); // 1 or 0		
					
		} catch (Exception e) {
			System.out.println("JobInfoDAO.delete()");
			e.printStackTrace();
		}
		return 0;
	}
	


}
