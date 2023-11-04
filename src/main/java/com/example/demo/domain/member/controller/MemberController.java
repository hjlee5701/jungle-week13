package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.MemberSignInRequest;
import com.example.demo.domain.member.dto.MemberSignUpRequest;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.domain.utility.response.ReturnResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private ReturnResponse res;

    // 회원가입
    @PostMapping(value = "/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid MemberSignUpRequest signupReq){
        log.info(signupReq.getAdminCode());
        ReturnResponse res =
                memberService.signUp( signupReq );
        return new ResponseEntity<>(res.getMessage(), res.getStatus());
    }

    // 로그인
    @PostMapping(value = "/signin")
    public ResponseEntity<String> signIn(@RequestBody @Valid MemberSignInRequest signInReq, HttpServletResponse response) {
        log.info(signInReq.getUsername());
        ReturnResponse res = memberService.signIn(signInReq, response);
        return new ResponseEntity<>(res.getMessage(), res.getStatus());
    }

}
