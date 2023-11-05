package com.example.demo.domain.board.dto;

import lombok.Getter;

@Getter
public class ReplyRes {
    private final Long replyId;

    private final String content;


    public ReplyRes(Long replyId, String content) {
        this.replyId = replyId;
        this.content = content;
    }
}
