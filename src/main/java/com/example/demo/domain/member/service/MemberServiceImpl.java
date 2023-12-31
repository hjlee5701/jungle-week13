package com.example.demo.domain.member.service;

import com.example.demo.domain.member.dto.MemberSignInRequest;
import com.example.demo.domain.member.dto.MemberSignUpRequest;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.UserRoleEnum;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.utility.exception.errorCode.MemberErrorCode;
import com.example.demo.domain.utility.exception.exception.RestApiException;
import com.example.demo.domain.utility.jwt.JwtUtil;
import com.example.demo.domain.utility.response.SuccessResponse;
import com.example.demo.domain.utility.response.responseEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private static final String ADMIN_CODE = "admin";
    private final MemberRepository memberRepository;
    private final JwtUtil jwt;


    private Member getMemberByUsername(String username){
        Optional<Member> isMember = memberRepository.findByUsername(username);
        if (isMember.isEmpty()) {
            throw new RestApiException(MemberErrorCode.USER_NOT_FOUND);
        }
        return isMember.get();
    }

    private UserRoleEnum determineUserRole(boolean isAdminCheck, String adminCode) {
        if (isAdminCheck) {
            if (ADMIN_CODE.equals(adminCode)) {
                return UserRoleEnum.ADMIN;
            } else {
                throw new RestApiException(MemberErrorCode.INVALID_ADMIN_CODE);
            }
        // adminCheck = false, adminCode != null
        } else if (adminCode != null) {
            throw new RestApiException(MemberErrorCode.INVALID_ADMIN_CHECK);
        }
        return UserRoleEnum.USER;
    }

    @Transactional
    @Override
    public SuccessResponse signUp(MemberSignUpRequest signUpReq){
        // username 중복 체크
        Optional<Member> isMember =
                memberRepository.findByUsername(signUpReq.getUsername());
        if( isMember.isPresent() ){
            throw new RestApiException(MemberErrorCode.DUPLICATE_USERNAME);
        }
        // 암호화 작업
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secretPw = encoder.encode(signUpReq.getPassword());

        // 관리자/유저 확인
        UserRoleEnum role = determineUserRole( signUpReq.isAdminCheck(),
                                               signUpReq.getAdminCode());
        // 유저 정보 저장
        Member member =
                new Member(signUpReq.getUsername(),secretPw, role);
        memberRepository.save(member);

        return role == UserRoleEnum.ADMIN ? new SuccessResponse(responseEnum.ADMIN_SIGNUP) : new SuccessResponse(responseEnum.USER_SIGNUP);
    }


    @Transactional
    @Override
    public SuccessResponse signIn(MemberSignInRequest signUpReq, HttpServletResponse response) {
        // Spring Security 는 Request body 에서 사용자의 비밀번호를 추출해, 자동으로 비밀번호를 검사/처리 한다.

        // username 으로 회원 찾기
        Member member = getMemberByUsername(signUpReq.getUsername());
        // 비밀번호 확인
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if( ! encoder.matches(signUpReq.getPassword(), member.getPassword())) {
            throw new RestApiException(MemberErrorCode.USER_NOT_FOUND);
        }

        // HttpServletResponse 에 token 담기
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,
                          jwt.createToken(member.getUsername(),member.getRole()));
        return new SuccessResponse(responseEnum.SIGNIN_SUCCESS);
    }

}
