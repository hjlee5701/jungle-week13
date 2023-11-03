package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class BoardRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    @Length(min = 1, max = 10, message = "1~10자로 입력해주세요.")
    private final String title;

    @NotBlank(message = "작성자를 입력해주세요.")
    @Length(min = 3, max = 10, message = "3~10자로 입력해주세요.")
    private final String writer;

    @NotBlank(message = "내용을 작성해주세요.")
    private final String content;

    public Board toBoard(){
        return new Board( title, writer, content);
    }

}
