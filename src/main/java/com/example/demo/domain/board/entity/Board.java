package com.example.demo.domain.board.entity;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.utility.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Member member;


    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void updateBoard(String title, String content){
        this.title = title;
        this.content = content;

    }

}
