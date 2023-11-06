package com.example.demo.domain.utility.exception.exception;

import com.example.demo.domain.utility.exception.errorCode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{

    /**
     * 발생한 예외를 처리해줄 예외 클래스
     * 언체크 예외 상속 (불필요한 throw 전파를 막기 위해)
     */
    private final ErrorCode errorCode;


}
