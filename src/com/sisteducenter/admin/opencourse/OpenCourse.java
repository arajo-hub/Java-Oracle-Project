package com.sisteducenter.admin.opencourse;

import java.util.ArrayList;
import java.util.Scanner;

import com.sisteducenter.admin.AdminMain;

/**
 * 개설과정의 조회/등록/삭제/수정이 가능한 클래스입니다.
 * @author 장진영
 *
 */
public class OpenCourse {
	
	private static Scanner scan;
	private static OpenCourseDAO ocDAO;
	
	static {
		scan = new Scanner(System.in);
		ocDAO = new OpenCourseDAO();
	}
	
	/**
	 * 개설 과정 관리의 메뉴를 나타내는 메서드입니다.
	 */
	public static void openCourseMain() {
		
		boolean loop = true;
		
		while(loop) {
			System.out.println("\t\t\t\t1. 개설 과정 조회");
			System.out.println("\t\t\t\t2. 개설 과정 수정");
			System.out.println("\t\t\t\t3. 개설 과정 등록");
			System.out.println("\t\t\t\t4. 개설 과정 삭제");
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
					loop = false;
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

	//개설 과정 삭제
	private static void delete() {
		
		System.out.println("\n\t\t\t\t\t\t[개설 과정 삭제]");
		
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
		System.out.print ("삭제할 개설 과정 선택 : ");
		int sel = scan.nextInt();
		
		//시퀀스 번호를 가져온다.
		String seq = ocList.get(sel-1).getSeq();
		
		
		int result = ocDAO.delete(seq);
		
		if(result > 0) {
			System.out.println("과정이 삭제되었습니다.");
		} else {
			System.out.println("과정을 삭제하지 못했습니다.");
		}
		
		pause();
		
	}

	//개설 과정 등록
	private static void add() {
		
		System.out.println("\n\t\t\t\t\t\t[과정 조회]");
		
		ArrayList<CourseListDTO> cList = ocDAO.cList();
		int n = 0;
		
		line();
		//과정명, 과정기간
		System.out.println("[번호]\t\t\t[과정명]\t\t\t\t\t[과정기간(주)]");
		for(CourseListDTO c : cList) {
			System.out.printf("%2d\t%-40s\t\t%5s\n"
								,n+1
								,c.getName()
								,c.getPeriod());
			n++;
		}
		
		System.out.print("등록하실 과정을 선택하세요 : ");
		String sel = scan.nextLine();
		//과정번호
		int courseseq = Integer.parseInt(cList.get(Integer.parseInt(sel)-1).getSeq());
		
		System.out.print("과정 시작일을 입력하세요 (예 : 2020/01/23) : ");
		String startdate = scan.nextLine();
		
		//날짜를 '/'기준으로 나눈다
		String cal[] = startdate.split("/");
		
		String year = cal[0];
		String month = cal[1];
		String date = cal[2];
		
		startdate = year+month+date; //yyyymmdd 
		
		//기간
		String period = cList.get(Integer.parseInt(sel)-1).getPeriod();
	
		//종료날짜 계산
		String enddate = ocDAO.retEndDate(year, month, date, period);
	
		//빈 강의실 목록 출력
		ArrayList<EmptyLectureRoomDTO> elrList = ocDAO.elrList(startdate);	
		n = 0;
		System.out.println("[번호] [강의실] [수용인원]");
		for(EmptyLectureRoomDTO elr : elrList) {
			System.out.printf("%d %s %s\n", n+1, elr.getRoom(), elr.getCapacity());
			n++;
		}
		System.out.print("강의실번호를 선택하세요 : ");
		sel = scan.nextLine();
		//강의실번호
		int rectureroomseq = Integer.parseInt(elrList.get(Integer.parseInt(sel)-1).getRoom());
		
		int result = ocDAO.add(startdate, enddate, rectureroomseq, courseseq);
		
		if(result > 0) {
			System.out.println("강좌가 등록되었습니다.");
		} else {
			System.out.println("강좌가 등록되지못했습니다.");
		}
		
		line();
		
		
	}

	//개설 과정 수정
	private static void edit() {
		
		System.out.println("\n\t\t\t\t\t\t[개설 과정 수정]");
		
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
		System.out.print ("수정할 개설 과정 선택 : ");
		String sel = scan.nextLine();
		
		//시퀀스 번호를 가져온다.
		TblOpenCourseDTO dto = new TblOpenCourseDTO();
		String seq = ocList.get(Integer.parseInt(sel)-1).getSeq();
		String startdate = ocList.get(Integer.parseInt(sel)-1).getStartdate();		//개설 과정기간(시작)
		String enddate = ocList.get(Integer.parseInt(sel)-1).getEnddate();			//개설 과정기간(끝)
		String lectureroom = ocList.get(Integer.parseInt(sel)-1).getLectureroom();		//강의실명
		String courseseq = ocList.get(Integer.parseInt(sel)-1).getCourseseq();
		
		dto.setSeq(seq);
		dto.setStartdate(startdate);
		dto.setEnddate(enddate);
		dto.setLectureroomseq(lectureroom);
		dto.setCourseseq(courseseq);
		
		//과정명
		ArrayList<CourseListDTO> cList = ocDAO.cList();
		n = 0;
		System.out.println("[번호]\t\t\t[과정명]\t\t\t\t\t[과정기간(주)]");
		for(CourseListDTO c : cList) {
			System.out.printf("%2d\t%-40s\t\t%5s\n"
								,n+1
								,c.getName()
								,c.getPeriod());
			n++;
		}
		
		//과정
		System.out.print("수정하실 과정을 선택하세요 : ");
		sel = scan.nextLine();
		courseseq = cList.get(Integer.parseInt(sel)-1).getSeq();
		
		//시작날짜는 수정가능
		System.out.print("수정할 시작 날짜(ex 2020/12/21) : ");
		startdate = scan.nextLine();
		if(startdate.equals("")) {
			startdate = dto.getStartdate();
		}
		
		String cal[] = startdate.split("/");
		
		String year = cal[0];
		String month = cal[1];
		String date = cal[2];
		
		startdate = year+month+date;
		
		//기간
		String period = cList.get(Integer.parseInt(sel)-1).getPeriod();
		
		
		//종료날짜는 과정의 period로 계산
		enddate = ocDAO.retEndDate(year, month, date, period);
		
		ArrayList<EmptyLectureRoomDTO> elrList = ocDAO.elrList(startdate);	
		n = 0;
		System.out.println("[번호] [강의실] [수용인원]");
		for(EmptyLectureRoomDTO elr : elrList) {
			System.out.printf("%d %s %s\n", n+1, elr.getRoom(), elr.getCapacity());
			n++;
		}
		System.out.print("강의실번호를 선택하세요 : ");
		sel = scan.nextLine();
		//강의실번호
		lectureroom = elrList.get(Integer.parseInt(sel)-1).getRoom();
		
		TblOpenCourseDTO editOC = new TblOpenCourseDTO();
		editOC.setSeq(seq);
		editOC.setStartdate(startdate);
		editOC.setEnddate(enddate);
		editOC.setLectureroomseq(lectureroom);
		editOC.setCourseseq(courseseq);
		
		int result = ocDAO.edit(seq, editOC);
		
		if(result > 0)
			System.out.println("수정이 완료되었습니다.");
		else
			System.out.println("수정을 실패하였습니다.");
		
		
		pause();
	}

	//개설강좌 조회
	//특정개설강좌를 조회할 수 있다.
	private static void list() {
		
		System.out.println("\n\t\t\t\t\t\t[개설 과정 조회]");
		
		ArrayList<OpenCourseListDTO> ocList = ocDAO.ocList();
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
		int sel = scan.nextInt();
		
		//시퀀스 번호를 가져온다.
		String seq = ocList.get(sel-1).getSeq();
		
		//시퀀스 번호를 가지고 쿼리문 다시 작성 오버로딩
		ArrayList<SpecificOpenCourseDTO> socList = ocDAO.socList(seq);
		line();
		n=0;
		System.out.printf("과정명 : %s\n과정기간 : %s ~ %s\n",ocList.get(sel-1).getCoursename(), ocList.get(sel-1).getStartdate(), ocList.get(sel-1).getEnddate());
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
		
		//교육생 이름, 주민번호 뒷자리, 전화번호, 등록일, 수료 및 중도탈락
		ArrayList<SpecificStudentDTO> ssList = ocDAO.ssList(seq);
		
		
		line();
		System.out.println("\t\t\t\t[학생명단]");
		System.out.println("[이름]\t[주민번호]\t\t  [전화번호]\t\t[등록일]\t\t[수료상태]");
		for(SpecificStudentDTO ss : ssList) {
			System.out.printf("%s\t%s\t\t%s\t\t%s\t   %s\n",ss.getsName(), ss.getSsn(), ss.getTel(), ss.getRegdate(), ss.getRegstate());
		}
		
		System.out.println();
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



