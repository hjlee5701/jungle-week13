package com.example.demo.domain.board.service;

import com.example.demo.domain.board.dto.BoardRes;
import com.example.demo.domain.board.dto.BoardAndReplyRes;
import com.example.demo.domain.board.dto.BoardReq;
import com.example.demo.domain.utility.response.SuccessResponse;

import java.util.List;

public interface BoardService {

    // 게시판 글 등록
    BoardRes registerBoard(String username, BoardReq registerReq);

    // 모든 게시판들의 리스트 불러오기
    List<BoardAndReplyRes> getBoardList();
    
    // 게시글 상세 정보 반환
    BoardAndReplyRes getBoardDetail(Long boardId);

    // 게시글 수정
    BoardRes updateBoard(String username, Long boardId, BoardReq updateReq);

    // 게시글 삭제
    SuccessResponse deleteBoard(String username, Long boardId);
}
