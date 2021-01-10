package com.sisteducenter.teacher;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 교사 메인 화면을 담당하는 클래스입니다.
 * @author 신지수, 황원준
 *
 */
public class TeacherMain {


	private static Scanner scan;
	private static TeacherSheduleDAO tsdao;
	private static StudentInfoDAO sdao;
	private static EndSubListDAO esldao;
	private static EvaluationPastDAO epdao;
	private static AllotListDAO aldao;
	private static InputAllotDAO iadao;
	private static EndSubListForAllotDAO eslfadao;
	private static EvaluationCurrentDAO ecdao;
	private static TeacherGradeDAO tgDAO;

	static {
		scan = new Scanner(System.in);
		iadao = new InputAllotDAO();
		tsdao = new TeacherSheduleDAO(); 
		sdao = new StudentInfoDAO();
		ecdao = new EvaluationCurrentDAO();
		epdao = new EvaluationPastDAO();
		esldao = new EndSubListDAO();
		aldao = new AllotListDAO();
		eslfadao = new EndSubListForAllotDAO();
		tgDAO = new TeacherGradeDAO();
	}

	/**
	 * 교사 메인 메뉴를 나타내는 메서드입니다.
	 * @param teacherSeq : 교사번호입니다.
	 */
	public static void showMain(String teacherSeq) {

		boolean loop = true;

		while(loop) {

			System.out.println();
			System.out.println();
			System.out.println("[교사 로그인 메뉴]");
			System.out.println("1. 강의 스케줄 조회");
			System.out.println("2. 출결조회");
			System.out.println("3. 시험 및 성적 관리");
			System.out.println("4. 강의 평가 조회");
			System.out.println();
			System.out.println();
			System.out.print("선택 : ");

			String sel = scan.nextLine();

			if(sel.equals("1")) { //"1. 강의 스케줄 조회
				schedultList(teacherSeq);
			} else if(sel.equals("2")) { //2. 출결조회
				attendance(teacherSeq);
			} else if(sel.equals("3")) { //3. 시험 및 성적 관리 - 배점입력
				scoreManagement(teacherSeq);
			} else if(sel.equals("4")) { //4. 강의 평가 조회
				evaluationList(teacherSeq);
			} else  {
				loop = false;
			} //if

		}
	}

	//신지수
	/**
	 * 교사 > 출결 조회 및 관리
	 * 교사가 강의한 과정번호를 통해 전체 출결, 기간별 출결을 조회 할 수 있다. 
	 * @param seq 교사번호
	 */
	private static void attendance(String teacherSeq) {
		System.out.println("\n[출결 조회 및 관리]");
		System.out.println();
		boolean loop = true;
		
		ArrayList<AttendanceDTO> attlist = tgDAO.AttendanceCourselist(teacherSeq);
		for (AttendanceDTO attdto : attlist) {
			
			System.out.println("[과정번호] : " + attdto.getCourseSeq());
			System.out.println("[과정명] : " + attdto.getCourseName());
			System.out.println("[과정기간] : " + attdto.getCourseStart() + " ~ " + attdto.getCourseEnd());
			System.out.println();
		}
		
		System.out.print("과정번호 선택 : ");
		String selcour = scan.nextLine();

		while(loop) {
			
			System.out.println("\n[출결 조회]");
			System.out.println("1. 전체 학생 조회");
			System.out.println("2. 특정 학생 조회");
			System.out.println("3. 특정기간별 전체 학생 조회");
			System.out.println("4. 특정기간별 특정 학생 조회");
			System.out.println("0. 메뉴로 돌아가기");
			System.out.print("선택(번호) : ");
			String sel = scan.nextLine();
			System.out.println();
			
			if(sel.equals("1")) {
				attendAllAll(selcour, teacherSeq);
			} else if (sel.equals("2")) {
				attendStudentAll(selcour, teacherSeq);
			} else if (sel.equals("3")) {
				attendAllPeriod(selcour, teacherSeq);
			} else if (sel.equals("4")) {
				attendStudentPeriod(selcour, teacherSeq);
			} else {
				loop = false;
			}
			
		}
		
		pause();
		
	}
	
