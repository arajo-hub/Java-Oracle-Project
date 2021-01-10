package com.sisteducenter.admin.teacher;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.admin.basis.Subject;
import com.sisteducenter.admin.basis.SubjectDAO;
import com.sisteducenter.admin.basis.SubjectDTO;


/**
 * 교사계정관리 메뉴를 출력하고 기능별 메소드를 실행하는 클래스 입니다.
 * 전체 교사 조회, 교사 검색, 등록, 수정, 삭제기능 및
 * 교사의 강의가능과목 조회, 등록, 수정, 삭제, 담당과정 조회 기능을 포함합니다.
 * @author 권주홍
 */
public class Teacher {
	
	private static Scanner scan;
	private static TeacherDAO dao;
	
	
	/**
	 * 교사관리 클래스의 기본생성자 입니다.
	 */
	static {  // 생성자
		
		scan = new Scanner(System.in);
		dao = new TeacherDAO(); // DB 담당 클래스
	}
	
	

	/**
	 * 교사관리 메뉴를 출력하는 메소드입니다.
	 */
	public static void intro() {
		
		boolean loop = true;
		
		while(loop) {
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println(" 교사 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			
			// 교사 전체 목록 출력
			ArrayList<TeacherDTO> list  = dao.list(null);
			list(list); 
			
			System.out.println();
			System.out.println("1. 교사 검색");
			System.out.println("2. 교사 등록");
			System.out.println("3. 교사 수정");
			System.out.println("4. 교사 삭제");
			System.out.println("5. 강의가능과목 조회");
			System.out.println("6. 담당과정 조회");
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
			} else if(sel.equals("5")) { //강의가능과목 조회
				getPossibleSubject();	
			} else if(sel.equals("6")) { //담당과정 조회
				getDetail();
			} else {  
				loop = false;
			} //if
			
		} //while
		
//		System.out.println("종료합니다.");
	
	} //main

	

	



	/**
	 * 교사 전체목록을 출력하는 메서드입니다.
	 * @param list : 교사정보들을 담고 있는 ArrayList를 반환합니다.
	 */
	public static void list(ArrayList<TeacherDTO> list) {
		
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" No  이름\t전화번호\t\t주민번호뒷자리(PW)");
		System.out.println("─────────────────────────────────────────────────────────────────────────────────");
		
		for (TeacherDTO dto : list) {
//			int titleLength = checkTitle(dto.getName(), 60);
			System.out.printf("%2s   %-4s\t%-15s \t%-10s\n"
					, dto.getSeq()
					, dto.getName()
					, dto.getTel()
					, dto.getIdNum());
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
	}
	
	
	
	
	// 특정교사 강의가능과목 출력 메소드
	private static void getPossibleSubject() {

		// 조회할 교사 선택
		System.out.print("조회할 교사 번호: "); 
		String seq = scan.nextLine();
		System.out.println();
		
		// 선택된 교사 강의가능과목 정보 출력
		try {
			
			ArrayList<PossibleSubjectDTO> plist = dao.getPossibleSubject(seq);

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf(" [%s] 교사의 강의가능과목 조회\n", plist.get(0).getTeacherName()); //교사명
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println(" No  과목");
			System.out.println("─────────────────────────────────────────────────────────────────────────────────");
			
			int i = 0;
			for (PossibleSubjectDTO dto : plist) {
				i++;
//				int courseLength = checkTitle(dto.getCourse(), 50);
				System.out.printf("%2d   %s\n"
						, i
						, dto.getSubjectName());
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				
			pause();
			
			
		} catch (Exception e) {
			
			System.out.println("해당 교사의 강의가능과목이 등록 되지 않았습니다.");
			
			pause();
		}
		
	}

	
	
	// 교사 담당과정 출력 메소드
	private static void getDetail() {
		
		// 조회할 교사 선택
		System.out.print("조회할 교사 번호: "); 
		String seq = scan.nextLine();
		System.out.println();
		
				
		// 선택된 교사 담당 과정 정보 출력
		ArrayList<TeacherDTO> list = dao.getDetail(seq);
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.printf(" [%s] 교사 담당 과정 조회\n", list.get(0).getName()); //교사명
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" No  담당과정\t\t\t\t\t\t과목\t\t시작일\t\t종료일\t\t진행상태\t강의실\t\t교재");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		int i = 0;
		for (TeacherDTO dto : list) {
			i++;
			int courseLength = checkTitle(dto.getCourse(), 50);
			int subjectLength = checkTitle(dto.getSubject(), 15);
			System.out.printf("%2d   %-"+courseLength+"s\t%-"+subjectLength+"s\t%s\t%s\t%-5s\t%-7s\t%s\n"
					, i
					, dto.getCourse()
					, dto.getSubject()
					, dto.getStartDate()
					, dto.getEndDate()
					, dto.getState()
					, dto.getLectureRoom()
					, dto.getBook());
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			
		pause();
		
	}





	/**
	 * 교사를 검색하는 메서드입니다.
	 */
	public static void search() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 교사 검색 ");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 교사명으로 검색하기
		System.out.print("검색어(교사명) : ");
		String word = scan.nextLine(); //검색어
		System.out.println();
		
		// 검색된 결과목록 출력
		ArrayList<TeacherDTO> list  = dao.list(word); // 매개변수로 검색어 전달
		
		list(list);
		
		pause();
	} 
	
	
	
	
	/**
	 * 교사를 등록하는 메서드입니다.
	 */
	public static void add() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 교사 등록");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 등록할 교사정보 입력
		System.out.print("교사명 : ");
		String name = scan.nextLine();
		
