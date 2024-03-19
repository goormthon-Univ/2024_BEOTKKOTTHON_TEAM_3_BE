package com.univ.haksamo.domain.keyword.entity;

import com.univ.haksamo.global.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "KEYWORD_TABLE")
public class Keyword extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
