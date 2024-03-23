package com.univ.haksamo.domain.bookmark.controller;


import com.univ.haksamo.domain.bookmark.service.UserKeywordService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserKeyWordController {
    private final UserKeywordService userKeywordService;


    @PostMapping("/haksamo/first/users/keywords")
    public SuccessResponse<String> FirstLoginKeywordController() {
        userKeywordService.FirstLoginSaveKeyword();
        return SuccessResponse.ok();
    }

}
