-- 공고회사 더미데이터
-- 컬럼 : 공고회사번호, 회사명, 대표자명, 설립일, 사원수, 연락처(하이픈포함), 주소, 매출액, 업종번호(FK)

Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '오케이아이에스', '서길동', to_date('1975-02-01', 'yyyy-mm-dd'), 216, '02-4323-5323', '서울특별시 마포구 양화로 188, 8층(동교동, 애경타워)', 42400000000, 1);
Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '(주)한우리소프트', '황동성', to_date('2001-01-31', 'yyyy-mm-dd'), 34, '02-775-0021', '서울시 서초구 서초중앙로 8길 117, 지어소프트 빌딩', 9200000000, 2);
Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '(주)케오에스티엠', '김수영', to_date('2000-07-15', 'yyyy-mm-dd'), 126, '031-643-3277', '경기도 수원시 권선구 곡반정로 24(곡반정동 393-2)', 8346300000, 2);
Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '현성엔지니어링(주)', '이현성', to_date('1998-04-23', 'yyyy-mm-dd'), 453, '031-854-1733', '경기도 광주시 오포읍 오포로 240', 62300000000, 3);
Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '제이테크놀러지(주)', '양준수', to_date('2002-08-12', 'yyyy-mm-dd'), 233, '031-854-1733', '서울특별시 강서구 마곡동로 110(마곡동) 코오롱원앤온리타워 3층', 530000000, 2);
Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '유에스인포', '유병협', to_date('1999-02-04', 'yyyy-mm-dd'), 141, '02-984-9021', '서울특별시 강서구 마곡중앙1로 20 마곡엠시그니처 603호', 16300000000, 2);
Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '(주)티엠시스템', '한태민', to_date('2008-09-09', 'yyyy-mm-dd'), 59, '031-123-6432', '경기 하남시 미사대로550, A동 1060-1062호', 8600000000, 2);
Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '(주)이노컨설팅', '이윤주', to_date('2010-08-21', 'yyyy-mm-dd'), 43, '02-9543-2584', '서울시 영등포구 양평로22길 21 선유도 코오롱디지털타워 1204호', 11832000000, 2);
Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '에어큐브', '윤상현', to_date('2011-02-20', 'yyyy-mm-dd'), 136, '02-2632-0315', '서울시 강서구 양천로 401 강서한강자이타워 B동 606호', 22657000000, 2);
Insert into tblCompany(seq, name, ceo, establishDate, employeeCount, tel, address, sales, industrySeq)
    values (seqTblCompany.nextVal, '(주)그린팩토리', '장윤이', to_date('2012-08-07', 'yyyy-mm-dd'), 28, '02-371-9913', '서울시 강남구 언주로 134길 4, 5층 (논현동, IB타워)', 22000000000, 2);