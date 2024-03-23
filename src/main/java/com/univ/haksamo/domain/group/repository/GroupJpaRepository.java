package com.univ.haksamo.domain.group.repository;

import com.univ.haksamo.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupJpaRepository extends JpaRepository<Group,Long> {
    List<Group> findAllByUniversityId(Long universityId);
    Group findAllByName(String name);
}
