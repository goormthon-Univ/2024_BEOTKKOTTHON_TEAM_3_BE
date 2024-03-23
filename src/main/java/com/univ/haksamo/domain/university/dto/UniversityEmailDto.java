package com.univ.haksamo.domain.university.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class UniversityEmailDto {
    String email;

    @JsonCreator
    public UniversityEmailDto(String email){
        this.email = email;
    }

}
