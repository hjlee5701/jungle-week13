package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.dto.BoardRes;
import com.example.demo.domain.board.dto.BoardAndReplyRes;
import com.example.demo.domain.board.dto.BoardReq;
import com.example.demo.domain.board.service.BoardService;

import com.example.demo.domain.utility.jwt.JwtUtil;
import com.example.demo.domain.utility.response.ReturnResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {


    private final BoardService boardService;
    private final JwtUtil jwt;
    private ReturnResponse res;


    @PostMapping
    public BoardRes registerBoard(@RequestBody @Valid BoardReq registerReq, HttpServletRequest request){
//RequestHeader
        String username = jwt.getUsernameFromRequest(request);
        return boardService.registerBoard(username, registerReq);

    }

    @GetMapping
    public List<BoardAndReplyRes> getBoardList(){

        return boardService.getBoardList();
    }

    @GetMapping("/{boardId}")
    public BoardAndReplyRes getBoardDetail(@PathVariable("boardId") Long boardId){

        return boardService.getBoardDetail(boardId);
    }

    @PutMapping("/{boardId}")
    public BoardRes updateBoard( @PathVariable("boardId") Long boardId,
                             @RequestBody @Valid BoardReq updateReq, HttpServletRequest request){
        String username = jwt.getUsernameFromRequest(request);
        return boardService.updateBoard(username, boardId, updateReq);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardId") Long boardId, HttpServletRequest request){
        String username = jwt.getUsernameFromRequest(request);
        ReturnResponse res =
                boardService.deleteBoard(username, boardId);
        return new ResponseEntity<>(res.getMessage(), res.getStatus());
    }


}
