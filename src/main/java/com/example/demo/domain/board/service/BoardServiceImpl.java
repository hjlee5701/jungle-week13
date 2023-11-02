package com.example.demo.domain.board.service;

import com.example.demo.domain.board.dto.BoardList;
import com.example.demo.domain.board.dto.BoardRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    private Board getBoardById(Long boardId){
        Optional<Board> board = boardRepository.findById(boardId);
        return board.orElse(null);
    }
    @Override
    public Boolean registerBoard(BoardRequest registerReq){

        Board board = registerReq.toBoard();
        boardRepository.save(board);
        return true;
    }

    @Override
    public List<BoardList> getBoardList(){

        List<Board> boards = boardRepository.findAll();
        List<BoardList> boardList = new ArrayList<>();
        for(Board board : boards){

            boardList.add(
                    new BoardList(board));
        }
        return boardList;
    }

    @Override
    public Board getBoardDetail(Long boardId){
        return getBoardById(boardId);
    }

    @Override
    public void updateBoard(Long boardId, BoardRequest updateReq) {
        Board board = getBoardById(boardId);

        board.updateBoard( updateReq.getTitle(),
                           updateReq.getContent());

        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }


}
