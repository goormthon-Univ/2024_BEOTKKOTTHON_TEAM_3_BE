package com.univ.haksamo.domain.keyword.controller;

import com.univ.haksamo.domain.keyword.service.KeywordReadService;
import com.univ.haksamo.domain.keyword.service.res.UserKeywordsDto;
import com.univ.haksamo.global.format.success.SuccessResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.user}")
public class KeywordController {
    private final KeywordReadService keywordReadService;
    @GetMapping("/users/{userId}/keywords")
    public SuccessResponse<UserKeywordsDto> keywordsWithSelected(@PathVariable Long userId){
        return new SuccessResponse(keywordReadService.getUserKeywords(userId));
    }
}