	//신지수
	private static void attendAllAll(String selcour, String seq) {
		ArrayList<AttendanceDTO> attlist = tgDAO.attendAllAll(selcour, seq);
		
		System.out.println("\n[번호]\t[교육생이름]\t[입실시간]\t\t  [퇴실시간]\t  [출결상태]");
		for (AttendanceDTO attdto : attlist) {
			System.out.printf("%s\t   %s     %s \t%s\t%s"
					, attdto.getStudentSeq()
					, attdto.getStudentName()
					, attdto.getInDate()
					, attdto.getOutDate()
					, attdto.getState());
			System.out.println();
		}
		
		pause();
	}

	//신지수
	/**
	 * 교사 > 특정 과정의 특정 교육생 출결 조회
	 * @param selcour 과정번호
	 * @param seq 교사번호
	 */
	private static void attendStudentAll(String selcour, String seq) {
		ArrayList<AttendanceDTO> attlist = tgDAO.StudentAttList(selcour, seq);
		
		System.out.println("[학생번호]   [학생이름]");
		for (AttendanceDTO attdto : attlist) {
			System.out.printf("%s\t%s"
					, attdto.getStudentSeq()
					, attdto.getStudentName());
			System.out.println();
		}
		
		System.out.print("학생 선택(번호) : ");
		String selstu = scan.nextLine();
		
		ArrayList<AttendanceDTO> attlist2 = tgDAO.attendStudentAll(selcour, seq, selstu);
		
		System.out.println("\n[번호]\t[교육생이름]\t[입실시간]\t\t  [퇴실시간]\t  [출결상태]");
		for (AttendanceDTO attdto : attlist2) {
			System.out.printf("%s\t   %s     %s \t%s\t%s"
					, attdto.getStudentSeq()
					, attdto.getStudentName()
					, attdto.getInDate()
					, attdto.getOutDate()
					, attdto.getState());
			System.out.println();
		}
		
		pause();
	}

	//신지수
	/**
	 * 교사 > 특정 과정의 특정기간별 전체 교육생 조회
	 * @param selcour 과정번호
	 * @param seq 교사번호
	 */
	private static void attendAllPeriod(String selcour, String seq) {
		System.out.println("조회를 원하는 날짜를 입력해주세요");
		System.out.print("시작(yyyy-mm-dd) : ");
		String attdateStart = scan.nextLine();
		System.out.print("종료(yyyy-mm-dd) : ");
		String attdateEnd = scan.nextLine();
		
		ArrayList<AttendanceDTO> attlist = tgDAO.procSelDateAttendance(selcour, seq, attdateStart, attdateEnd);
		
		System.out.println("\n[번호]\t[교육생이름]\t[입실시간]\t\t  [퇴실시간]\t  [출결상태]");
		for (AttendanceDTO attdto : attlist) {
			System.out.printf("%s\t   %s     %s \t%s\t%s"
					, attdto.getStudentSeq()
					, attdto.getStudentName()
					, attdto.getInDate()
					, attdto.getOutDate()
					, attdto.getState());
			System.out.println();
		}
		pause();
	}
	
	//신지수
	/**
	 * 교사 > 특정 과정의 특정기간별 특정 교육생 조회
	 * @param selcour 과정번호
	 * @param seq 교사번호
	 */
	private static void attendStudentPeriod(String selcour, String seq) {
		ArrayList<AttendanceDTO> attlist = tgDAO.StudentAttList(selcour, seq);
		
		System.out.println("[학생번호]   [학생이름]");
		for (AttendanceDTO attdto : attlist) {
			System.out.printf("%s\t%s"
					, attdto.getStudentSeq()
					, attdto.getStudentName());
			System.out.println();
		}
		
		System.out.print("학생 선택(번호) : ");
		String selstu = scan.nextLine();
		
		System.out.println("조회를 원하는 날짜를 입력해주세요");
		System.out.print("시작(yyyy-mm-dd) : ");
		String attdateStart = scan.nextLine();
		System.out.print("종료(yyyy-mm-dd) : ");
		String attdateEnd = scan.nextLine();
		
		ArrayList<AttendanceDTO> attlist2 = tgDAO.attendStudentPeriod(selcour, seq, attdateStart, selstu, attdateEnd);
		
		System.out.println("\n[번호]\t[교육생이름]\t[입실시간]\t\t  [퇴실시간]\t  [출결상태]");
		for (AttendanceDTO attdto : attlist2) {
			System.out.printf("%s\t   %s     %s \t%s\t%s"
					, attdto.getStudentSeq()
					, attdto.getStudentName()
					, attdto.getInDate()
					, attdto.getOutDate()
					, attdto.getState());
			System.out.println();
		}
		pause();
	}

