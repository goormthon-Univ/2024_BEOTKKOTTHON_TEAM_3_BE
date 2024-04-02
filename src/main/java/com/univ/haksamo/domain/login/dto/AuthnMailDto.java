package com.univ.haksamo.login.dto;

import lombok.Getter;

@Getter
public class AuthnMailDto {
    private String email;
    private String authnCode;

    public AuthnMailDto(String email, String authnCode) {
        this.email = email;
        this.authnCode = authnCode;
    }

}
