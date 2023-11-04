package com.example.demo.domain.member.service;

import com.example.demo.domain.utility.exception.MemberEx;
import com.example.demo.domain.member.dto.MemberSignUpRequest;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.UserRoleEnum;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.utility.response.ReturnResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private static final String ADMIN_CODE = "admin";
    private final MemberRepository memberRepository;

    private UserRoleEnum determineUserRole(boolean isAdminCheck, String adminCode) {
        if (isAdminCheck) {
            if (ADMIN_CODE.equals(adminCode)) {
                return UserRoleEnum.ADMIN;
            } else {
                throw MemberEx.invalidAdminCode();
            }
        }
        return UserRoleEnum.USER;
    }

    @Transactional
    @Override
    public ReturnResponse signUp(MemberSignUpRequest signUpReq) {
        // username 중복 체크
        Optional<Member> isMember =
                memberRepository.findByUsername(signUpReq.getUsername());
        if( isMember.isPresent() ){
            throw MemberEx.duplicateUsername();
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

        return role == UserRoleEnum.ADMIN ? ReturnResponse.ADMIN_SIGNUP :ReturnResponse.USER_SIGNUP;
    }

}
