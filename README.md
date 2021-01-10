# 교육센터 관리 프로그램
 본 프로젝트는 오라클을 데이터베이스로 이용, JDBC를 활용하여 구현한 교육센터 관리 프로그램 제작 프로젝트입니다.
### 참여

조아라(조장) 이준오 장진영 황원준 권주홍 신지수 

### 구현 목표 기능

#### 관리자

1. 기초정보관리
   1. 과정 관리 : 과정 조회/수정/등록/삭제 기능
   2. 과목 관리 : 과목 조회/수정/등록/삭제 기능
   3. 강의실 관리 : 강의실 조회/수정/등록/삭제 기능
   4. 교재 관리 : 교재 조회/수정/등록/삭제 기능
2. 교사 관리

   1. 교사 정보 조회 기능
   2. 교사 정보 수정 기능
   3. 교사 등록 기능
   4. 교사 삭제 기능
3. 교육생 관리
   1. 교육생 정보 조회 기능
      1. 교육생 목록 조회 기능
      2. 교육생 검색 기능
   2. 교육생 정보 수정 기능
   3. 교육생 등록 기능
   4. 교육생 삭제 기능
   5. 출결 관리 및 조회 기능
      1. 개설과정별 조회 기능
      2. 교육생 검색 기능
      3. 기간별 조회 기능
4. 개설과목 관리
   1. 개설과목 조회/수정/등록/삭제 기능
5. 개설과정 관리
   1. 개설과정 조회/수정/등록/삭제 기능
6. 시험 및 성적 관리
   1. 시험 정보 조회 기능
   2. 성적 정보 조회 기능
7. 구인 정보 관리
   1. 구인 정보 조회/수정/등록/삭제 기능
8. 강의 평가 관리
   1. 강의 평가 조회/수정/등록/삭제 기능

#### 교사

1. 강의스케줄 조회  기능
2. 출결 조회
   1. 개설과정별 조회 기능
   2. 기간별 조회 기능
3. 시험 및 성적 관리
   1. 시험 정보 조회 기능
   2. 시험 배점 입력 기능
   3. 시험 정보 입력 기능
   4. 성적 정보 조회 기능
   5. 성적 정보 입력 기능
4. 강의 평가 조회
   1. 진행중인 과정 평가 조회 기능
   2. 완료된 과정 평가 조회 기능

#### 교육생

1. 출결 조회 및 관리
   1. 출결 현황 조회 기능
      1. 전체 조회 기능
      2. 월별 조회 기능
      3. 일별 조회 기능
   2. 출결 입력 기능
2. 성적 조회 기능
3. 강의 평가 등록 기능
4. 개인 정보 조회/수정 기능
5. 구인 정보 조회 기능



### 개발환경

운영체제 : Windows10, MacOS   Catalina 10.15.7  

개발툴 : Eclipse(OJDBC6)

개발언어 : JAVA(JDK 1.8)

### 프로그램 구성도

기획 당시 작성한 구성도

![](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/프로그램%20구성도.png)

### 개발일정

총 19일 소요

|    날짜    | 진행한 업무                   |
| :--------: | :---------------------------- |
| 12/2 (수)  | 아이템 선정 및 요구사항 분석  |
| 12/3 (목)  | 요구사항 분석                 |
| 12/4 (금)  | 요구사항 분석 ( 총 3일 소요)  |
| 12/5 (토)  |                               |
| 12/6 (일)  |                               |
| 12/7 (월)  | ERD 작성                      |
| 12/8 (화)  | ERD 작성 (총 2일 소요)        |
| 12/9 (수)  | DDL 작성 (총 1일 소요)        |
| 12/10 (목) | 더미데이터 작성 (총 1일 소요) |
| 12/11 (금) | 쿼리 작성                     |
| 12/12 (토) |                               |
| 12/13 (일) |                               |
| 12/14 (월) | 쿼리 작성                     |
| 12/15 (화) | 쿼리 작성                     |
| 12/16 (수) | 쿼리 작성                     |
| 12/17 (목) | 쿼리 작성                     |
| 12/18 (금) | 쿼리 작성 (총 6일 소요)       |
| 12/19 (토) |                               |
| 12/20 (일) |                               |
| 12/21 (월) | 기능 구현                     |
| 12/22 (화) | 기능 구현                     |
| 12/23 (수) | 기능 구현                     |
| 12/24 (목) | 기능 구현 (총 4일 소요)       |
| 12/25 (금) | 테스트                        |
| 12/28 (토) | 테스트 (총 2일 소요)          |

### 담당업무

