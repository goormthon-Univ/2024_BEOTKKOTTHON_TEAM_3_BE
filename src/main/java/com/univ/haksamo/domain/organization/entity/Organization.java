package com.univ.haksamo.domain.organization.entity;

import com.univ.haksamo.domain.university.entity.University;
import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ORGANIZATION_TABLE")
public class Organization extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "univId")
    private University university;
}
