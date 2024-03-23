package com.univ.haksamo.domain.university.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UniversityListDto {
    List<UniversityDto> universities;
}
