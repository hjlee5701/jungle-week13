package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.dto.BoardList;
import com.example.demo.domain.board.dto.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping(value = "/register")
    public void registerBoard(@RequestBody BoardRequest registerReq){
        Boolean isRegister = boardService.registerBoard(registerReq);
        if (isRegister)
            log.info("등록되었습니다.");
        else
            log.info("등록 실패..");
    }

    @GetMapping("/list")
    public List<BoardList> getBoardList(){

        return boardService.getBoardList();
    }

    @GetMapping("/detail/{boardId}")
    public Board getBoardDetail(@PathVariable("boardId") Long boardId){

        return boardService.getBoardDetail(boardId);
    }

    @PutMapping("/update/{boardId}")
    public void updateBoard( @PathVariable("boardId") Long boardId,
                             @RequestBody BoardRequest updateReq ){
        boardService.updateBoard(boardId, updateReq);
    }

    @DeleteMapping("/delete/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.deleteBoard(boardId);
    }


}
