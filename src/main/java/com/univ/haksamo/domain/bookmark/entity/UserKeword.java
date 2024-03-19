package com.univ.haksamo.domain.bookmark.entity;


import com.univ.haksamo.domain.keyword.entity.Keyword;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "USERKEYWORD_TABLE")
public class UserKeword extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "keywordId")
    private Keyword keyword;
}
