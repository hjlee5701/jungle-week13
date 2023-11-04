package com.example.demo.domain.utility.exception;

public class MemberEx extends RuntimeException{


    public MemberEx() {
        super("해당 사용자를 찾을 수 없습니다.");
    }

    public MemberEx(String message) {
        super(message);
    }

    public MemberEx(String message, Throwable cause) {
        super(message, cause);
    }


    public static MemberEx userNotFound() {
        return new MemberEx("등록되지 않은 회원입니다.");
    }

    public static MemberEx adminNotFound() {
        return new MemberEx("관리자를 찾을 수 없습니다.");
    }

    public static MemberEx invalidUserType() {
        return new MemberEx("유효하지 않은 사용자 유형입니다.");
    }

    public static MemberEx duplicateUsername(){
        return new MemberEx("중복된 아이디입니다.");
    }

    public static MemberEx invalidPassword(){
        return new MemberEx("비밀번호가 일치하지 않습니다.");
    }

    public static MemberEx invalidAdminCheck(){
        return new MemberEx("관리자 확인을 선택해주세요.");
    }
    public static MemberEx invalidAdminCode(){
        return new MemberEx("관리자 코드가 불일치합니다.");
    }

}
