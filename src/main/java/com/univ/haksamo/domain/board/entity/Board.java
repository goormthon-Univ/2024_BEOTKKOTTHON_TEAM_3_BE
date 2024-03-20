package com.univ.haksamo.domain.board.entity;

import com.univ.haksamo.domain.group.entity.Group;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOARD_TABLE")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private Long keywordId;
    @ManyToOne
    @JoinColumn(name = "organId")
    private Group group;
}