		System.out.print("주민번호 뒷자리 : ");
		String idNum = scan.nextLine();
		
		System.out.print("전화번호 : ");
		String tel = scan.nextLine();
		
		System.out.println();
		
		// TeacherDTO 객체에 입력된 교사 기초정보 저장
		TeacherDTO dto = new TeacherDTO();
		dto.setName(name);
		dto.setIdNum(idNum);
		dto.setTel(tel);
		
		// DB에 insert 작업 수행
		int result = dao.add(dto); 

		// 강의가능과목 등록
		addPossibleSubject(name);
		
		
		if (result == 1) {
			System.out.println("교사등록 성공");
		} else {
			System.out.println("교사등록 실패");
		}
		
		pause();
	}


	
	// 강의가능과목 등록 메소드
	private static void addPossibleSubject(String name) {
		
		
		ArrayList<TeacherDTO> list  = dao.list(name); // 등록한 교사 이름조회하여 해당 교사 정보 list 생성
		
		String teacherSeq = list.get(0).getSeq(); // list에서 해당 교사의 번호를 변수에 저장 

		// 과목 전체목록 출력
		Subject subject = new Subject();
		SubjectDAO sdao = new SubjectDAO();
		ArrayList<SubjectDTO> slist  = sdao.list(null);
		subject.list(slist);
		

		// 등록할 과목 입력
		System.out.println("강의가능 과목을 선택하세요(복수선택 가능)");
		System.out.println("입력을 마치면 엔터키를 눌러주세요");
		System.out.print("과목 번호 : ");
		String subjectSeq = scan.nextLine();
		
		
		// PossibleSubjectDTO 객체에 강의가능과목 정보 저장
		ArrayList<PossibleSubjectDTO> plist = new ArrayList();
		
		while (!subjectSeq.equals("")) {

			PossibleSubjectDTO pdto = new PossibleSubjectDTO();
			pdto.setTeacherSeq(teacherSeq);
			pdto.setSubjectSeq(subjectSeq);
			plist.add(pdto);
			
			System.out.print("과목 번호 : ");
			subjectSeq = scan.nextLine();
			
		}
		
		// DB에 insert 작업 수행
		int result = dao.addPossibleSubject(plist); 
		
		System.out.println();
		System.out.println("등록된 강의가능과목 수 : " + result + "과목");
		System.out.println();
		
		if (result > 0) {
			System.out.println("강의가능과목 등록 성공");
		} else {
			System.out.println("강의가능과목 등록 실패");
		}
		
	}













	/**
	 * 교사정보를 수정하는 메서드입니다.
	 */
	public static void edit() {

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 교사 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 수정할 교사 선택
		System.out.print("수정할 교사 번호: "); 
		String seq = scan.nextLine();
		System.out.println();
		
		// 선택된 교사 정보 출력
		TeacherDTO dto = dao.get(seq);
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("       선택한 교사 정보");
		System.out.println("──────────────────────────────────");
		System.out.println("번호 : " + dto.getSeq()); 				//교사번호
		System.out.println("교사명 : " + dto.getName()); 			//교사명
		System.out.println("주민번호 뒷자리 : " + dto.getIdNum()); 	//주민번호 뒷자리
		System.out.println("전화번호 : " + dto.getTel()); 			//전화번호		
		System.out.println("강의가능 과목 : ");						//강의가능과목 
		
		ArrayList <PossibleSubjectDTO> plist = dao.getPossibleSubject(seq);
		
		int i = 0;
		for (PossibleSubjectDTO pdto : plist) {
			i++;
			System.out.printf("%d. %s\n", i , pdto.getSubjectName());
		}
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println();
		
		

		// 수정사항 입력
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("        수정사항 입력");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("* 수정하지 않는 항목은 엔터를 입력하세요.");
		System.out.println();
		
		System.out.print("1. 교사명 : ");
		String name = scan.nextLine();
		
		if (name.equals("")) { // 엔터입력시 기존정보 저장
			name = dto.getName(); 
		}
		
		System.out.print("2. 주민번호 뒷자리 : ");
		String idNum = scan.nextLine();
		
		if (idNum.equals("")) {
			idNum = dto.getIdNum();
		}
		
		System.out.print("3. 전화번호 : ");
		String tel = scan.nextLine();
		
		if (tel.equals("")) {
			tel = dto.getTel();
		}
		
		System.out.println();

		// TeacherDTO 객체에 입력된 교사 기초정보 저장
		TeacherDTO dto2 = new TeacherDTO(); 
		dto2.setSeq(seq);
		dto2.setName(name);
		dto2.setIdNum(idNum);
		dto2.setTel(tel);
		
		// 수정사항 DB update
		int result = dao.edit(dto2); 
		
		
		// 강의가능과목 수정
		editPossibleSubject(seq);
		

		if(result > 0) {
			System.out.println("교사수정 성공");
		} else {
			System.out.println("교사수정 실패");
		}
		
		pause();		

	}

	
	
	// 강의가능과목 수정 메소드
	private static void editPossibleSubject(String seq) {

		// 과목 전체 목록 출력
		Subject subject = new Subject();
		SubjectDAO sdao = new SubjectDAO();
		ArrayList<SubjectDTO> slist = sdao.list(null);
		subject.list(slist);
		

		// 등록할 강의가능과목 입력
		System.out.println("4. 강의가능과목 ");
		System.out.println("강의가능 과목을 선택하세요(복수선택 가능)");
		System.out.println("입력을 마치면 엔터키를 눌러주세요");
		System.out.print("과목 번호 : ");
		String subjectSeq = scan.nextLine();
		
		if (!subjectSeq.equals("")) {
			
			// 기존 강의가능과목 삭제 
			dao.deletePossibleSubject(seq);
			
			// PossibleSubjectDTO 객체에 강의가능과목 정보 저장
			ArrayList<PossibleSubjectDTO> plist = new ArrayList();
			
			while (!subjectSeq.equals("")) {
				
				PossibleSubjectDTO pdto = new PossibleSubjectDTO();
				pdto.setTeacherSeq(seq);
				pdto.setSubjectSeq(subjectSeq);
				plist.add(pdto);
				
				System.out.print("과목 번호 : ");
				subjectSeq = scan.nextLine();
				
			}
			
			// DB에 insert 작업 수행
			int result = dao.addPossibleSubject(plist); 

			
			System.out.println();
			System.out.println("등록된 강의가능과목 수 : " + result + "과목");
			System.out.println();
			
			if (result > 0) {
				System.out.println("강의가능과목 등록 성공");
			} else {
				System.out.println("강의가능과목 등록 실패");
			}
			
		}

		
	}




	/**
	 * 교사정보를 삭제하는 메서드입니다.
	 */
	public static void delete() {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 교사 삭제");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		// 삭제할 교사 선택
		System.out.print("삭제할 교사 번호: ");
		String seq = scan.nextLine();
		System.out.println();
		
		// 해당 교사의 강의가능과목 삭제 
		dao.deletePossibleSubject(seq);

		// DB에서 delete 수행
		int result = dao.delete(seq); 
		
		if(result > 0) {
			System.out.println("교사삭제 완료");
		} else {
			System.out.println("교사삭제 실패");
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
