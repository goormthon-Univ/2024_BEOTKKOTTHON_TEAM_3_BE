package com.univ.haksamo.domain.bookmark.entity;

import com.univ.haksamo.domain.board.entity.Board;
import com.univ.haksamo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERBOARD_TABLE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Board board;

    @ManyToOne
    @JoinColumn
    private User user;
}
