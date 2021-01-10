package com.sisteducenter.admin.jobinfo;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * 구인공고관리 메뉴를 출력하고 기능별 메소드를 실행하는 클래스 입니다.
 * 전체 구인공고 조회, 상세내용 조회, 공고 검색, 등록, 수정, 삭제 기능을 포함합니다.
 * @author 권주홍
 */
public class JobInfo {
	
	private static Scanner scan;
	private static JobInfoDAO dao;
	
	
	
	/**
	 * 구인공고관리 클래스의 기본생성자 입니다.
	 */
	static {  // 생성자
		
		scan = new Scanner(System.in);
		dao = new JobInfoDAO(); // DB 담당 클래스
	}
	
	
	
	
	/**
	 * 구인공고관리 메뉴를 출력하는 메소드입니다.
	 */
	public static void intro() {
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println(" 구인공고 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			
			// 구인공고 전체 목록 출력
			ArrayList<JobInfoDTO> list  = dao.list(null);
			list(list); 

			System.out.println();
			System.out.println("1. 구인공고 상세내용 조회");
			System.out.println("2. 구인공고 검색");
			System.out.println("3. 구인공고 등록");
			System.out.println("4. 구인공고 수정");
			System.out.println("5. 구인공고 삭제");
			System.out.println();
			System.out.println("0. 메인으로");
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			
			System.out.print("선택할 번호를 입력하세요 : ");
			String sel = scan.nextLine();
			System.out.println();
			
			if(sel.equals("1")) { //상세내용 조회
				getDetailHeader(); 
			} else if(sel.equals("2")) { //검색
				search();
			} else if(sel.equals("3")) { //등록
				add();
			} else if(sel.equals("4")) { //수정
				edit();
			} else if(sel.equals("5")) { //삭제
				delete();
			} else {  
				loop = false;
			} //if
			
		} //while
		
//		System.out.println("종료합니다.");
	
	} //main

	

	
	




	/**
	 * 구인공고 전체목록을 출력하는 메서드입니다.
	 * @param list : 구인공고정보들을 담고 있는 ArrayList들을 반환합니다.
	 */
	public static void list(ArrayList<JobInfoDTO> list) {
		
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" No  회사명\t\t\t업종\t\t\t\t직군\t근무형태   연봉\t\t진행상태\t");
		System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		for (JobInfoDTO dto : list) {
			int length = checkTitle(dto.getIndustry(), 25);
			System.out.printf("%2s   %-15s\t%-"+length+"s\t%s\t%s\t%s\t%s\n"
					, dto.getSeq()
					, dto.getCompany()
					, dto.getIndustry()
					, dto.getJobDivision()
					, dto.getJobType()
					, dto.getAnnualIncome()
					, dto.getState());
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
	}
	
	
	private static void getDetailHeader() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 구인공고 상세 내용 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 조회할 구인공고 선택
		System.out.print("조회할 구인공고 번호: "); 
		String seq = scan.nextLine();
		
		// 선택된 구인공고 상세내용 출력 메소드
		getDetail(seq);
		
