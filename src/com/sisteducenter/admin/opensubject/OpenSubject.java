package com.sisteducenter.admin.opensubject;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.admin.AdminMain;
import com.sisteducenter.admin.opencourse.OpenCourseDAO;
import com.sisteducenter.admin.opencourse.OpenCourseListDTO;
import com.sisteducenter.admin.opencourse.PossibleTeacherDTO;
import com.sisteducenter.admin.opencourse.SpecificOpenCourseDTO;

/**
 * 개설과목 조회/수정/등록/삭제를 담당하는 클래스입니다.
 * @author 장진영
 *
 */
public class OpenSubject {
	
	private static Scanner scan;
	private static OpenSubjectDAO osDAO;
	private static OpenCourseDAO ocDAO;
	
	static {
		scan = new Scanner(System.in);
		osDAO = new OpenSubjectDAO();
		ocDAO = new com.sisteducenter.admin.opencourse.OpenCourseDAO();
	}
	
	/**
	 * 개설과목관리 메뉴를 나타내는 메서드입니다.
	 */
	public static void showMain() {
		
		boolean loop = true;
		
		while(loop) {
			System.out.println("\t\t\t\t1. 개설 과목 조회");
			System.out.println("\t\t\t\t2. 개설 과목 수정");
			System.out.println("\t\t\t\t3. 개설 과목 등록");
			System.out.println("\t\t\t\t4. 개설 과목 삭제");
			System.out.println("\t\t\t\t5. 뒤로가기");
			System.out.println("\t\t\t\t00. 종료");
			
			System.out.print("입력 : ");
			String sel = scan.nextLine();
			
			switch(sel) {
				case "1":
					list();
					break;
				case "2":
					edit();
					break;
				case "3":
					add();
					break;
				case "4":
					delete();
					break;
				case "5":
					AdminMain.showMain();
					break;
				case "00":
					loop = false;
					break;
				default:
					System.out.println("잘못된 입력입니다.");
					break;
			}
		}
	}

	private static void delete() {
		
		//개설 과목 출력시 개설 과정 정보(과정명, 과정기간(시작 년월일, 끝 년월일), 강의실)와 과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명을 출력한다. 
		System.out.println("\n\t\t\t\t\t\t[개설 과정 조회]");
		
		ArrayList<OpenCourseListDTO> ocList = osDAO.ocList();
		int n = 0;
		
		line();
		//개설 과정명, 개설 과정기간(시작 년월일, 끝 년월일), 강의실명, 개설 과목 등록 여부, 교육생 등록 인원
		System.out.println("[번호]\t\t[과정명]\t\t\t\t\t\t[과정기간]\t     [강의실]   [등록인원]");
		for(OpenCourseListDTO oc : ocList) {
			System.out.printf("%2d\t%-40s\t%s ~ %s\t%1s\t%s\n"
								,n+1
								,oc.getCoursename()
								,oc.getStartdate(), oc.getEnddate()
								,oc.getLectureroom()
								,oc.getRegstudentnumber());
			n++;
		}
		
		//개설 과정 선택
		System.out.print ("개설 과정 선택 : ");
		String sel = scan.nextLine();
		
		//시퀀스 번호를 가져온다.
		String seq = ocList.get(Integer.parseInt(sel)-1).getSeq();
		
		//시퀀스 번호를 가지고 쿼리문 다시 작성 오버로딩
		ArrayList<SpecificOpenCourseDTO> socList = osDAO.socList(seq);
		line();
		n=0;
		System.out.printf("과정명 : %s\n과정기간 : %s ~ %s\n",
				ocList.get(Integer.parseInt(sel)-1).getCoursename(),
				ocList.get(Integer.parseInt(sel)-1).getStartdate(), 
				ocList.get(Integer.parseInt(sel)-1).getEnddate());
		//개설 과목 정보(과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명)
		System.out.printf("[번호]\t[과목명]\t\t\t\t\t[과정기간]\t\t\t[교재명]\t\t\t\t\t[교사명]\n");
		for(SpecificOpenCourseDTO soc : socList) {
			System.out.printf("%2d\t%-25s\t%s ~ %s\t%-40s\t%s\n"
								, n+1
								, soc.getSubjectname()
								, soc.getStartdate(), soc.getEnddate()
								, soc.getTitle()
								, soc.gettName());
			n++;
		}
		
		System.out.print("삭제할 과목 번호 입력 : ");
		sel = scan.nextLine();
		seq = socList.get(Integer.parseInt(sel)-1).getSeq();
		
		int result = osDAO.delete(seq);
		
		if(result > 0) {
			System.out.println("과정이 삭제되었습니다.");
		} else {
			System.out.println("과정이 삭제되지 못했습니다.");
		}
		
		pause();
		
	}

