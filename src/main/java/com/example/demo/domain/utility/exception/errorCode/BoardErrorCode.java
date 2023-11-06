package com.example.demo.domain.utility.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {

    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    WRITER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "작성자가 아닙니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
