/*

    로그인 쿼리

*/

-- 교육생 로그인

create or replace procedure procIsValid
(
    ptype varchar2,
    pseq tblStudent.seq%type,
    pidNum tblstudent.idnum%type,
    presult out number
)
is
begin
    if ptype='1' then -- 학생일 경우
        select
            case
                when idNum=pidNum then 0
                else 1
            end
        into presult
        from tblStudent
        where seq=pseq;
    
    elsif ptype='2' then -- 교사일 경우
        select
            case
                when idNum=pidNum then 0
                else 1
            end
        into presult
        from tblTeacher
        where seq=pseq;
        
    else -- 관리자일 경우(세 가지 경우 외는 없음)
        select
            case
                when idNum=pidNum then 0
                else 1
            end
        into presult
        from tblAdmin
        where seq=pseq;
    end if;
exception -- 아이디가 존재하지 않을 경우
    when no_data_found then
        presult:=2;
end;


/*

-- procIsValid 테스트

declare
    vresult number;
begin
    procStudentisValid(100, 2871111, vresult);
    if vresult=1 then
        dbms_output.put_line('로그인에 성공했습니다.');
    elsif vresult=2 then
        dbms_output.put_line('존재하지 않는 교육생번호입니다.');
    else
        dbms_output.put_line('비밀번호가 틀렸습니다.');
    end if;
end;

*/