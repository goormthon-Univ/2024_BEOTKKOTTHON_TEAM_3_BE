package com.univ.haksamo.domain.bookmark.entity;


import com.univ.haksamo.domain.keyword.entity.Keyword;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private boolean selected;
    public void changeSelect(boolean selected){
        this.selected = selected;
    }
}
