package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.dto.BoardRes;
import com.example.demo.domain.board.dto.BoardAndReplyRes;
import com.example.demo.domain.board.dto.BoardReq;
import com.example.demo.domain.board.service.BoardService;

import com.example.demo.domain.utility.jwt.JwtUtil;
import com.example.demo.domain.utility.response.SuccessResponse;
import com.example.demo.domain.utility.response.responseEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {


    private final BoardService boardService;
    private final JwtUtil jwt;
    private responseEnum res;
    private SuccessResponse successResponse;


    @PostMapping
    public BoardRes registerBoard(@RequestBody @Valid BoardReq registerReq, @RequestHeader(value="Authorization") String bearerToken) {
//RequestHeader
        String username = jwt.getUsernameFromToken(bearerToken);
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
                             @RequestBody @Valid BoardReq updateReq, @RequestHeader(value="Authorization") String bearerToken) {
        String username = jwt.getUsernameFromToken(bearerToken);
        return boardService.updateBoard(username, boardId, updateReq);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable("boardId") Long boardId, @RequestHeader(value="Authorization")String bearerToken) {
        String username = jwt.getUsernameFromToken(bearerToken);
        SuccessResponse res =
                boardService.deleteBoard(username, boardId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


}
