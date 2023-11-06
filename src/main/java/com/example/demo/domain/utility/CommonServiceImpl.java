package com.example.demo.domain.utility;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.UserRoleEnum;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.utility.exception.errorCode.BoardErrorCode;
import com.example.demo.domain.utility.exception.errorCode.MemberErrorCode;
import com.example.demo.domain.utility.exception.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{


    private final MemberRepository memberRepository;

    @Override
    public Member getMemberByUsername(String username ) {
        Optional<Member> isMember = memberRepository.findByUsername(username);
        if(isMember.isEmpty()){
            throw new RestApiException(MemberErrorCode.USER_NOT_FOUND);
        }
        return isMember.get();
    }

    @Override
    public void checkWriterOrAdmin(Member writer, Member reqMember) {
        // 관리자 여부 확인
        if(UserRoleEnum.USER == reqMember.getRole()){
            // 작성자와 요청한 회원이 같은지 확인
            if(writer != reqMember){
                throw new RestApiException(BoardErrorCode.WRITER_NOT_FOUND);
            }
        }
    }



}
