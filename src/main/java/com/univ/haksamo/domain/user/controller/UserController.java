package com.univ.haksamo.domain.user.controller;


import com.univ.haksamo.domain.user.dto.UserDto;
import com.univ.haksamo.domain.user.dto.UserPageDto;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.domain.user.service.UserService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "유저 정보 관련 api")
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public  UserController(UserService userService){
        this.userService = userService;
    }


    @Operation(summary = "회원가입 api")
    @PostMapping("/haksamo/sign-up")
    @ResponseBody
    public SuccessResponse<Boolean> signUpController(@RequestBody UserDto userDto){
        try{
            userService.saveUser(userDto);
            return new SuccessResponse(true);

        } catch(Exception e) {
            return new SuccessResponse(false);
        }
    }

    @Operation(summary = "mypage api")
    @GetMapping("/haksamo/my-page")
    @ResponseBody
    public SuccessResponse<UserPageDto> findMe() {
        return new SuccessResponse(userService.findMe());
    }



}
