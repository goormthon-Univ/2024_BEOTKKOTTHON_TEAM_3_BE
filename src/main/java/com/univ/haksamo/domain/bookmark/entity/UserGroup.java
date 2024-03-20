package com.univ.haksamo.domain.bookmark.entity;

import com.univ.haksamo.domain.group.entity.Group;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "USER_GROUP_TABLE")
public class UserGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organId")
    private Group group;
}
