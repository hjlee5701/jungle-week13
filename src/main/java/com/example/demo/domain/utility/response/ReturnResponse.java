package com.example.demo.domain.utility.response;

import org.springframework.http.HttpStatus;

public enum ReturnResponse {
    USER_SIGNUP("회원가입에 성공했습니다", HttpStatus.OK),
    ADMIN_SIGNUP("(관리자) 회원가입에 성공했습니다", HttpStatus.OK),
    ;


    private final String message;
    private final HttpStatus status;

    ReturnResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
