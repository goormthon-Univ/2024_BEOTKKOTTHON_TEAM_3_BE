package com.univ.haksamo.login.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter

public class EmailDTO {
    private String email;

    @JsonCreator
    public EmailDTO(String email) {
        this.email = email;
    }
}
