package com.univ.haksamo.domain.bookmark.controller;


import com.univ.haksamo.domain.bookmark.service.UserKeywordService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 키워드 관련 api")
@RestController
@RequiredArgsConstructor
public class UserKeyWordController {
    private final UserKeywordService userKeywordService;

    @Operation(summary = "회원가입하면 키워드 목록 가져오는 api")
    @PostMapping("/haksamo/users/keywords")
    public SuccessResponse<String> saveUserKeywordController() {
        userKeywordService.saveUserKeyword();
        return SuccessResponse.ok();
    }

}
