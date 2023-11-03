package com.example.demo.domain.utility.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/*
 * @ControllerAdvice
 * 예외 처리 관련 로직을 담고 있는 클래스에 사용
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Valid 어노테이션 예외 Handler
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){

        /*
         * MethodArgumentNotValidException 예외가 발생하면
         * Spring Framework 가 자동으로 동작
         */

        BindingResult result = ex.getBindingResult();

        Map<String, String> errors = new HashMap<>();

        /*
         * 예시)
            "title": "제목을 입력해주세요."
         */
        result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(),
                   fieldError.getDefaultMessage()));

        return errors;
    }
}