	//황원준
	private static void schedultList(String teacherSeq) {

		System.out.println();
		System.out.println();
		System.out.println("[강의 스케줄 조회]");

		ArrayList<TeacherSheduleDTO> list = tsdao.list(teacherSeq);

		System.out.println("[과목번호]\t\t\t[과정명]\t\t\t[과정 시작일]\t [과정 종료일]\t [과정상태]\t [강의실]\t [과목명]\t\t [과목시작일]\t [과목 종료일]\t [교재명]\t [교육생 등록인원]");
		for(TeacherSheduleDTO dto : list) {

			System.out.printf("%s\t %s\t %s\t %s\t  %s\t %s\t\t %-15s\t %s\t %s\t %-30.25s\t %s\n"
					, dto.getOs_seq()
					, dto.getC_name()
					, dto.getOc_startDate()
					, dto.getOc_endDate()
					, dto.getStateCourse()
					, dto.getOc_lectureRoom()
					, dto.getS_subjectName()
					, dto.getOs_startDate()
					, dto.getOs_endDate()
					, dto.getB_title()
					, dto.getCountStudent());

		}

		System.out.println("[해당 과목의 학생 정보를 보려면 과목 번호를 입력하시오]");
		System.out.print("과목 번호 입력 : ");
		String subSeq = scan.nextLine();


		ArrayList<StudentInfo1DTO> stuInfo = sdao.stuInfo(subSeq);

		System.out.println("[교육생 이름]\t [전화번호]\t [등록일]\t [참여상태]\t [과목명]\t");

		for(StudentInfo1DTO sdto : stuInfo) {

			System.out.printf("%s\t\t %s\t %s\t %s\t\t %s\n"
					, sdto.getS_name()
					, sdto.getS_tel()
					, sdto.getReg_regdate()
					, sdto.getState()
					, sdto.getS_subjectName());

		}



	}

	//황원준 + 신지수
	private static void scoreManagement(String teacherSeq) {

		System.out.println("[시험 및 성적 관리]");

		System.out.println("1. 시험 배점 및 정보 입력");
		System.out.println("2. 배점 조회");
		System.out.println("3. 성적 정보 조회 및 입력");	

		System.out.print("선택 : ");
		String sel = scan.nextLine();

		if(sel.equals("1")) {
			inputScoreAllot(teacherSeq);
		} else if(sel.equals("2")) {
			AllotList(teacherSeq);
		} else if(sel.equals("3")) {
			grade(teacherSeq);
		} 

	}

