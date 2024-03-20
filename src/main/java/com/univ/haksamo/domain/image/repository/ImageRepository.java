package com.univ.haksamo.domain.image.repository;

import com.univ.haksamo.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findAllByBoardId(Long boardId);
}
