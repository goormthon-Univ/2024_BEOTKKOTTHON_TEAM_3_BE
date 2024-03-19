package com.univ.haksamo.login.controller;

import com.univ.haksamo.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void mail(@RequestBody Map<String, String> email) {
        loginService.send(email.get("email"));
    }
}
