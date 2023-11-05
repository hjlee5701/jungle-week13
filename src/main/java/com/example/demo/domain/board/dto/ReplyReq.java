package com.example.demo.domain.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyReq {

    private final Long boardId;

    private final String content;

}
