package com.univ.haksamo.domain.bookmark.repository;

import com.univ.haksamo.domain.bookmark.entity.UserBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBoardRepository extends JpaRepository<UserBoard,Long> {
    List<UserBoard> findAllByUserId(Long userId);

    UserBoard getUserBoardByBoardIdAndUserId(Long boardId, Long userId);
}
