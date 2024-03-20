package com.univ.haksamo.domain.image.entity;

import com.univ.haksamo.domain.board.entity.Board;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "IMAGE_TABLE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String url;
    private String name;
    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
}