	//신지수
	/**
	 * 교사 > 끝난 과목 검색, 특정 교육생 선택
	 * 강사가 강의를 끝낸 과목의 특정 교육생을 찾아서 성적을 출력할 수 있다. 
	 * @param seq 교사번호
	 */
	private static void grade(String teacherSeq) {
		System.out.println("\n[성적 입출력]");
		
		//1. 강의를 마친 과목의 목록 출력
		ArrayList<GradeDTO> glist = tgDAO.grade(teacherSeq);
		
		System.out.println("선택 가능한 과목 갯수 : " + glist.size()); //컬럼의 갯수 
		System.out.println();
		
		for (GradeDTO dto : glist) {

			System.out.println("[과정명] : " + dto.getCourseName());
			System.out.println("[과정 시작종료일] : " + dto.getCourseStart() + " ~ " + dto.getCourseEnd());
			System.out.println("[강의실] : " + dto.getClassRoom());
			System.out.println("[과목번호] : " + dto.getOpenSubjectSeq());
			System.out.println("[과목명] : " + dto.getSubjectName());
			System.out.println("[교재명] : " + dto.getBookTitle());
			System.out.println("[필기/실기/출결 배점] : " + dto.getWritingAllot() + " / " + dto.getPracticeAllot() + " / " + dto.getAttendanceAllot());
			System.out.println("[성적등록여부] : " + dto.getRegisterGrade());
			System.out.println();
			}
		
		//2. 과목번호로 선택 -> 교육생 목록 출력	
		//진행상태가 중도탈락일때 : 탈락 날짜 출력, 이후 성적 입력X 
		System.out.print("과목번호 선택: ");
		String selsub = scan.nextLine();
		
		ArrayList<StudentInfoDTO> silist = tgDAO.studentList(teacherSeq, selsub);
		
		System.out.println("\n[번호]\t[학생이름] [수강상태] [필기성적] [실기성적] [출결성적] [중도포기날짜]");
		for (StudentInfoDTO sidto : silist) {
			System.out.printf("%s\t%s\t\t%s\t\t%s\t%s\t%s\t\t%s"
								, sidto.getStudentSeq()
								, sidto.getStudentName()
								, sidto.getRegistState()
								, sidto.getWritingScore()
								, sidto.getPracticeScore()
								, sidto.getAttendanceScore()
								, sidto.getStateFailDate());
			System.out.println();
		}
		System.out.println();
		
		//3. 특정 교육생 선택 -> 시험 점수 입력 가능
		System.out.print("학생번호 선택: ");
		String selstu = scan.nextLine();
		System.out.println();
		
		//4. 실기필기출결 점수 입력/삭제/수정
		boolean loop = true;
		if (silist.get(0).getRegistState().equals("중도포기")) {
			System.out.println("성적 입력이 불가능합니다.");
			loop = false;
			
		} else {
			while(loop) {
				System.out.println("1. 성적 등록");
				System.out.println("2. 성적 삭제");
				System.out.println("0. 메뉴로 돌아가기");
				System.out.print("선택(번호) : ");
				String sel = scan.nextLine();
				
				if(sel.equals("1")) { 
					scoreInput(selsub, selstu, teacherSeq);
				} else if(sel.equals("2")) {
					scoreDelete(selsub, selstu, teacherSeq);
				} else {
					loop = false;
				}
			}
		}
		
		pause();
	}//grade 과목목록 + 학생정보

	/**
	 * 교사 > 성적 입력
	 * 특정 교육생의 성적을 입력할 수 있다. 
	 * @param selsub 과목번호
	 * @param selstu 학생번호 
	 * @param seq 교사번호
	 */
	private static void scoreInput(String selsub, String selstu, String seq) {
		
		System.out.println("\n[성적 등록하기]");
		System.out.print("필기 성적 입력 : ");
		String write = scan.nextLine();
		System.out.print("실기 성적 입력 : ");
		String practice = scan.nextLine();
		System.out.print("출결 성적 입력 : ");
		String attendance = scan.nextLine();
		
		StudentInfoDTO sidto = new StudentInfoDTO();
		
		sidto.setTeacherSeq(seq);
		sidto.setOpenSubjectSeq(selsub);
		sidto.setStudentSeq(selstu);
		sidto.setWritingScore(write);
		sidto.setPracticeScore(practice);
		sidto.setAttendanceScore(attendance);
		
		int result = tgDAO.gradeUpdate(sidto);
		
		if (result > 0) {
			System.out.println("성적 입력 성공");
		} else {
			System.out.println("성적 입력 실패");
		}
		pause();
	}

