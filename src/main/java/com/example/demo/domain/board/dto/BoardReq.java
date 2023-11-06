package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class BoardReq {

    @NotBlank(message = "제목을 입력해주세요.")
    private final String title;


    @NotBlank(message = "내용을 작성해주세요.")
    private final String content;

    public Board toBoard(Member member){
        return new Board( title, content, member);
    }

}
