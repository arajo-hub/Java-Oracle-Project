package com.sisteducenter.attendance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 출결정보를 월별조회하는 클래스입니다.
 * @author 조아라
 *
 */
public class ShowAttendanceMonth {
	
	private static Scanner scan=new Scanner(System.in);
	
	/**
	 * 출결정보를 원하는 년도와 달을 입력하여 달력형식으로 나타내는 메서드입니다.
	 * @param seq
	 */
	public static void showRecordMonth(String seq) {
		
		showHeader();
		
		System.out.println("\t\t\t\t\t* 조회를 원하는 년도를 입력해주세요.");
		System.out.print("\t\t\t\t\t▶︎ ");
		
		String year=scan.nextLine();
		
		System.out.println("\t\t\t\t\t* 조회를 원하는 달을 입력해주세요.");
		System.out.print("\t\t\t\t\t▶︎ ");
		
		String month=scan.nextLine();
		
		boolean loop=true;
		boolean isFirstTime=true;
		
		while(loop) {
			
			// 헤더 출력
			showHeader();
			showCalendar(seq, year, month);
			// 메인메뉴 출력
			showMenu(isFirstTime);
			isFirstTime=false;
			// 메뉴를 이미 한 번 보여줬으므로 값을 false로 바꾼다.
			// 이후엔 메뉴번호를 제대로 입력하지 않으면 잘못된 메뉴 번호를 입력했다는 메시지가
			// 새 화면에 함께 뜬다.
			
			/*
			 * 메뉴 구성
			 * 
			 * 1. 뒤로 가기
			 * 00. 종료
			 * */
			
			String sel=scan.nextLine();
			
			if (sel.equals("1")) { // 뒤로 가기
				AttendanceSubMain.showSubMain(seq);
				loop=false;
			}else if (sel.equals("00")) { // 프로그램 종료 선택
				System.out.println("\t\t\t\t\t프로그램을 종료합니다.");
				loop=false;
			}
			
		} // while

	}
	
	private static void showCalendar(String seq, String year, String month) {
	      
		ArrayList<AttendanceRecordDTO> list=new ArrayList<AttendanceRecordDTO>();
	    
		list=AttendanceDAO.getMonthRecord(seq, year, month);
		
		int yearNum=Integer.parseInt(year);
	    int monthNum=Integer.parseInt(month);
		
	    int lastDay = 0; // 마지막 날짜
		int day_of_week = 0; // 무슨 요일인지
	      
		Calendar c1=Calendar.getInstance();
	      
		c1.set(yearNum, monthNum-1, 1); // 입력한 날짜로 설정하고
	      
		lastDay=c1.getActualMaximum(Calendar.DATE);
	      
		day_of_week=c1.get(Calendar.DAY_OF_WEEK)-1;
	      
		//출력하기
		System.out.println();
		System.out.printf("                                                * %d년 %02d월 *\n", yearNum, monthNum);
		System.out.println("   ------------------------------------------------------------------------------------------------------------");
		System.out.println("     [일]\t\t[월]\t\t[화]\t\t[수]\t\t[목]\t\t[금]\t\t[토]");
	    System.out.println("   ------------------------------------------------------------------------------------------------------------");
		System.out.println();
		
		// 1일을 요일 위치에 맞추기 위해 탭 추가
		for (int i=0;i<day_of_week;i++) {
			System.out.print("\t\t");
		}
  
		//날짜 출력
		for (int i=1;i<=lastDay;i++) {
    		  System.out.printf("%6d일 ", i);
			boolean isAttend=false;
			for (AttendanceRecordDTO dto : list) {
				isAttend=false;
				if (Integer.parseInt(dto.getInTime().substring(8, 10))==i) {
        	  
					System.out.printf("/ %2s\t", dto.getState());
					isAttend=true;
					break;
				}
			}
			if (isAttend==false) {
				System.out.print("\t");
			}
  
			//현재 출력하는 날짜(i)가 토요일인지?
			if ((i+day_of_week)%7==0) {
				System.out.println();
				System.out.println();
			}
		} // 날짜 출력
	      
	} // createCalendar
	
	/**
	 * 상단에 표시되는 헤더입니다.
	 */
	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t출결월별조회\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void showMenu(boolean isFirstTime) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t\t\t1. 뒤로가기");
		System.out.println("\t\t\t\t\t\t00. 종료");
		System.out.println();
		System.out.println();
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
}
