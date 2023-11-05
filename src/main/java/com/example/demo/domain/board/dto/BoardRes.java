package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardRes {

    private final Long board_id;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;




    public BoardRes(Board board){

        this.board_id =  board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();

    }
}