		pause();
		
	}
	
	
	// 구인공고 상세내용 출력 메소드
	private static JobInfoDTO getDetail(String seq) {
		
		// 선택된 구인공고 정보 출력
		JobInfoDTO dto = dao.getDetail(seq);
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("       선택한 구인공고 정보");
		System.out.println("──────────────────────────────────");
		System.out.println("번호 : " + dto.getSeq()); 				//구인공고번호
		System.out.println("회사 : " + dto.getCompany()); 			//회사명
		System.out.println("업종 : " + dto.getIndustry()); 			//업종
		System.out.println("직군 : " + dto.getJobDivision()); 		//직군		
		System.out.println("근무형태 : " + dto.getJobType());		//근무형태 
		System.out.println("연봉 : " + dto.getAnnualIncome());		//연봉  
		System.out.println("공고시작일 : " + dto.getStartDate());	//공고 시작일 
		System.out.println("공고마감일 : " + dto.getEndDate());		//공고 마감일 
		System.out.println("진행상태 : " + dto.getState());			//진행상태 
		System.out.println("채용단계 : " + dto.getRecruitStep());	//채용단계 
		System.out.println("내용 : ");								//내용 
		System.out.println(dto.getDetail());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		
		return dto;
	}
	
	
	
	/**
	 * 구인공고를 검색하는 메서드입니다.
	 */
	public static void search() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 구인공고 검색 ");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 구인공고명으로 검색하기
		System.out.print("검색어(회사명) : ");
		String word = scan.nextLine(); //검색어
		System.out.println();
		
		// 검색된 결과목록 출력
		ArrayList<JobInfoDTO> list  = dao.list(word); // 매개변수로 검색어 전달
		
		list(list);
		
		pause();
	} 



	
	
	/**
	 * 구인공고를 등록하는 메서드입니다.
	 */
	public static void add() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 구인공고 등록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		
		// 등록할 구인공고정보 입력

		listCompany(); // 회사전체 목록 출력
		
		System.out.print("회사선택(번호입력) : "); // 등록된 회사 중 선택
		String companySeq = scan.nextLine();
				
		System.out.print("직군 : ");
		String jobDivision = scan.nextLine();
		
		System.out.print("근무형태 : ");
		String jobType = scan.nextLine();
		
		System.out.print("연봉(단위:만원) : ");
		String annualIncome = scan.nextLine();
		
		System.out.print("공고시작일 : ");
		String startDate = scan.nextLine();
		
		System.out.print("공고마감일 : ");
		String endDate = scan.nextLine();
		
		System.out.print("채용단계 : ");
		String recruitStep = scan.nextLine();
		
		System.out.print("내용 : ");
		String detail = scan.nextLine();
		
		System.out.println();
		
		// JobInfoDTO 객체에 입력된 공고정보 저장
		JobInfoDTO dto = new JobInfoDTO();
		
		dto.setCompanySeq(companySeq);
		dto.setJobDivision(jobDivision);
		dto.setJobType(jobType);
		dto.setAnnualIncome(annualIncome);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setRecruitStep(recruitStep);
		dto.setDetail(detail);
		
		
		// DB에 insert 작업 수행
		int result = dao.add(dto); //구인공고 등록
		int result2 = dao.addJobNoticePeriod(dto); //공고기간 등록
		
		if (result * result2 == 1) {
			System.out.println("구인공고등록 성공");
		} else {
			System.out.println("구인공고등록 실패");
		}
		
		pause();
	}


	//회사 목록 출력 메소드
	private static void listCompany() {
		
		ArrayList<CompanyDTO> clist  = dao.listCompany();
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" No  업종\t\t\t회사\t\t\t대표자\t설립일\t\t사원수\t\t매출액\t\t연락처\t\t주소");
		System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────");
	
		for (CompanyDTO dto : clist) {
			int length = checkTitle(dto.getIndustryName(), 25);
			System.out.printf("%2s   %-"+length+"s\t%-15s\t%s\t%s\t%-5s%s\t%s\t%s\n"
					, dto.getSeq() //번호
					, dto.getIndustryName() //업종
					, dto.getName() //회사명
					, dto.getCeo() //대표명
					, dto.getEstablishDate() //설립일
					, dto.getEmployeeCount() + "명" //사원수
					, dto.getSales() //매출액
					, dto.getTel() //연락처
					, dto.getAddress()); //주소
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
	}



	/**
	 * 구인공고를 수정하는 메서드입니다.
	 */
	public static void edit() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 구인공고 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 수정할 구인공고 선택
		System.out.print("수정할 구인공고 번호: "); 
		String seq = scan.nextLine();
		System.out.println();
		
		// 선택된 구인공고 정보 출력
		JobInfoDTO dto = getDetail(seq);
		
		// 수정사항 입력
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("        수정사항 입력");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("* 수정하지 않는 항목은 엔터를 입력하세요.");
		System.out.println();
		
		System.out.print("1. 직군 : ");
		String jobDivision = scan.nextLine();
		
		if (jobDivision.equals("")) { // 엔터입력시 기존정보 저장
			jobDivision = dto.getJobDivision(); 
		}
		
		System.out.print("2. 근무형태 : ");
		String jobType = scan.nextLine();
		
		if (jobType.equals("")) {
			jobType = dto.getJobType();
		}
		
		System.out.print("3. 연봉 : ");
		String annualIncome = scan.nextLine();
		
		if (annualIncome.equals("")) {
			annualIncome = dto.getAnnualIncome();
		}
		
		System.out.print("4. 채용단계 : ");
		String recruitStep = scan.nextLine();
		
		if (recruitStep.equals("")) {
			recruitStep = dto.getRecruitStep();
		}
		
		System.out.print("5. 내용 : ");
		String detail = scan.nextLine();
		
		if (detail.equals("")) {
			detail = dto.getDetail();
		}
		
		System.out.print("6. 공고시작일 : ");
		String startDate = scan.nextLine();
		
		if (startDate.equals("")) { // 엔터입력시 기존정보 저장
			startDate = dto.getStartDate(); 
		}
		
		System.out.print("7. 공고마감일 : ");
		String endDate = scan.nextLine();
		
		if (endDate.equals("")) {
			endDate = dto.getEndDate();
		}
		
		System.out.println();

		// JobInfoDTO 객체에 입력된 구인공고 정보 저장
		JobInfoDTO dto2 = new JobInfoDTO(); 
		dto2.setSeq(seq);
		dto2.setCompanySeq(dto.getCompanySeq()); // 회사번호는 기존 그대로 저장 (수정하지 않음)
		dto2.setJobDivision(jobDivision);
		dto2.setJobType(jobType);
		dto2.setAnnualIncome(annualIncome);
		dto2.setRecruitStep(recruitStep);
		dto2.setDetail(detail);
		dto2.setStartDate(startDate);
		dto2.setEndDate(endDate);
		
		// 수정사항 DB update (tblJobInfo insert)
		int result = dao.edit(dto2); 
		

		if(result > 0) {
			System.out.println("구인공고수정 성공");
		} else {
			System.out.println("구인공고수정 실패");
		}
		
		pause();		

	}

	
	
	
	
	/**
	 * 구인공고를 삭제하는 메서드입니다.
	 */
	public static void delete() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 구인공고 삭제");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 삭제할 구인공고 선택
		System.out.print("삭제할 구인공고 번호: ");
		String seq = scan.nextLine();
		System.out.println();
		
		// DB에서 delete 수행
		int result = dao.delete(seq); 
		
		if(result > 0) {
			System.out.println("구인공고삭제 완료");
		} else {
			System.out.println("구인공고삭제 실패");
		}
		
		pause();
	}






	// 퍼즈 메소드
	public static void pause() {
		
		System.out.println();
		System.out.println("계속하려면 엔터를 누르세요.");
		scan.nextLine();
		
	}
	
	

	// 레코드 길이 계산 메소드 (열 맞추기 위한)
	public static int checkTitle(String str, int length) {

		int result = length;
		for (int i = 0; i < str.length(); i++) {
			char c1 = str.charAt(i);
			if (c1 >= '가' && c1 <= '힣') {
				result--;
			}
		}
		return result;

	}


}

