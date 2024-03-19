package com.univ.haksamo.domain.bookmark.repository;

import com.univ.haksamo.domain.bookmark.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGroupRespository extends JpaRepository<UserGroup,Long> {
    @Query(value = "select UG from UserGroup as UG join fetch UG.group where UG.user.id = :userId")
    List<UserGroup> findByUserId(Long userId);
}
