package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.MemberSignInRequest;
import com.example.demo.domain.member.dto.MemberSignUpRequest;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.domain.utility.response.SuccessResponse;
import com.example.demo.domain.utility.response.responseEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    private responseEnum res;

    // 회원가입
    @PostMapping(value = "/signup")
    public ResponseEntity signUp(@RequestBody @Valid MemberSignUpRequest signupReq){
        log.info(signupReq.getAdminCode());
        SuccessResponse res =
                memberService.signUp( signupReq );
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    // 로그인
    @PostMapping(value = "/signin")
    public ResponseEntity signIn(@RequestBody @Valid MemberSignInRequest signInReq, HttpServletResponse response){
        log.info(signInReq.getUsername());
        SuccessResponse res = memberService.signIn(signInReq, response);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
