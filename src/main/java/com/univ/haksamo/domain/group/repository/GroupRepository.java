package com.univ.haksamo.domain.group.repository;

import com.univ.haksamo.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
}
