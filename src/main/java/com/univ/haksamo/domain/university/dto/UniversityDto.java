package com.univ.haksamo.domain.university.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UniversityDto {
    private String email;
    private Long univId;
    private String univName;

}
