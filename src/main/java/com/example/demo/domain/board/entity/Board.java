package com.example.demo.domain.board.entity;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.utility.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();


    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.replies = new ArrayList<>();
    }

    public void updateBoard(String title, String content){
        this.title = title;
        this.content = content;

    }

}
