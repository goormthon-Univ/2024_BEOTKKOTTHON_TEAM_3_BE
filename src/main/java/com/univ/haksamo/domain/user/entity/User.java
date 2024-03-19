package com.univ.haksamo.domain.user.entity;

import com.univ.haksamo.domain.university.entity.University;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USER_TABLE")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "univId")
    private University university;

    private String name;
    private String fcmToken;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
}
