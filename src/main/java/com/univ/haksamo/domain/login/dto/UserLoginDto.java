package com.univ.haksamo.login.dto;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class UserLoginDto {
    private String email;
    private String password;
    private String fcmToken;

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
        this.fcmToken = getFcmToken();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
