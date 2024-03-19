package com.univ.haksamo.login.dto;

import lombok.Getter;

@Getter
public class AuthnMailDto {
    private String email;
    private String AuthnCode;

    public AuthnMailDto(String email, String authnCode) {
        this.email = email;
        this.AuthnCode = authnCode;
    }

}
