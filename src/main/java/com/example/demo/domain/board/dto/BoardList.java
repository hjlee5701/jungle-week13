package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class BoardList {

    private final Long board_id;

    private final String title;

    private final String writer;

    private final String content;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;


    public BoardList (Board board){

        this.board_id =  board.getBoardId();
        this.title = board.getTitle();
        this.writer =  board.getWriter();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();

    }
}
