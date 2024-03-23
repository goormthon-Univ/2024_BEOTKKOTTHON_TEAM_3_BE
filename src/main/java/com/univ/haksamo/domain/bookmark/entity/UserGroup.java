package com.univ.haksamo.domain.bookmark.entity;

import com.univ.haksamo.domain.group.entity.Group;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@Table(name = "USER_GROUP_TABLE")
@NoArgsConstructor
@AllArgsConstructor

public class UserGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "groupId")
    private Group group;
}
