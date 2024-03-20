package com.univ.haksamo.domain.board.repository;

import com.univ.haksamo.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByGroupId(Long groupId);
}
