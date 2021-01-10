package com.sisteducenter.admin.basis;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 교재관리 메뉴를 출력하고 기능별 메소드를 실행하는 클래스 입니다.
 * 전체 교재 조회, 교재 검색, 등록, 수정, 삭제 기능을 포함합니다.
 * @author 권주홍
 */
public class Book {
	
	private static Scanner scan;
	private static BookDAO dao;
	
	
	/**
	 * 교재관리 클래스의 기본생성자 입니다.
	 */
	static {  // 생성자
		
		scan = new Scanner(System.in);
		dao = new BookDAO(); // DB 담당 클래스
	}
	
	
	
	
	/**
	 * 교재관리 메뉴를 출력하는 메소드입니다.
	 */
	public static void intro() {
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println(" 교재 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			
			// 교재 전체 목록 출력
			ArrayList<BookDTO> list  = dao.list(null);
			list(list); 
			
			System.out.println();
			System.out.println("1. 교재 검색");
			System.out.println("2. 교재 등록");
			System.out.println("3. 교재 수정");
			System.out.println("4. 교재 삭제");
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

	

	
	
	// 교재 목록 출력 메소드
	public static void list(ArrayList<BookDTO> list) {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("No   제목\t\t\t\t\t\t      출판사");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────");
		
		for (BookDTO dto : list) {
			int titleLength = checkTitle(dto.getTitle(), 55);
			System.out.printf("%2s   %-" + titleLength + "s  %s\n"
					, dto.getSeq()
					, dto.getTitle()
					, dto.getPublisher());
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

	}


	// 교재 검색 메소드
	public static void search() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 교재 검색 ");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 교재명으로 검색하기
		System.out.print("검색어(제목) : ");
		String word = scan.nextLine(); //검색어
		System.out.println();
		
		// 검색된 결과목록 출력
		ArrayList<BookDTO> list  = dao.list(word); // 매개변수로 검색어 전달
		
		list(list);
		
		pause();
	} 
	
	
	// 교재 등록 메소드
	public static void add() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 교재 등록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 등록할 교재정보 입력
		System.out.print("제목 : ");
		String title = scan.nextLine();
		
		System.out.print("출판사 : ");
		String publisher = scan.nextLine();
		System.out.println();
		
		// dto 객체에 입력된 정보 저장
		BookDTO dto = new BookDTO();
		dto.setTitle(title);
		dto.setPublisher(publisher);

		// DB에 insert 작업 수행
		int result = dao.add(dto); 
		
		if (result == 1) {
			System.out.println("교재등록 성공");
		} else {
			System.out.println("교재등록 실패");
		}
		
		pause();
	}


	
	
	// 교재 수정 메소드
	public static void edit() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 교재 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 수정할 교재 선택
		System.out.print("수정할 교재 번호: "); 
		String seq = scan.nextLine();
		System.out.println();
		
		// 선택된 교재 정보 출력
		BookDTO dto = dao.get(seq);
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("       선택한 교재 정보");
		System.out.println("──────────────────────────────────");
		System.out.println("번호 : " + dto.getSeq()); //교재번호
		System.out.println("제목 : " + dto.getTitle()); //제목
		System.out.println("출판사 : " + dto.getPublisher()); //출판사
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		

		// 수정사항 입력
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("        수정사항 입력");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("* 수정하지 않는 항목은 엔터를 입력하세요.");
		System.out.println();
		
		System.out.print("1. 수정할 제목 : ");
		String title = scan.nextLine();
		
		if (title.equals("")) { // 엔터입력시 기존이름 저장
			title = dto.getTitle(); 
		}
		
		System.out.print("2. 수정할 출판사 : ");
		String publisher = scan.nextLine();
		
		if (publisher.equals("")) {
			publisher = dto.getPublisher();
		}
		System.out.println();
		
		// dto2 객체에 입력된 수정사항 반영
		BookDTO dto2 = new BookDTO(); 
		dto2.setSeq(seq);
		dto2.setTitle(title);
		dto2.setPublisher(publisher);
		
		// 수정사항 DB update
		int result = dao.edit(dto2); 
		
		if(result > 0) {
			System.out.println("교재수정 성공");
		} else {
			System.out.println("교재수정 실패");
		}
		
		pause();		

	}

	
	
	
	// 교재 삭제 메소드
	public static void delete() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 교재 삭제");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 삭제할 교재 선택
		System.out.print("삭제할 교재 번호: ");
		String seq = scan.nextLine();
		System.out.println();
		
		// DB에서 delete 수행
		int result = dao.delete(seq); 
		
		if(result > 0) {
			System.out.println("교재삭제 완료");
		} else {
			System.out.println("교재삭제 실패");
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
