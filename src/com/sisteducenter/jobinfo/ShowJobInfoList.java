package com.sisteducenter.jobinfo;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.student.StudentMain;

/**
 * 구직정보리스트를 조회하기 위한 클래스입니다.
 * @author 조아라
 *
 */
public class ShowJobInfoList {
	
	private static Scanner scan;
	
	static {
		scan=new Scanner(System.in);
	}

	/**
	 * 현재 진행중인 구직정보를 출력해서 보여주는 메서드입니다.
	 * 구인정보 5개가 한 페이지를 이룹니다.
	 * @param seq : 교육생번호
	 */
	public static void showAllInfo(String seq) {
		ArrayList<JobInfoListDTO> list=new ArrayList<JobInfoListDTO>();
		list=JobInfoDAO.getInfo();
		
		showHeader();
			
		String sel;
		
		boolean loop=true; // 루프변수
		boolean isFirstTime=true; // 처음으로 메뉴를 보여준건지 체크하는 변수
		boolean noPage=false; // 이전목록 혹은 다음목록이 없을 경우 메시지를 표시하는 역할을 하는 변수
		int listNo=0;
		
		while(loop) {
			
			for (int i=listNo; i<listNo+1;i++) { // 페이지번호

				for (int j=i*5;j<(5+i*5);j++) { // 5개의 정보를 보여준다.

					if (j>=list.size()) { // j가 목록의 길이보다 크면
						
						break; // while문은 끝난다.
						
					}else {
						
						showInfo(list.get(j));
					}
				}	//2중 for
			}	//1중 for

			showMenu(noPage, isFirstTime);
			/*
			 * 메뉴 구성
			 * 
			 * 1. 이전목록
			 * 2. 다음목록
			 * 5. 뒤로가기
			 * 00. 종료
			 * */
			
			sel = scan.nextLine();

			if (sel.equals("1")) { // 이전목록 선택
				
				if(listNo==0){	// 첫 페이지일 때
					
					noPage=true;
					
				}else{ // 첫 페이지가 아니면
					
					noPage=false;
					listNo--;
				}
				
			}else if (sel.equals("2")){ // 다음목록 선택
				
				if(listNo+1 >(list.size()/5))	{ // 마지막 페이지일 때
					
					noPage=true;
					
				} else { // 마지막 페이지가 아니면
					
					noPage=false;
					listNo++;
				}
			
			}else if (sel.equals("3")){ // 뒤로가기
				
				StudentMain.showMain(seq);
				loop=false;
				
			}else if (sel.equals("00")){ // 종료
				
				break;
				
			}else {
				
				isFirstTime=false;
			}

		} // while문
	}
	
	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t구 인 정 보\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void showMenu(boolean noPage, boolean isFirstTime) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t1. 이전목록");
		System.out.println("\t\t\t\t\t\t2. 다음목록");
		System.out.println("\t\t\t\t\t\t3. 뒤로가기");
		System.out.println("\t\t\t\t\t\t00. 종료");
		System.out.println();
		System.out.println();
		if (noPage) {
			System.out.println("\t\t\t\t\t* 목록이 없습니다.");
		}
		if (isFirstTime) {
			System.out.println("\t\t\t\t\t* 원하는 메뉴번호를 입력해주세요.");
			System.out.print("\t\t\t\t\t▶︎ ");
		}else {
			System.out.println("\t\t\t\t\t[!!!]");
			System.out.println("\t\t\t\t\t잘못된 메뉴번호입니다.");
			System.out.println("\t\t\t\t\t다시 입력해주세요.");
			System.out.print("\t\t\t\t\t▶︎ ");
		}
	}
	
	private static void showInfo(JobInfoListDTO dto) {
		System.out.println("\t\t\t\t------------------------------------------------------------------");
		System.out.printf("\t\t\t\t[회사명] %s\n", dto.getCompany());
		System.out.printf("\t\t\t\t[업종명] %s\t[매출액] %s\n", dto.getIndustryName(), dto.getSales());
		System.out.printf("\t\t\t\t[모집직군] %s\t[근무형태] %s\n", dto.getJobDivision(), dto.getJobType());
		System.out.printf("\t\t\t\t[연봉] %s\t[채용단계] %s\n", dto.getAnnualIncome(), dto.getRecruitStep());
		System.out.printf("\t\t\t\t[공고시작일] %s\t[공고마감일] %s\n", dto.getStartDate().substring(0,10), dto.getEndDate().substring(0,10));
		System.out.println();
		System.out.println("\t\t\t\t[공고내용]");
		System.out.print("\t\t\t\t");
		
		for(int k=0;k<dto.getDetail().length();k++) {
			System.out.print(dto.getDetail().charAt(k));
			if (k%50==0 && k!=0) {
				System.out.println();
				System.out.print("\t\t\t\t");
			}
		
		}
		System.out.println("\n\t\t\t\t------------------------------------------------------------------");
		System.out.println();
		System.out.println();
	}
}