|    단계    | 조아라 담당업무(교육생 담당)                                 |
| :--------: | :----------------------------------------------------------- |
|  기획단계  | - 담당업무 요구사항분석서 작성<br />- 담당업무 화면 설계 작성<br />- 담당업무 ERD 작성<br />- 담당업무 DDL 작성<br />- 프로그램 구성도 작성 |
|  구현단계  | - 교사/교육생/관리자 로그인 기능 구현<br />- 교육생 로그인시 간략한 교육생 정보(참여중인 과정명, 강의실, 출석률그래프) 조회 기능 구현<br />- 교육생 출결 전체 조회 기능 구현<br />- 교육생 출결 월별 조회(달력형식) 기능 구현<br />- 교육생 출결 일별 조회 기능 구현 <br />\- 교육생 출결 입력 기능 구현<br />\- 교육생 강의 평가 조회 기능 구현<br />\- 교육생 강의 평가 입력 기능 구현<br />\- 교육생 구인 정보 조회 기능 기능 |
| 마무리단계 | - 프로그램 담당업무 화면 캡처                                |

#### 조아라 구현 코드

 교사/교육생/관리자 로그인 기능 구현<br />- 교육생 로그인시 간략한 교육생 정보(참여중인 과정명, 강의실, 출석률그래프) 조회 기능 구현<br />- 교육생 출결 전체 조회(출결상태별 횟수 조회 포함) 기능 구현<br />- 교육생 출결 월별 조회(달력형식) 기능 구현<br />- 교육생 출결 일별 조회 기능 구현 <br />\- 교육생 출결 입력 기능 구현<br />\- 교육생 강의 평가 조회 기능 구현<br />\- 교육생 강의 평가 입력 기능 구현<br />\- 교육생 구인 정보 조회 기능 구현

1. [교사/교육생/관리자 로그인 기능 구현](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/src/com/sisteducenter/start/Login.java)

![교육생 로그인](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/교육생%20로그인.png)

![교사 로그인](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교사/교사%20로그인.png)

![관리자 로그인](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/관리자/관리자%20로그인.png)

2. [교육생 로그인시 간략한 교육생 정보(참여중인 과정명, 강의실, 출석률 그래프) 조회 기능 구현](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/src/com/sisteducenter/student/studentMain.java)

![](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/교육생%20메인.png)

3. [교육생 출결 전체 조회(출결상태별 횟수 조회 포함) 기능 구현](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/src/com/sisteducenter/attendance/ShowAttendanceAll.java)

![](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/전체출결조회1.png)

![](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/전체출결조회2.png)

4. [교육생 출결 월별 조회(달력형식) 기능 구현](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/src/com/sisteducenter/attendance/ShowAttendanceMonth.java)

![조회하고자 하는 월 입력](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/출결월별조회%20-%20월입력.png)

![조회화면](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/출결월별조회.png)

5. [교육생 출결 일별 조회 기능 구현](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/src/com/sisteducenter/attendance/ShowAttendanceDate.java)

![조회하고자 하는 날짜 입력](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/출결일별조회%20-%20날짜%20입력.png)

![조회화면](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/출결일별조회.png)

6. [교육생 출결 입력 기능 구현](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/src/com/sisteducenter/attendance/InsertAttendanceRecord.java)
7. ![입실체크 성공](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/입실체크완료.png)

![입실체크 메인화면](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/출결입력메뉴.png)

![이미 입실체크되어 있는 경우](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/입실체크%20-%20이미%20입실체크되어있는%20경우.png)

![입실체크 가능한 시간이 아닐 경우](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/입실체크%20-%20입실체크%20가능한%20시간이%20아닐%20경우.png)

![입력할 퇴실상태 선택](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/퇴실체크%20-%20입력할%20퇴실%20상태%20선택.png)

![퇴실체크 성공](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/퇴실체크완료.png)

![이미 퇴실체크되어 있는 경우](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/퇴실체크%20-%20이미%20퇴실체크되어있는%20경우.png)

![퇴실체크 가능한 시간이 아닐 경우](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/출결%20조회%20및%20입력/퇴실체크%20-%20퇴실체크가능한%20시간이%20아닐%20경우.png)

7. [교육생 강의 평가 조회 기능 구현](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/src/com/sisteducenter/jobinfo/ShowJobInfoList.java)

![강의평가조회](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/강의평가조회/강의평가조회.png)

