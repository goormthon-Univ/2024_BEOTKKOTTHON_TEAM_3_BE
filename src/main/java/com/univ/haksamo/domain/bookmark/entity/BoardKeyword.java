package com.univ.haksamo.domain.bookmark.entity;

import com.univ.haksamo.domain.board.entity.Board;
import com.univ.haksamo.domain.keyword.entity.Keyword;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "BOARDKEYWORD_TABLE")
public class BoardKeyword extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "keywordId")
    private Keyword keyword;
}
