# 교육센터 관리 프로그램
 본 프로젝트는 오라클을 데이터베이스로 이용, JDBC를 활용하여 구현한 교육센터 관리 프로그램 제작 프로젝트입니다.
### 참여

조아라 이준오 장진영 황원준 권주홍 신지수 

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

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/date-modeling.png)

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

1. [회원 - 현재 상영중인 영화 목록 조회 기능 및 영화 선택 기능](https://github.com/arajo-hub/Java-Console-Project/blob/main/src/com/test/member/MovieList.java)

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/영화별예매/영화별예매%20-%20영화별조회.png)

2. [회원 - 영화별 상영내역조회 기능 및 상영정보 선택 기능](https://github.com/arajo-hub/Java-Console-Project/blob/main/src/com/test/member/ChooseMovie.java)

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/영화별예매/상영번호%20선택.png)

3. [비회원 - 현재 상영중인 영화 목록 조회 기능 및 영화 선택 기능](https://github.com/arajo-hub/Java-Console-Project/blob/main/src/com/test/nonmember/MovieList.java)

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/영화별예매/영화별예매%20-%20영화별조회.png)

4. [비회원 - 영화별 상영내역조회 기능 및 상영정보 선택 기능](https://github.com/arajo-hub/Java-Console-Project/blob/main/src/com/test/nonmember/ChooseMovie.java)

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/영화별예매/상영번호%20선택.png)

5. [현재 상영중인 영화 정보 상세 조회 기능 구현](https://github.com/arajo-hub/Java-Console-Project/blob/main/src/com/test/start/ViewMovieDetail.java)

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/영화정보상세보기.png)

6. [매점 조회 기능](https://github.com/arajo-hub/Java-Console-Project/blob/main/src/com/test/snack/BuySnack.java)

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/스낵/스낵메뉴%20조회.png)

7. [고객 공지사항 목록 조회 기능](https://github.com/arajo-hub/Java-Console-Project/blob/main/src/com/test/notice/showNoticeList.java)

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/공지사항/공지사항목록.png)

8. (고객 공지사항 상세 조회 기능 )[https://github.com/arajo-hub/Java-Console-Project/blob/main/src/data/NoticeBoard.java]

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/공지사항/공지사항%20상세(고객).png)

9. (관리자 공지사항 목록 조회 기능)[https://github.com/arajo-hub/Java-Console-Project/blob/main/src/com/test/notice/showNoticeListAdmin.java]

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/공지사항/공지사항목록.png)

10. (관리자 공지사항 상세 조회 기능)[https://github.com/arajo-hub/Java-Console-Project/blob/main/src/data/NoticeBoard.java]

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/공지사항/공지사항%20상세(관리자).png)

11. (관리자 공지사항 등록 기능)[https://github.com/arajo-hub/Java-Console-Project/blob/main/src/data/Notice.java]

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/공지사항/공지사항%20등록.png)

12. (관리자 공지사항 수정 기능)[https://github.com/arajo-hub/Java-Console-Project/blob/main/src/data/NoticeBoard.java]

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/공지사항/공지사항%20수정.png)

13. (관리자 공지사항 삭제 기능)[https://github.com/arajo-hub/Java-Console-Project/blob/main/src/data/NoticeBoard.java]

![](https://github.com/arajo-hub/Java-Console-Project/blob/main/document/%5B08%5D모든%20화면%20스크린샷/공지사항/공지사항%20삭제.png)

### 소감

 뜻하지 않게 조장을 맡아 평소처럼 조원으로 참여했을 때와는 많이 다른 입장을 느낄 수 있었습니다. 프로젝트를 진행함에 있어 조금 더 책임감을 느끼게 되었고, 자바와 오라클을 JDBC로 연결하는 이번 프로젝트를 진행하며 쿼리를 잘 짜는 것의 중요성을 느꼈습니다. 그리고 데이터모델링을 진행할 때, ERD 작업시 적절한 정규화가 필요함을 느꼈습니다.
