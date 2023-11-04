package com.example.demo.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String username;

    // 인증 : 회원 비밀번호
    @JsonIgnore
    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Setter
    private UserRoleEnum role;

    public Member(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;

    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
