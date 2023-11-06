package com.example.demo.domain.utility.response;

public enum responseEnum {
    USER_SIGNUP("회원가입에 성공했습니다"),
    ADMIN_SIGNUP("(관리자) 회원가입에 성공했습니다"),
    SIGNIN_SUCCESS("로그인 성공했습니다."),
    REPLY_DELETE("댓글이 삭제되었습니다."),
    BOARD_DELETE("게시글이 삭제되었습니다."),
    ;

    private final String message;

    responseEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
