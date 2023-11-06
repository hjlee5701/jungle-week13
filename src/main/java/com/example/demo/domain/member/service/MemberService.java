package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.MemberSignInRequest;
import com.example.demo.domain.member.dto.MemberSignUpRequest;
import com.example.demo.domain.utility.response.SuccessResponse;

import javax.servlet.http.HttpServletResponse;

public interface MemberService {
    SuccessResponse signUp(MemberSignUpRequest signUpReq);

    SuccessResponse signIn(MemberSignInRequest signUpReq, HttpServletResponse response) ;

}
