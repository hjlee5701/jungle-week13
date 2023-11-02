package com.example.demo.domain.board.service;

import com.example.demo.domain.board.dto.BoardList;
import com.example.demo.domain.board.dto.BoardRequest;
import com.example.demo.domain.board.entity.Board;

import java.util.List;

public interface BoardService {

    // 게시판 글 등록
    Boolean registerBoard(BoardRequest registerReq);

    // 모든 게시판들의 리스트 불러오기
    List<BoardList> getBoardList();
    
    // 게시글 상세 정보 반환
    Board getBoardDetail(Long boardId);

    // 게시글 수정
    void updateBoard(Long boardId, BoardRequest updateReq);

    // 게시글 삭제
    void deleteBoard(Long boardId);
}
