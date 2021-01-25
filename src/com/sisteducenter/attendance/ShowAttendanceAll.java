package com.sisteducenter.attendance;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.student.StudentDAO;

/**
 * 출결정보를 전체조회하는 클래스입니다.
 * @author 조아라
 *
 */
public class ShowAttendanceAll {
	
	private static Scanner scan=new Scanner(System.in);
	
	public static void showRecordAll(String seq) {
		
		ArrayList<AttendanceRecordDTO> list=new ArrayList<AttendanceRecordDTO>();
		ArrayList<String> courseInfo=new ArrayList<String>();
		
		
		list=AttendanceDAO.getAllRecord(seq);
		courseInfo=StudentDAO.getTitle(seq);
		// 교육생번호를 인자로 보내서 교육생이 수강한 과정의 출결정보를 list로 가져온다.
		/*
		 * list.get(0) : 입실시간
		 * list.get(1) : 퇴실시간
		 * list.get(2) : 출결상태
		 * */
		
		

		showHeader();
		
		String sel;
		
		boolean loop=true; // 루프변수
		boolean isFirstTime=true; // 처음으로 메뉴를 보여준건지 체크하는 변수
		boolean noPage=false; // 이전목록 혹은 다음목록이 없을 경우 메시지를 표시하는 역할을 하는 변수
		int listNo=0;
		
		while(loop) {
			
			System.out.printf("\t\t\t\t* 과정명 : %s\n", courseInfo.get(1));
			showAttendanceRate(seq);
			showStateCount(seq);
			System.out.println();
			System.out.println();
			System.out.println("\t\t\t\t-----------------------------------------------------");
			System.out.println("\t\t\t\t[출석일]\t\t[입실시간]\t\t[퇴실시간]\t\t[상태]");
			System.out.println("\t\t\t\t-----------------------------------------------------");
			
			if (list.size()==0) {
				System.out.println();
				System.out.println();
				System.out.println("\t\t\t\t[!!!]");
				System.out.println("\t\t\t\t표시할 데이터가 없습니다.");
			}else {
				for (int i=listNo; i<listNo+1;i++) { // 페이지번호
		
					for (int j=i*15;j<(15+i*15);j++) { // 5개의 정보를 보여준다.
	
						if (j>=list.size()) { // j가 목록의 길이보다 크면
							
							break; // while문은 끝난다.
							
						}else {
							
							showInfo(list.get(j));
						}
					}	//2중 for
				}	//1중 for
			}


			showMenu(noPage, isFirstTime);
			/*
			 * 메뉴 구성
			 * 
			 * 1. 이전목록
			 * 2. 다음목록
			 * 3. 뒤로가기
			 * 4. 종료
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
				
				if(listNo+1 >(list.size()/15))	{ // 마지막 페이지일 때
					
					noPage=true;
					
				} else { // 마지막 페이지가 아니면
					
					noPage=false;
					listNo++;
				}
			
			}else if (sel.equals("3")){ // 뒤로가기
				
				AttendanceSubMain.showSubMain(seq);
				loop=false;
				
			}else if (sel.equals("00")){ // 종료
				
				break;
				
			}else {
				
				isFirstTime=false;
			}

		} // while문
		
	}
	
	/**
	 * 상단에 표시되는 헤더입니다.
	 */
	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t전 체 출 결 조 회\t\t*");
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
	
	
	private static void showInfo(AttendanceRecordDTO dto) {
		String outTime=dto.getOutTime();
		if (outTime!=null) {
			System.out.printf("\t\t\t\t%s\t%s\t%s\t%s\n",
							dto.getInTime().substring(0, 10),
							dto.getInTime().substring(11, 19),
							dto.getOutTime().substring(11, 19),
							dto.getState());
			
		}else {
			System.out.printf("\t\t\t\t%s\t%s\tnull\t\t%s\n",
					dto.getInTime().substring(0, 10),
					dto.getInTime().substring(11, 19),
					dto.getState());
		}
	}
	
	/**
	 * 출석률을 나타내주는 메서드입니다.
	 * @param seq : 교육생번호
	 */
	public static void showAttendanceRate(String seq) {
		
		AttendanceRateDTO dto=new AttendanceRateDTO();
		
		dto=AttendanceDAO.getAttendanceRate(seq);
		
		int percent=(int)(dto.getRate()/5);
		
		System.out.print("\t\t\t\t* 출석률 : ");
		
		for(int i=1;i<=20;i++) {
			if (i<=percent) {
				System.out.print("■");
			}else {
				System.out.print("□");
			}
		}
		System.out.printf("\t%.1f%%\n", dto.getRate());
	}
	
	/**
	 * 출결상태별 횟수를 나타내주는 메서드입니다.
	 * @param seq : 교육생번호
	 */
	public static void showStateCount(String seq) {
		ArrayList<Integer> list=new ArrayList<Integer>();
		
		list=AttendanceDAO.getStateCount(seq);
		
		System.out.printf("\t\t\t\t* 정상 : %d회 / 지각 : %d회 / 조퇴 : %d회 / 외출 : %d회 / 병가 : %d회 / 기타 : %d회",
						list.get(0),
						list.get(1),
						list.get(2),
						list.get(3),
						list.get(4),
						list.get(5));
	}
}
