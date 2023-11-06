package com.example.demo.domain.board.entity;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.utility.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Reply extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column
    private String content;

    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Board board;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Member member;


    public Reply(String content, Board board, Member member) {
        this.content = content;
        this.board = board;
        this.member = member;
    }

    public void updateReply(String content) {
        this.content = content;
    }

    //- 댓글은 작성 날짜 기준 내림차순으로 정렬하기

}
