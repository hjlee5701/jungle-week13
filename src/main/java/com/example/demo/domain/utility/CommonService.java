package com.example.demo.domain.utility;

import com.example.demo.domain.member.entity.Member;


public interface CommonService {

    /** 회원 아이디로 Member 엔티티 반환 */
    Member getMemberByUsername(String username );


    /** 작성자 혹은 관리자인자 확인 */
    void checkWriterOrAdmin(Member writer, Member reqMember);

}