	//개설 과목 정보 입력시 과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명을 입력할 수 있어야 한다. 
	private static void add() {
		
		//개설 과정 리스트
		ArrayList<OpenCourseListDTO> ocList = ocDAO.ocList();
		int n = 0;
		//개설 과정명, 개설 과정기간(시작 년월일, 끝 년월일), 강의실명, 개설 과목 등록 여부, 교육생 등록 인원
		System.out.println("[번호]\t\t[과정명]\t\t\t\t\t\t[과정기간]\t     [강의실]   [등록인원]");
		for(OpenCourseListDTO oc : ocList) {
			System.out.printf("%2d\t%-40s\t%s ~ %s\t%1s\t%s\n"
								,n+1
								,oc.getCoursename()
								,oc.getStartdate(), oc.getEnddate()
								,oc.getLectureroom()
								,oc.getRegstudentnumber());
			n++;
		}
		
		//개설 과정 선택
		System.out.print ("추가할 개설 과정 선택 : ");
		String sel = scan.nextLine();
		String opencourseq = ocList.get(Integer.parseInt(sel)-1).getSeq();		//opencourseq
		
		line();
		
		System.out.println("\t\t\t\t\t\t[과목 조회]");
		ArrayList<SubjectListDTO> sList = osDAO.sList();
		n = 0;
		//과목 기초데이터
		System.out.println("[번호]\t[과목명]\t\t\t[구분]\t[수업기간(주)]");
		for(SubjectListDTO s : sList) {
			System.out.printf("%2d\t%-20s\t%s\t\t%s\n"
								,n+1
								,s.getSubjectName()
								,s.getDivision()
								,s.getPeriod());
			n++;
		}
		
		line();
		//개설 과정 선택
		System.out.print ("추가할 개설 과목 선택 : ");
		sel = scan.nextLine();
		
		//시퀀스 번호를 가져온다.
		String subjectseq = sList.get(Integer.parseInt(sel)-1).getSeq();		//subjectseq
		
		line();
		//개설 과목 시작
		System.out.print("과정 시작일을 입력하세요 (예 : 2020/01/23) : ");
		String startdate = scan.nextLine();
		
		//날짜를 '/'기준으로 나눈다
		String cal[] = startdate.split("/");
		
		String year = cal[0];
		String month = cal[1];
		String date = cal[2];
		
		startdate = year+month+date; //yyyymmdd 
		
		//기간
		String period = sList.get(Integer.parseInt(sel)-1).getPeriod();
	
		//종료날짜 계산
		String enddate = osDAO.retEndDate(year, month, date, period);
		
		line();
		//담당교사 배정
		ArrayList<PossibleTeacherDTO> ptList = osDAO.ptList(startdate, subjectseq);
		n=0;
		System.out.println("[번호]\t[이름]");
		for(PossibleTeacherDTO pt : ptList) {
			System.out.printf("%d\t%s\n", n+1, pt.getName());		
			n++;
		}
		
		System.out.print ("교사 선택 : ");
		sel = scan.nextLine();
		
		String teacherseq = ptList.get(Integer.parseInt(sel)-1).getSeq();
		
		int result = osDAO.add(startdate, enddate, opencourseq, subjectseq, teacherseq);
	
		if(result > 0) {
			System.out.println("과목이 등록되었습니다.");
		} else {
			System.out.println("과목이 등록되지 못했습니다.");
		}
		
		pause();
		
	}