	//신지수
	private static void scoreDelete(String selsub, String selstu, String seq) {
		System.out.println("\n[성적 삭제하기]");
		boolean loop = true;

		while(loop) {
			System.out.println("1. 필기 점수 삭제");
			System.out.println("2. 실기 점수 삭제");
			System.out.println("2. 출결 점수 삭제");
			System.out.println("0. 메뉴로 돌아가기");
			System.out.print("선택(번호) : ");
			String sel = scan.nextLine();
			
			if(sel.equals("1")) { 
				System.out.println();
				int result = tgDAO.deleteWrite(selsub, selstu, seq);
				//System.out.println(result); //1삭제 0실패
				
				if (result > 0) {
					System.out.println("필기점수 삭제 성공");
				} else {
					System.out.println("필기점수 삭제 실패");
				}
				
			} else if(sel.equals("2")) {
				
				System.out.println();
				int result = tgDAO.deletePractice(selsub, selstu, seq);
				
				if (result > 0) {
					System.out.println("실기점수 삭제 성공");
				} else {
					System.out.println("실기점수 삭제 실패");
				}
				
			} else if(sel.equals("3")) {
				
				System.out.println();
				int result = tgDAO.deleteAttend(selsub, selstu, seq);
				
				if (result > 0) {
					System.out.println("출결점수 삭제 성공");
				} else {
					System.out.println("출결점수 삭제 실패");
				}
				
			} else {
				loop = false;
			}
		}
		pause();
	}

