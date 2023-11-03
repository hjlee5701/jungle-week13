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
@RequestMapping(value = "/board", method = {RequestMethod.GET, RequestMethod.POST})
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public Board registerBoard(@RequestBody BoardRequest registerReq) {

        return boardService.registerBoard(registerReq);

    }

    @GetMapping("/list")
    public List<BoardList> getBoardList(){

        return boardService.getBoardList();
    }

    @GetMapping("/{boardId}")
    public Board getBoardDetail(@PathVariable("boardId") Long boardId){

        return boardService.getBoardDetail(boardId);
    }

    @PutMapping("/{boardId}")
    public void updateBoard( @PathVariable("boardId") Long boardId,
                             @RequestBody BoardRequest updateReq ){
        boardService.updateBoard(boardId, updateReq);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.deleteBoard(boardId);
    }


}
