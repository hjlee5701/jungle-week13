package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardAndReplyRes {

    private final Long board_id;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    private final List<ReplyRes> replies;


    public BoardAndReplyRes(Board board, List<ReplyRes> replies){

        this.board_id =  board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.replies = replies;

    }

}