	//황원준
	private static void AllotList(String teacherSeq) {
		//개설과정 번호 받기
		//출력

		//개설과정 리스트 출력
		ArrayList<EndSubListForAllotDTO> list = eslfadao.list(teacherSeq);
		System.out.println();
		System.out.println();
		System.out.println("[개설과정번호]\t[과목명]\t\t[과목시작일]\t[과목종료일]\t[과목상태]\t[과정명]\t[과정시작일]\t[과정종료일]\t[인원]\t[과정상태]");

		for(EndSubListForAllotDTO esldto : list) {

			System.out.printf("%s\t%s\t\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n" 
					, esldto.getOc_seq()
					, esldto.getS_subjectname()
					, esldto.getOs_startdate()
					, esldto.getOs_enddate()
					, esldto.getSub_state()
					, esldto.getC_name()
					, esldto.getOc_startdate()
					, esldto.getOc_enddate()
					, esldto.getStu_count()
					, esldto.getCourse_state());


		}
		System.out.println();
		System.out.println();


		//과정 번호 입력하면 배점 출력
		System.out.print("배점을 확인하고 싶은 개설과정 번호를 입력 : ");
		String couSeq = scan.nextLine();

		ArrayList<AllotListDTO> alList = aldao.alList(couSeq);
		System.out.println("[과목번호]\t[과정명]\t[과정시작일]\t[과정종료일]\t[과목명]\t[과목시작일]\t[과목종료일]\t[교재명]\t[출결배점]\t[필기배점]\t[실기배점]");

		for(AllotListDTO aldto : alList) {

			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n"
					, aldto.getOs_seq()
					, aldto.getC_name()
					, aldto.getOc_startdate()
					, aldto.getOc_enddate()
					, aldto.getS_subjectname()
					, aldto.getOs_startdate()
					, aldto.getOs_enddate()
					, aldto.getB_title()
					, aldto.getSa_attendanceallot()
					, aldto.getSa_handwritingallot()
					, aldto.getSa_practiceallot());
		}
		System.out.println();
		System.out.println();


	}

	//황원준
	private static void inputScoreAllot(String teacherSeq) {

		//강의를 마친 과목을 출력
		//과목 번호 선택
		//배점 입력

		ArrayList<EndSubListDTO> eslList = esldao.eslList(teacherSeq); 
		System.out.println("[과목번호]\t[과목명]\t[과목시작일]\t[과목종료일]\t[과목상태]\t[과정명]\t[과정시작일]\t[과정종료일]\t[인원]\t[과정상태]\t[교사명]\t[평점]");
		for(EndSubListDTO esldto : eslList) {

			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n" 
					, esldto.getOs_seq()
					, esldto.getS_subjectname()
					, esldto.getOs_startdate()
					, esldto.getOs_enddate()
					, esldto.getSub_state()
					, esldto.getC_name()
					, esldto.getOc_startdate()
					, esldto.getOc_enddate()
					, esldto.getStu_count()
					, esldto.getCourse_state()
					, esldto.getT_name()
					, esldto.getAvg_score());
		}
		System.out.println();
		System.out.println();

		System.out.println("[배점 입력할 과목 ]");
		System.out.print("과목 번호 입력 : ");
		String subSeq = scan.nextLine();

		// TODO 여기에 for문 추가해서
		// 루프돌리면서 과목번호맞는 dto 찾아서 개설과목번호, 개설과정번호 넘겨주기.
		for (EndSubListDTO dto:eslList) {
			if (dto.getOs_seq().equals(subSeq)) {
				addExamAllot(subSeq, dto.getOc_seq());
			}
		}
	}

	//황원준 - 위에서 넘겨준 개설과목번호, 개설과정번호 받기
	private static void addExamAllot(String subSeq, String openCourseSeq) {

		System.out.print("필기 배점 입력 : ");
		String write = scan.nextLine();

		System.out.print("실기 배점 입력 : ");
		String practice = scan.nextLine();

		System.out.print("출결 배점 입력 : ");
		String attendance = scan.nextLine();

		System.out.print("시험 날짜 입력 : ");
		String examDate = scan.nextLine();

		InputAllotDTO iadto = new InputAllotDTO();

		iadto.setSub(subSeq);
		iadto.setOpenCourse(openCourseSeq); 
		iadto.setWrite(write);
		iadto.setPractice(practice);
		iadto.setAttendance(attendance);
		iadto.setExamDate(examDate);

		int result = iadao.inputAllot(iadto);

		if (result > 0) {
			System.out.println("배점 입력 성공");
		} else {
			System.out.println("배점 입력 실패");
		}

	}

	//황원준
	private static void evaluationList(String teacherSeq) {

		System.out.println("강의 평가 조회");
		System.out.println("1. 진행과정 평가 조회");
		System.out.println("2. 과거 평가 조회");
		System.out.print("입력 : ");
		String sel = scan.nextLine();

		if(sel.equals("1")) {
			evalCur(teacherSeq);
		} else if(sel.equals("2")) {
			evalPast(teacherSeq);
		}

	}

	//황원준
	private static void evalPast(String teacherSeq) {

		ArrayList<EvaluationPastDTO> epList = epdao.epList(teacherSeq);
		for(EvaluationPastDTO epdto : epList) {

			System.out.printf("%s %s %s %s %s %s %s %s %s %s %s %s\n" 
					, epdto.getOs_seq()
					, epdto.getS_subjectname()
					, epdto.getOs_startdate()
					, epdto.getOs_enddate()
					, epdto.getSub_state()
					, epdto.getC_name()
					, epdto.getOc_startdate()
					, epdto.getOc_enddate()
					, epdto.getStu_count()
					, epdto.getCourse_state()
					, epdto.getT_name()
					, epdto.getAvg_score());
		}
		System.out.println();
		System.out.println();

	}

	//황원준
	private static void evalCur(String teacherSeq) {


		ArrayList<EvaluationCurrentDTO > ecList =  ecdao.ecList(teacherSeq);

		System.out.println();
		for(EvaluationCurrentDTO ecdto : ecList) {

			System.out.printf("%s %s %s %s %s %s %s %s %s %s %s %s\n" 
					, ecdto.getOs_seq()
					, ecdto.getS_subjectname()
					, ecdto.getOs_startdate()
					, ecdto.getOs_enddate()
					, ecdto.getSub_state()
					, ecdto.getC_name()
					, ecdto.getOc_startdate()
					, ecdto.getOc_enddate()
					, ecdto.getStu_count()
					, ecdto.getCourse_state()
					, ecdto.getT_name()
					, ecdto.getAvg_score());
		}
		System.out.println();
		System.out.println();

	}

	private static void pause() {
		System.out.println();
		System.out.println("계속하시라면 엔터를 누르세요.");
		scan.nextLine();	
	}


}

