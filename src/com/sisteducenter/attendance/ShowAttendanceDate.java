package com.sisteducenter.attendance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 출결정보를 일별조회하는 클래스입니다.
 * @author 조아라
 *
 */
public class ShowAttendanceDate {
	
	static Scanner scan=new Scanner(System.in);

	/**
	 * 출결정보를 일별로 조회하여 나타내주는 메서드입니다.
	 * @param seq 교육생번호
	 */
	public static void showRecordDate(String seq){
		
		boolean loop=true;
		boolean isFirstTime;
		boolean isDateValid=false;
		
		String year;
		String month;
		String date;
		
		while(loop) {
			
			showHeader();
			
			System.out.println("\t\t\t\t\t* 조회를 원하는 년도를 입력해주세요.");
			System.out.print("\t\t\t\t\t▶︎ ");
			
			year=scan.nextLine();
			
			System.out.println("\t\t\t\t\t* 조회를 원하는 달을 입력해주세요.");
			System.out.print("\t\t\t\t\t▶︎ ");
			
			month=scan.nextLine();
			
			System.out.println("\t\t\t\t\t* 조회를 원하는 일을 입력해주세요.");
			System.out.print("\t\t\t\t\t▶︎ ");
			
			date=scan.nextLine();
			
			isDateValid=isValidDate(year, month, date);
			
			if (!isDateValid) {
				isFirstTime=false;
				loop=false;
				showRightDateMessage(isDateValid);
				continue;
			}

			isFirstTime=true;
			
			// 헤더 출력
			showHeader();
			
			// 입력한 날의 출결 출력
			showDate(seq, year, month, date);
			
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
	
	private static void showDate(String seq, String year, String month, String date) {
		ArrayList<AttendanceRecordDTO> list=new ArrayList<AttendanceRecordDTO>();
		
		list=AttendanceDAO.getDateRecord(seq, year, month, date);
		System.out.println("\t\t\t\t-----------------------------------------------------------");
		System.out.println("\t\t\t\t[출석일]\t\t[입실시간]\t\t[퇴실시간]\t\t[상태]");
		System.out.println("\t\t\t\t-----------------------------------------------------------");
		
		for(AttendanceRecordDTO dto:list) {
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
		System.out.println();
		System.out.println();
		
	}

	private static void showHeader() {
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t=========================================");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎                                       *︎︎");
		System.out.println("\t\t\t\t*︎︎︎︎︎︎︎︎︎︎︎︎︎\t\t쌍용교육센터\t\t*");
		System.out.println("\t\t\t\t*\t\t출결일별조회\t\t*");
		System.out.println("\t\t\t\t*                                       *︎︎︎︎︎︎︎︎︎︎︎︎︎");
		System.out.println("\t\t\t\t=========================================");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	private static void showMenu(boolean isFirstTime) {
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
	
	private static boolean isValidDate(String year, String month, String date) {
		
		
		try{
			
			String test=String.format("%04d-%02d-%02d", Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			dateFormat.setLenient(false);
			dateFormat.parse(test);
			return true;

		}catch (ParseException  e){
			return false;
		}
		
	}
	
	private static void showRightDateMessage(boolean isDateValid) {
		System.out.println("\t\t\t\t\t[!!!]");
		System.out.println("\t\t\t\t\t잘못된 날짜입니다.");
		System.out.println("\t\t\t\t\t다시 입력해주세요.");
		System.out.println();
		System.out.println();
	}
}
