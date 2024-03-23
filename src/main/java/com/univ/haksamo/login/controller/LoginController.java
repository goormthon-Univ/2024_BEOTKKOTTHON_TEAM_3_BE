package com.univ.haksamo.login.controller;

import com.univ.haksamo.domain.user.dto.UserDto;
import com.univ.haksamo.jwt.TokenDto;
import com.univ.haksamo.jwt.TokenRequestDto;
import com.univ.haksamo.login.dto.AuthnMailDto;
import com.univ.haksamo.login.dto.EmailDto;
import com.univ.haksamo.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/haksamo/authn/email")
    @ResponseBody
    public boolean sendAuthnMailController(@RequestBody EmailDto emailDto) {
        try {
            loginService.send(emailDto);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @PostMapping("/haksamo/authn/email/check")
    @ResponseBody
    public boolean authnCodeCheckController(@RequestBody AuthnMailDto authnMailDto) {
        return loginService.checkEmailAuthn(authnMailDto);
    }

    @PostMapping("/haksamo/authn/login")
    @ResponseBody
    public ResponseEntity<TokenDto> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(loginService.login(userDto));
    }

    @PostMapping("/haksamo/authn/reissue")
    @ResponseBody
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(loginService.reissue(tokenRequestDto));
    }

}
