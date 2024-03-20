package com.univ.haksamo.domain.board.repository;

import com.univ.haksamo.domain.board.entity.Board;

import java.util.List;

public interface BoardCustomRepository {
    List<Board> findAllByGroupId(Long lastBookId, Long groupId, int pageSize);

}
