package com.univ.haksamo.domain.user.controller;


import com.univ.haksamo.domain.user.dto.UserDto;
import com.univ.haksamo.domain.user.dto.UserPageDto;
import com.univ.haksamo.domain.user.service.UserService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public SuccessResponse<String> signUpController(@Valid @RequestBody UserDto userDto){
        userService.saveUser(userDto);
        userService.saveUserKeyword(userDto);
        return SuccessResponse.ok();

    }

    @Operation(summary = "mypage api")
    @GetMapping("/haksamo/{userId}")
    @ResponseBody
    public SuccessResponse<UserPageDto> findMe(@PathVariable Long userId) {
        return new SuccessResponse(userService.findMe(userId));
    }



}
