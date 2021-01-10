package com.sisteducenter.admin.basis;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * 과목관리 메뉴를 출력하고 기능별 메소드를 실행하는 클래스 입니다.
 * 전체 과목 조회, 과목 검색, 등록, 수정, 삭제 기능을 포함합니다.
 * @author 권주홍
 */
public class Subject {
	
	private static Scanner scan;
	private static SubjectDAO dao;
	
	
	/**
	 * 과목관리 클래스의 기본생성자 입니다.
	 */
	static {  // 생성자
		
		scan = new Scanner(System.in);
		dao = new SubjectDAO(); // DB 담당 클래스
	}
	
	
	
	/**
	 * 과목관리 메뉴를 출력하는 메소드입니다.
	 */
	public static void intro() {
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println(" 과목 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			
			// 과목 전체 목록 출력
			ArrayList<SubjectDTO> list  = dao.list(null);
			list(list); 
			
			System.out.println();
			System.out.println("1. 과목 검색");
			System.out.println("2. 과목 등록");
			System.out.println("3. 과목 수정");
			System.out.println("4. 과목 삭제");
			System.out.println();
			System.out.println("0. 메인으로");
			System.out.println();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			
			System.out.print("선택할 번호를 입력하세요 : ");
			String sel = scan.nextLine();
			System.out.println();
			
			if(sel.equals("1")) { //검색
				search(); 
			} else if(sel.equals("2")) { //등록
				add();
			} else if(sel.equals("3")) { //수정
				edit();
			} else if(sel.equals("4")) { //삭제
				delete();
			} else {  
				loop = false;
			} //if
			
		} //while
		
//		System.out.println("종료합니다.");
	
	} //main

	

	
	
	/**
	 * 과목 목록을 출력하는 메서드입니다.
	 * @param list : 과목정보를 담고 있는 ArrayList입니다.
	 */
	public static void list(ArrayList<SubjectDTO> list) {
		
	
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" No  과목\t\t\t구분\t기간\t교재");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────");
		
		for (SubjectDTO dto : list) {
			int titleLength = checkTitle(dto.getName(), 25);
			System.out.printf("%2s   %-" + titleLength + "s  %s\t%s\t%s\n"
					, dto.getSeq()
					, dto.getName()
					, dto.getDivision()
					, dto.getPeriod()+"주"
//					, dto.getBookSeq()
					, dto.getBookTitle()); // 사용 교재제목 출력
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	
	}


	/**
	 * 과목을 검색하는 메서드입니다.
	 */
	public static void search() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 과목 검색 ");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 과목명으로 검색하기
		System.out.print("검색어(과목명) : ");
		String word = scan.nextLine(); //검색어
		System.out.println();
		
		// 검색된 결과목록 출력
		ArrayList<SubjectDTO> list  = dao.list(word); // 매개변수로 검색어 전달
		
		list(list);
		
		pause();
	} 
	
	
	/**
	 * 과목을 등록하는 메서드입니다.
	 */
	public static void add() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 과목 등록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 등록할 과목정보 입력
		System.out.print("과목명 : ");
		String name = scan.nextLine();
		
		System.out.print("구분(공통/추가) : ");
		String division = scan.nextLine();
				
		System.out.print("과목기간(단위:주) : ");
		String period = scan.nextLine();
		
		//교재 전체 목록출력	
		Book book = new Book();
		BookDAO bdao = new BookDAO();
		ArrayList<BookDTO> list  = bdao.list(null);
		book.list(list); 
		
		System.out.print("교재(번호) : ");
		String bookSeq = scan.nextLine();
		System.out.println();
		
		// dto 객체에 입력된 정보 저장
		SubjectDTO dto = new SubjectDTO();
		dto.setName(name);
		dto.setDivision(division);
		dto.setPeriod(period);
		dto.setBookSeq(bookSeq);

		// DB에 insert 작업 수행
		int result = dao.add(dto); 
		
		if (result == 1) {
			System.out.println("과목등록 성공");
		} else {
			System.out.println("과목등록 실패");
		}
		
		pause();
	}


	
	
	/**
	 * 과목을 수정하는 메서드입니다.
	 */
	public static void edit() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 과목 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 수정할 과목 선택
		System.out.print("수정할 과목 번호: "); 
		String seq = scan.nextLine();
		System.out.println();
		
		// 선택된 과목 정보 출력
		SubjectDTO dto = dao.get(seq);
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("       선택한 과목 정보");
		System.out.println("──────────────────────────────────");
		System.out.println("번호 : " + dto.getSeq()); //과목번호
		System.out.println("과목명 : " + dto.getName()); //과목명
		System.out.println("구분 : " + dto.getDivision()); //과목명
		System.out.println("과목기간(단위:주) : " + dto.getPeriod()+"주"); //과목기간
		System.out.println("교재 : " + dto.getBookTitle()); //교재 제목
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		

		// 수정사항 입력
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("        수정사항 입력");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("* 수정하지 않는 항목은 엔터를 입력하세요.");
		System.out.println();
		
		System.out.print("1. 과목명 : ");
		String name = scan.nextLine();
		
		if (name.equals("")) { // 엔터입력시 기존이름 저장
			name = dto.getName(); 
		}
		
		System.out.print("2. 구분 : ");
		String division = scan.nextLine();
		
		if (division.equals("")) { 
			division = dto.getDivision(); 
		}
		
		System.out.print("3. 과목기간(단위:주) : ");
		String period = scan.nextLine();
		
		if (period.equals("")) {
			period = dto.getPeriod();
		}
		
		System.out.print("4. 교재(번호) : ");
		String bookSeq = scan.nextLine();
		
		if (bookSeq.equals("")) {
			bookSeq = dto.getBookSeq();
		}
		System.out.println();
		
		// dto2 객체에 입력된 수정사항 반영
		SubjectDTO dto2 = new SubjectDTO(); 
		dto2.setSeq(seq);
		dto2.setName(name);
		dto2.setDivision(division);
		dto2.setPeriod(period);
		dto2.setBookSeq(bookSeq);
		
		// 수정사항 DB update
		int result = dao.edit(dto2); 
		
		if(result > 0) {
			System.out.println("과목수정 성공");
		} else {
			System.out.println("과목수정 실패");
		}
		
		pause();		

	}

	
	
	
	/**
	 * 과목을 삭제하는 메서드입니다.
	 */
	public static void delete() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 과목 삭제");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 삭제할 과목 선택
		System.out.print("삭제할 과목 번호: ");
		String seq = scan.nextLine();
		System.out.println();
		
		// DB에서 delete 수행
		int result = dao.delete(seq); 
		
		if(result > 0) {
			System.out.println("과목삭제 완료");
		} else {
			System.out.println("과목삭제 실패");
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