	//개설 과목 수정
	private static void edit() {
		
		//개설 과목 출력시 개설 과정 정보(과정명, 과정기간(시작 년월일, 끝 년월일), 강의실)와 과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명을 출력한다. 
		System.out.println("\n\t\t\t\t\t\t[개설 과정 조회]");
		
		ArrayList<OpenCourseListDTO> ocList = osDAO.ocList();
		int n = 0;
		
		line();
		//개설 과정명, 개설 과정기간(시작 년월일, 끝 년월일), 강의실명, 개설 과목 등록 여부, 교육생 등록 인원
		System.out.println("[번호]\t\t[과정명]\t\t\t\t\t\t[과정기간]\t     [강의실]   [등록인원]");
		for(OpenCourseListDTO oc : ocList) {
			System.out.printf("%2d\t%-40s\t%s ~ %s\t%1s\t%s\n"
								,n+1
								,oc.getCoursename()
								,oc.getStartdate(), oc.getEnddate()
								,oc.getLectureroom()
								,oc.getRegstudentnumber());
			n++;
		}
		
		//개설 과정 선택
		System.out.print ("개설 과정 선택 : ");
		String sel = scan.nextLine();
		
		//시퀀스 번호를 가져온다.
		String OpenCourseq = ocList.get(Integer.parseInt(sel)-1).getSeq();
		
		//시퀀스 번호를 가지고 쿼리문 다시 작성 오버로딩
		ArrayList<SpecificOpenCourseDTO> socList = osDAO.socList(OpenCourseq);
		line();
		n=0;
		System.out.printf("과정명 : %s\n과정기간 : %s ~ %s\n",
				ocList.get(Integer.parseInt(sel)-1).getCoursename(),
				ocList.get(Integer.parseInt(sel)-1).getStartdate(), 
				ocList.get(Integer.parseInt(sel)-1).getEnddate());
		//개설 과목 정보(과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명)
		System.out.printf("[번호]\t[과목명]\t\t\t\t\t[과정기간]\t\t\t[교재명]\t\t\t\t\t[교사명]\n");
		for(SpecificOpenCourseDTO soc : socList) {
			System.out.printf("%2d\t%-25s\t%s ~ %s\t%-40s\t%s\n"
								, n+1
								, soc.getSubjectname()
								, soc.getStartdate(), soc.getEnddate()
								, soc.getTitle()
								, soc.gettName());
			n++;
		}
		
		System.out.print("수정할 과목 선택 : ");
		sel = scan.nextLine();
		
		//seq
		String seq = socList.get(Integer.parseInt(sel)-1).getSeq();
		
		System.out.println("\t\t\t\t\t\t[과목 조회]");
		ArrayList<SubjectListDTO> sList = osDAO.sList();
		n = 0;
		//과목 기초데이터
		System.out.println("[번호]\t[과목명]\t\t\t[구분]\t[수업기간(주)]");
		for(SubjectListDTO s : sList) {
			System.out.printf("%2d\t%-20s\t%s\t\t%s\n"
								,n+1
								,s.getSubjectName()
								,s.getDivision()
								,s.getPeriod());
			n++;
		}
		
		line();
		//개설 과정 선택
		System.out.print ("변경할 과목 선택 : ");
		sel = scan.nextLine();
		
		//시퀀스 번호를 가져온다.
		String subjectseq = sList.get(Integer.parseInt(sel)-1).getSeq();		//subjectseq
		
		line();
		
		
		//startdate
		System.out.print("수정할 시작 날짜(ex 2020/12/21) : ");
		String startdate = scan.nextLine();
	
		String cal[] = startdate.split("/");
		
		String year = cal[0];
		String month = cal[1];
		String date = cal[2];
		
		startdate = year+month+date;
		
		//기간
		String period = socList.get(Integer.parseInt(sel)-1).getPeriod();
		
		//enddate
		String enddate = osDAO.retEndDate(year, month, date, period);
		
		//teachserseq
		ArrayList<PossibleTeacherDTO> ptList = osDAO.ptList(startdate, subjectseq);
		n=0;
		System.out.println("[번호]\t[이름]");
		for(PossibleTeacherDTO pt : ptList) {
			System.out.printf("%d\t%s\n", n+1, pt.getName());		
			n++;
		}
		
		System.out.print ("교사 선택 : ");
		sel = scan.nextLine();
		
		String teacherseq = ptList.get(Integer.parseInt(sel)-1).getSeq();
	
		
		TblOpenSubjectDTO editDTO = new TblOpenSubjectDTO();
		editDTO.setSeq(seq);
		editDTO.setStartdate(startdate);
		editDTO.setEnddate(enddate);
		editDTO.setOpenCourseq(OpenCourseq);
		editDTO.setSubjectseq(subjectseq);
		editDTO.setTeacherseq(teacherseq);
		
		int result = osDAO.edit(editDTO);
		
		if(result > 0)
			System.out.println("수정이 성공하였습니다.");
		else
			System.out.println("수정이 실패하였습니다.");
		
		
	}

