package com.univ.haksamo.login.controller;

import com.univ.haksamo.global.format.success.SuccessResponse;
import com.univ.haksamo.jwt.UserTokenDto;
import com.univ.haksamo.jwt.TokenRequestDto;
import com.univ.haksamo.login.dto.AuthnMailDto;
import com.univ.haksamo.login.dto.EmailDTO;
import com.univ.haksamo.login.dto.UserLoginDto;
import com.univ.haksamo.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "이메일 인증 및 로그인 관련 api")
@Controller
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(summary = "이메일로 인증번호 전송 api")
    @PostMapping("/haksamo/authn/email")
    @ResponseBody
    public SuccessResponse<String> sendAuthnMailController(@RequestBody EmailDTO emailDto) {
        loginService.send(emailDto);
        return SuccessResponse.ok();
    }

    @Operation(summary = "인증번호 확인 api")
    @PostMapping("/haksamo/authn/email/check")
    @ResponseBody
    public SuccessResponse<Boolean> authnCodeCheckController(@RequestBody AuthnMailDto authnMailDto) {
        return new SuccessResponse(loginService.checkEmailAuthn(authnMailDto));
    }

    @Operation(summary = "로그인 api")
    @PostMapping("/haksamo/authn/login")
    @ResponseBody
    public SuccessResponse<UserTokenDto> login(@RequestBody UserLoginDto userLoginDto) {
        return new SuccessResponse(loginService.login(userLoginDto));
    }

    @Operation(summary = "엑세스 토큰 재발급 api")
    @PostMapping("/haksamo/authn/reissue")
    @ResponseBody
    public SuccessResponse<UserTokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return new SuccessResponse(loginService.reissue(tokenRequestDto));
    }

}
