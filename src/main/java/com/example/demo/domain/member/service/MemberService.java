package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.MemberSignUpRequest;
import com.example.demo.domain.utility.response.ReturnResponse;

public interface MemberService {
    ReturnResponse signUp(MemberSignUpRequest signUpReq);

}
