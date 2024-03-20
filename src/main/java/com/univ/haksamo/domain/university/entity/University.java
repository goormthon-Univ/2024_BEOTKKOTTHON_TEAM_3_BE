package com.univ.haksamo.domain.university.entity;

import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "UNIVERSITY_TABLE")
public class University extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
