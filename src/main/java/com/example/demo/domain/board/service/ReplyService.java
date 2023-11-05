package com.example.demo.domain.board.service;

import com.example.demo.domain.board.dto.ReplyReq;
import com.example.demo.domain.board.dto.ReplyRes;
import com.example.demo.domain.utility.response.ReturnResponse;

public interface ReplyService {
    ReplyRes registerReply(String member, ReplyReq registerReq);

    ReplyRes updateBoard(String member, Long replyId, ReplyReq updateReq);

    ReturnResponse deleteReply(String member, Long replyId);
}
