package com.example.demo.domain.utility.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode  {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "중복된 username 입니다."),
    INVALID_ADMIN_CHECK(HttpStatus.NOT_FOUND, "관리자 확인을 선택해주세요."),
    INVALID_ADMIN_CODE(HttpStatus.NOT_FOUND, "관리자 코드가 불일치합니다."),
    INVALID_TOKEN(HttpStatus.INTERNAL_SERVER_ERROR, "토큰이 유효하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;


}
