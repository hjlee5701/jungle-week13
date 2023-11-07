package com.example.demo.domain.board.service;

import com.example.demo.domain.board.dto.ReplyReq;
import com.example.demo.domain.board.dto.ReplyRes;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.entity.Reply;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.board.repository.ReplyRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.utility.CommonService;
import com.example.demo.domain.utility.exception.exception.RestApiException;
import com.example.demo.domain.utility.response.SuccessResponse;
import com.example.demo.domain.utility.response.responseEnum;
import com.example.demo.domain.utility.exception.errorCode.BoardErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final CommonService common;

    private Board getBoardByBoardId(Long boardId){
        Optional<Board> isBoard = boardRepository.findById(boardId);
        if(isBoard.isEmpty()){
            throw new RestApiException(BoardErrorCode.BOARD_NOT_FOUND);
        }
        return isBoard.get();
    }

    @Override
    public ReplyRes registerReply(String username, ReplyReq registerReq){

        // 회원 정보 확인
        Member reqMember = common.getMemberByUsername(username);

        // 게시글의 db 저장 유무 확인
        Board board = getBoardByBoardId(registerReq.getBoardId());

        // 댓글 저장
        Reply reply = new Reply(registerReq.getContent(), board, reqMember);
        replyRepository.save(reply);
        return new ReplyRes(reply.getReplyId(), reply.getContent());
    }




    @Override
    public ReplyRes updateBoard(String username, Long replyId, ReplyReq updateReq) {

        // 회원 정보 가져오기
        Member reqMember = common.getMemberByUsername(username);

        // 게시글의 db 저장 유무 확인
        Board board = getBoardByBoardId(updateReq.getBoardId());
        
        // 댓글 찾기
        Optional<Reply> isReply = replyRepository.findById(replyId);
        if(isReply.isEmpty()){
            throw new RestApiException(BoardErrorCode.REPLY_NOT_FOUND);
        }
        Reply reply = isReply.get();
        log.info(reply.getMember().getUsername());

        // 댓글 작성자 확인
        common.checkWriterOrAdmin(reply.getMember(), reqMember);

        // 댓글 수정
        reply.updateReply(updateReq.getContent());

        // 수정한 댓글 저장
        replyRepository.save(reply);

        board.getReplies().add(reply);
        boardRepository.save(board);

        return new ReplyRes(reply.getReplyId(), reply.getContent());
    }

    @Override
    public SuccessResponse deleteReply(String username, Long replyId) {

        // 회원 정보 가져오기
        Member reqMember = common.getMemberByUsername(username);

        // 댓글 존재 여부
        Optional<Reply> isReply =
                replyRepository.findById(replyId);
        if(isReply.isEmpty()){
            throw new RestApiException(BoardErrorCode.REPLY_NOT_FOUND);
        }

        // 댓글 작성자와 로그인한 회원이 같은지 확인
        common.checkWriterOrAdmin(isReply.get().getMember(), reqMember);

        // 삭제 성공
        replyRepository.deleteById(replyId);
        return new SuccessResponse(responseEnum.REPLY_DELETE);
    }


}
