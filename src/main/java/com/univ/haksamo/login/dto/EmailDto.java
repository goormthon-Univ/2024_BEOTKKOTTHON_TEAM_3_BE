package com.univ.haksamo.login.dto;

import lombok.Getter;

@Getter
public class EmailDto {
    private String email;

    public EmailDto(String email) {
        this.email = email;
    }
}