8. (교육생 강의 평가 입력 기능 구현 )[https://github.com/arajo-hub/Java-Console-Project/blob/main/src/data/NoticeBoard.java]

![강의평가 완료](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/강의평가조회/강의평가완료.png)

![이미 강의평가한 과목을 선택했을 경우](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/강의평가조회/강의평가%20-%20이미%20강의평가한%20과목을%20선택했을%20경우.png)

9. (교육생 구인 정보 조회 기능 구현)[https://github.com/arajo-hub/Java-Oracle-Project/blob/main/src/com/sisteducenter/jobinfo/ShowJobInfoList.java]

![](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/구인정보조회/구인정보조회1.png)

![](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/구인정보조회/구인정보조회2.png)

![](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/구인정보조회/구인정보조회3.png)

![](https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B8%5D%20화면캡처/교육생/구인정보조회/구인정보조회4.png)

### 구현시 어려웠던 점

#### 자바, 오라클 중 어디에서 예외처리를 할 것인가?

 JDBC를 연결해서 DAO를 작성해서 진행하면서 예외처리를 자바에서 해줘야할지 오라클에서 해줘야할지 고민이 됐습니다. 자바에서는 오라클 관련한 예외를 표시할 때 일괄적으로 SQLException로 표시하지만, 오라클에서는 보다 자세하게 오류를 분류할 수 있었습니다. 그렇기 때문에 오라클에서 예외처리를 해주고 자바에서는 결과만 출력하는 것으로 결정했습니다.

 교육생/교사/관리자 로그인 기능을 구현할 때, 아이디가 존재하지 않는 예외를 처리하기 위해 프로시저에서 실행결과를 나타내는 변수(presult)를 만들어 정상적으로 실행됐을 경우 1을 입력, 아이디가 존재하지 않을 경우(no_data_found) 2를 입력하여 자바에서 반환받은 결과값이 2일 경우, 아이디가 존재하지 않는다는 문구를 출력하도록 했습니다.

(교육생/교사/관리자 로그인 프로시저 자세히 보기)[https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B3%5D%20쿼리/DML/9.%20로그인.sql]

#### 교육생 출결 입력 중 시간대별 상태별 처리를 어떻게 할 것인가?

 구현해야 할 기능 중 가장 까다로웠던 건 기능은 교육생 출결 입력 기능이었습니다. 출결 입력이 가능한 시간에 입력해야 한다는 요구사항과 상태별 구분(1. 정상, 2. 지각, 3. 조퇴, 4. 외출, 5. 병가, 6. 기타)이 가능해야 한다는 요구사항, 출결 입력은 하루에 1번 가능해야 한다는 요구사항이 있었습니다.

 이를 해결하기 위해 먼저 입실체크와 퇴실체크를 구분해서 프로시저를 작성했습니다.

 입실체크에서는 공휴일, 주말이 아니고, 오늘 입력된 입실체크 데이터가 없는 경우에 입력하는 시간에 따라 기타(8시 30분~9시 사이에 입력), 지각(9시부터 18시 사이에 입력)으로 나눠서 입력이 가능하도록 했습니다. 정상이 아니라 기타로 입력되도록 한 것은 퇴실체크까지 정상적으로 완료했을 경우 정상으로 체크되도록 하기 위함이었습니다.

 퇴실체크에서는 교육생이 입력하고자 하는 상태를 선택하여 입력할 수 있도록 했습니다. 공휴일, 주말이 아니고, 오늘 입력된 퇴실체크 데이터가 없는 경우에 6시(종료시간) 이전에 퇴실체크를 하는지 이후에 체크를 하는지에 따라 두 가지 경우로 나눴습니다.

 첫번째는 6시~6시 30분 사이에 퇴실체크하는 경우입니다. 이 경우는 정상적으로 퇴실체크를 하는 경우로, 입실체크시 지각으로 입력된 경우 출결상태를 바꾸지 않고 퇴실시각만 입력되도록 구현하고, 기타로 입력된 경우는 상태를 정상으로 바꾸고 퇴실시각을 입력되도록 했습니다.

 두번째는 6시 이전에 퇴실체크하는 경우입니다. 이 경우는 6가지 경우 중 3. 조퇴, 4. 외출, 5. 병가, 6. 기타만 입력이 가능합니다. 그렇기 때문에 6시 이후인데 위의 네 경우를 입력했을 때에는 예외처리를 해서 6시 이전에만 입력이 가능하도록 했습니다.

(교육생 출결 입력 프로시저 자세히 보기)[https://github.com/arajo-hub/Java-Oracle-Project/blob/main/document/%5B3%5D%20쿼리/DML/교육생/4.%20출결입력(아라).sql]

### 소감

 뜻하지 않게 조장을 맡아 평소처럼 조원으로 참여했을 때와는 많이 다른 입장을 느낄 수 있었습니다. 프로젝트를 진행함에 있어 조금 더 책임감을 느끼게 되었고, 자바와 오라클을 JDBC로 연결하는 이번 프로젝트를 진행하며 쿼리를 잘 짜는 것의 중요성을 느꼈습니다. 그리고 데이터모델링을 진행할 때, ERD 작업시 적절한 정규화가 필요함을 느꼈습니다.
