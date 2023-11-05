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
        return new MemberEx("회원을 찾을 수 없습니다.");
    }

    public static MemberEx duplicateUsername(){
        return new MemberEx("중복된 username 입니다.");
    }


    public static MemberEx invalidAdminCheck(){
        return new MemberEx("관리자 확인을 선택해주세요.");
    }
    public static MemberEx invalidAdminCode(){
        return new MemberEx("관리자 코드가 불일치합니다.");
    }

}
