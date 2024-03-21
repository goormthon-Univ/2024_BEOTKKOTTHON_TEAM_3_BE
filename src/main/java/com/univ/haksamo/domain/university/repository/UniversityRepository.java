package com.univ.haksamo.domain.university.repository;

import com.univ.haksamo.domain.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University,Long> {
    List<University> findByName(String name);
}
