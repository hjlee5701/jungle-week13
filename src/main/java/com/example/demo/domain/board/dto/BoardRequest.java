package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.Board;
import lombok.Getter;
import java.beans.ConstructorProperties;

@Getter
public class BoardRequest {
    private final String title;

    private final String writer;

    private final String content;

    @ConstructorProperties({"title", "writer", "content"})
    public BoardRequest(String title, String writer, String content){
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Board toBoard(){
        return new Board( title, writer, content);
    }

}
