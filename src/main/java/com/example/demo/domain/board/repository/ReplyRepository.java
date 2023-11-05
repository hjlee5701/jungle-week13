package com.example.demo.domain.board.repository;

import com.example.demo.domain.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r join fetch r.board b where b.boardId = :boardId")
    List<Reply> findByBoardId(Long boardId);

}
