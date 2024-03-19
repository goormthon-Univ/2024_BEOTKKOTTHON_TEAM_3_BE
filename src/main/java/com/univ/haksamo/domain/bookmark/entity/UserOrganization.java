package com.univ.haksamo.domain.bookmark.entity;

import com.univ.haksamo.domain.organization.entity.Organization;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "USERORGANIZATION_TABLE")
public class UserOrganization extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "organId")
    private Organization organization;
}
