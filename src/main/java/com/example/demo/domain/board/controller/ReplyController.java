package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.dto.ReplyReq;
import com.example.demo.domain.board.dto.ReplyRes;
import com.example.demo.domain.board.service.ReplyService;
import com.example.demo.domain.utility.jwt.JwtUtil;
import com.example.demo.domain.utility.response.ReturnResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final JwtUtil jwt;
    
    @PostMapping
    public ReplyRes registerReply(@RequestBody @Valid ReplyReq registerReq, HttpServletRequest request){
        // 유효 토큰인지 검사 후 회원 아이디 반환
        String username = jwt.getUsernameFromRequest(request);

        return replyService.registerReply(username, registerReq);
    }

    @PutMapping("/{replyId}")
    public ReplyRes updateReply(@RequestBody @Valid ReplyReq registerReq,
                             @PathVariable("replyId") Long replyId,
                             HttpServletRequest request){

        String username = jwt.getUsernameFromRequest(request);
        return replyService.updateBoard(username, replyId, registerReq);
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity<String> deleteReply(@PathVariable Long replyId,HttpServletRequest request){

        String username = jwt.getUsernameFromRequest(request);

        ReturnResponse res =  replyService.deleteReply(username, replyId);
        return new ResponseEntity<>(res.getMessage(), res.getStatus());
    }






}
