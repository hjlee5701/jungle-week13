package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.dto.ReplyReq;
import com.example.demo.domain.board.dto.ReplyRes;
import com.example.demo.domain.board.service.ReplyService;
import com.example.demo.domain.utility.jwt.JwtUtil;
import com.example.demo.domain.utility.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final JwtUtil jwt;
    
    @PostMapping
    public ReplyRes registerReply(@RequestBody @Valid ReplyReq registerReq, @RequestHeader(value="Authorization") String bearerToken){
        // 유효 토큰인지 검사 후 회원 아이디 반환
        String username = jwt.getUsernameFromToken(bearerToken);

        return replyService.registerReply(username, registerReq);
    }

    @PutMapping("/{replyId}")
    public ReplyRes updateReply(@RequestBody @Valid ReplyReq registerReq,
                             @PathVariable("replyId") Long replyId,
                                @RequestHeader(value="Authorization") String bearerToken)  {

        String username = jwt.getUsernameFromToken(bearerToken);
        return replyService.updateBoard(username, replyId, registerReq);
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity deleteReply(@PathVariable Long replyId, @RequestHeader(value="Authorization") String bearerToken) {

        String username = jwt.getUsernameFromToken(bearerToken);

        SuccessResponse res =  replyService.deleteReply(username, replyId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
