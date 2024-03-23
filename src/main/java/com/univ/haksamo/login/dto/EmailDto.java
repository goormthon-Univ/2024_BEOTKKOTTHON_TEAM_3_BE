package com.univ.haksamo.login.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter

public class EmailDto {
    private String email;

    @JsonCreator
    public EmailDto(String email) {
        this.email = email;
    }
}
