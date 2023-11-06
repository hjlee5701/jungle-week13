package com.example.demo.domain.utility.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SuccessResponse {
    private final String message;

    public SuccessResponse(responseEnum responseEnum) {
        this.message = responseEnum.getMessage();
    }
}
