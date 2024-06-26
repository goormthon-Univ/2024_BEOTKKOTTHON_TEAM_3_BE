package com.univ.haksamo.domain.board.repository;

import com.univ.haksamo.domain.board.entity.Board;

import java.util.List;

public interface BoardCustomRepository {
    List<Board> findAllByGroupId(Long lastBookId, Long groupId, int pageSize);
    List<Board> findAllByTitleInput(String input, int pageSize);
    List<Board> findAllByContentInput(String input, int pageSize);
    List<Board> findAllByKeywordInput(Long input, int pageSize);

}
