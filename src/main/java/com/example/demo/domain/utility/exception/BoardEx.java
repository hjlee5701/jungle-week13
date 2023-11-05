package com.example.demo.domain.utility.exception;

public class BoardEx extends RuntimeException{


    public BoardEx(String message) {
        super(message);
    }

    public static BoardEx boardNotFound(){
         return new BoardEx("게시글을 찾을 수 없습니다.");
    }

    public static BoardEx replyNotFound(){
        return new BoardEx("댓글을 찾을 수 없습니다.");
    }


    public static BoardEx notWriter() {
        return new BoardEx("작성자가 아닙니다.");
    }
}
