package com.univ.haksamo.domain.user.controller;


import com.univ.haksamo.domain.user.dto.UserDto;
import com.univ.haksamo.domain.user.dto.UserPageDto;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public  UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/haksamo/authn/sign-up")
    @ResponseBody
    public void signUpController(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
    }

    @GetMapping("/haksamo/my-page")
    @ResponseBody
    public ResponseEntity<UserPageDto> findMe() {
        return ResponseEntity.ok(userService.findMe());
    }


}
