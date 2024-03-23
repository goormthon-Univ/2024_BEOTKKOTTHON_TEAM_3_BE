package com.univ.haksamo.domain.keyword.repository;

import com.univ.haksamo.domain.keyword.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword,Long> {
    Keyword findByName(String input);
}
