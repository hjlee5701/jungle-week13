package com.example.demo.domain.utility.exception.errorCode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    /**
     * error 코드를 클래스별로 나누기 위해 ErrorCode 를 인터페이스로 구현 (CommonErrorCode 에 추상화)
     *
     */

    HttpStatus getHttpStatus();

    String getMessage();
}