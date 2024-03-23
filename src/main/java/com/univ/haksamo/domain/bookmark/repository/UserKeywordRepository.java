package com.univ.haksamo.domain.bookmark.repository;

import com.univ.haksamo.domain.bookmark.entity.UserKeword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserKeywordRepository extends JpaRepository<UserKeword, Long> {
    List<UserKeword> findAllByUserId(Long userId);

    List<UserKeword> findAllByKeywordId(Long keywordId);

    UserKeword findAllByUserIdAndKeywordId(Long userId,Long keywordId);

}
