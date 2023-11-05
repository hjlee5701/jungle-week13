package com.example.demo.domain.board.service;

import com.example.demo.domain.board.dto.BoardRes;
import com.example.demo.domain.board.dto.BoardAndReplyRes;
import com.example.demo.domain.board.dto.BoardReq;
import com.example.demo.domain.board.dto.ReplyRes;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.Reply;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.board.repository.ReplyRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.utility.CommonService;
import com.example.demo.domain.utility.exception.BoardEx;
import com.example.demo.domain.utility.response.ReturnResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.domain.utility.response.ReturnResponse.BOARD_DELETE;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final CommonService common;

    public Board getBoardById(Long boardId){
        Optional<Board> isBoard = boardRepository.findById(boardId);
        if(isBoard.isEmpty()){
            throw BoardEx.boardNotFound();
        }
        return isBoard.get();
    }

    private List<ReplyRes> getRepliesFromBoardId(Long boardId) {
        // 작성 날짜 기준 내림차순
        List<Reply> replies =
                replyRepository.findByBoardId( boardId );

        List<ReplyRes> res = new ArrayList<>();

        for (Reply reply : replies){
            res.add(new ReplyRes(reply.getReplyId(), reply.getContent()));
        }
        return res;
    }
    @Override
    @Transactional
    public BoardRes registerBoard(String username, BoardReq registerReq){
        // 회원 여부 확인
        Member reqMember = common.getMemberByUsername(username);
        
        // 게시글 저장
        Board board = registerReq.toBoard(reqMember);
        boardRepository.save(board);

        log.info("게시글 \" "+registerReq.getTitle()+" \" 이(가) 등록되었습니다.");
        return new BoardRes(board);
    }


    @Override
    @Transactional(readOnly = true)
//    TODO : paging 처리 page index = 0부터
    public List<BoardAndReplyRes> getBoardList(){
        List<Board> boards =
                boardRepository.findAllByOrderByCreatedAtDesc();

        List<BoardAndReplyRes> boardsRes = new ArrayList<>();
        for(Board board : boards){

            List<ReplyRes> replies =
                    getRepliesFromBoardId(board.getBoardId());
            boardsRes.add(
                    new BoardAndReplyRes(board, replies));
        }
        return boardsRes;
    }



    @Override
    @Transactional(readOnly = true)
    public BoardAndReplyRes getBoardDetail(Long boardId){
        Board board = getBoardById(boardId);

        List<ReplyRes> replies =
                getRepliesFromBoardId(board.getBoardId());

        return new BoardAndReplyRes(board, replies );
    }

    @Override
    @Transactional
    public BoardRes updateBoard(String username, Long boardId, BoardReq updateReq) {
        Member reqMember = common.getMemberByUsername(username);

        Board board = getBoardById(boardId);

        common.checkWriterOrAdmin(reqMember, board.getMember());
        board.updateBoard( updateReq.getTitle(),
                           updateReq.getContent());

        boardRepository.save(board);
        return new BoardRes(board);
    }

    @Override
    @Transactional
    public ReturnResponse deleteBoard(String username, Long boardId) {
        Member reqMember = common.getMemberByUsername(username);

        Board board = getBoardById(boardId);

        common.checkWriterOrAdmin(reqMember, board.getMember());

        boardRepository.deleteById(boardId);
        return BOARD_DELETE;
    }


}
