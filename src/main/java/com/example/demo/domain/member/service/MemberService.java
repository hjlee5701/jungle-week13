package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.MemberSignInRequest;
import com.example.demo.domain.member.dto.MemberSignUpRequest;
import com.example.demo.domain.utility.response.ReturnResponse;

import javax.servlet.http.HttpServletResponse;

public interface MemberService {
    ReturnResponse signUp(MemberSignUpRequest signUpReq);

    ReturnResponse signIn(MemberSignInRequest signUpReq, HttpServletResponse response);

}
