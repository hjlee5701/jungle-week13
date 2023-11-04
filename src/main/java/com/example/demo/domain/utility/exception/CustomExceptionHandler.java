package com.example.demo.domain.utility.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    /*
     * Spring 은 예외를 미리 처리해둔 ResponseEntityExceptionHandler 를 추상 클래스로 제공한다.
     * 따라서 ControllerAdvice 클래스는 이를 상속받은 후 @Override 한다.
     */

    /**
     * 요청 들어온 아이디가 저장되어 있지 않을 때 발생하는 에러
     */
    @ExceptionHandler(MemberEx.class)
    public ResponseEntity<String> handleMemberNotFoundException(MemberEx ex) {
        // 원하는 에러 메시지와 HTTP 상태 코드를 클라이언트에게 반환
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    /**
     * Valid 어노테이션 예외 Handler
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        String errorMessage = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
