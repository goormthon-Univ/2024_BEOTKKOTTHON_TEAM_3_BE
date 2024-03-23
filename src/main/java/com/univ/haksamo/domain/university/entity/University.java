package com.univ.haksamo.domain.university.entity;

import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "UNIVERSITY_TABLE")
@Getter
public class University extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String univEmail;
}