	//개설 과정 + 개설 과정의 개설 과목 조회
	private static void list() {
		
		//개설 과목 출력시 개설 과정 정보(과정명, 과정기간(시작 년월일, 끝 년월일), 강의실)와 과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명을 출력한다. 
		System.out.println("\n\t\t\t\t\t\t[개설 과정 조회]");
		
		ArrayList<OpenCourseListDTO> ocList = osDAO.ocList();
		int n = 0;
		
		line();
		//개설 과정명, 개설 과정기간(시작 년월일, 끝 년월일), 강의실명, 개설 과목 등록 여부, 교육생 등록 인원
		System.out.println("[번호]\t\t[과정명]\t\t\t\t\t\t[과정기간]\t     [강의실]   [등록인원]");
		for(OpenCourseListDTO oc : ocList) {
			System.out.printf("%2d\t%-40s\t%s ~ %s\t%1s\t%s\n"
								,n+1
								,oc.getCoursename()
								,oc.getStartdate(), oc.getEnddate()
								,oc.getLectureroom()
								,oc.getRegstudentnumber());
			n++;
		}
		
		//개설 과정 선택
		System.out.print ("개설 과정 선택 : ");
		String sel = scan.nextLine();
		
		//시퀀스 번호를 가져온다.
		String seq = ocList.get(Integer.parseInt(sel)-1).getSeq();
		
		//시퀀스 번호를 가지고 쿼리문 다시 작성 오버로딩
		ArrayList<SpecificOpenCourseDTO> socList = osDAO.socList(seq);
		line();
		n=0;
		System.out.printf("과정명 : %s\n과정기간 : %s ~ %s\n",
				ocList.get(Integer.parseInt(sel)-1).getCoursename(),
				ocList.get(Integer.parseInt(sel)-1).getStartdate(), 
				ocList.get(Integer.parseInt(sel)-1).getEnddate());
		
		//개설 과목 정보(과목명, 과목기간(시작 년월일, 끝 년월일), 교재명, 교사명)
		System.out.printf("[번호]\t[과목명]\t\t\t\t\t[과정기간]\t\t\t[교재명]\t\t\t\t\t[교사명]\n");
		for(SpecificOpenCourseDTO soc : socList) {
			System.out.printf("%2d\t%-25s\t%s ~ %s\t%-40s\t%s\n"
								, n+1
								, soc.getSubjectname()
								, soc.getStartdate(), soc.getEnddate()
								, soc.getTitle()
								, soc.gettName());
			n++;
		}
		
		
		pause();
	}
	
	private static void line() {
		
		for(int i=0; i<120; i++)
			System.out.print("-");
		System.out.println();
	}

	private static void pause() {
		line();
		System.out.println("계속하시려면 엔터를 누르세요.");
		scan.nextLine();
	}
	
}
