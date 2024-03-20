package com.univ.haksamo.domain.group.repository;

import com.univ.haksamo.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupJpaRepository extends JpaRepository<Group,Long> {
}
