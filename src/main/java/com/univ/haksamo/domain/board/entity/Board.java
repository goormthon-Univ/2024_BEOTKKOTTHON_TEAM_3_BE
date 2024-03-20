package com.univ.haksamo.domain.board.entity;

import com.univ.haksamo.domain.group.entity.Group;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "BOARD_TABLE")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "organId")
    private Group group;
}
