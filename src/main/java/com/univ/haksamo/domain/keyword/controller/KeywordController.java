package com.univ.haksamo.domain.keyword.controller;

import com.univ.haksamo.domain.keyword.dto.ChangeKeywordDto;
import com.univ.haksamo.domain.keyword.dto.ChangeKeywordRequest;
import com.univ.haksamo.domain.keyword.service.KeywordReadService;
import com.univ.haksamo.domain.keyword.service.KeywordWriteService;
import com.univ.haksamo.domain.keyword.service.res.UserKeywordsDto;
import com.univ.haksamo.global.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.user}")
public class KeywordController {
    private final KeywordReadService keywordReadService;
    private final KeywordWriteService keywordWriteService;

    @GetMapping("/users/{userId}/keywords")
    public SuccessResponse<UserKeywordsDto> keywordsWithSelected(@PathVariable Long userId) {
        return new SuccessResponse(keywordReadService.getUserKeywords(userId));
    }

    @PostMapping("/users/{userId}/keywords")
    public SuccessResponse<String> changeSelectKeywords(@PathVariable Long userId,
                                                        @RequestBody ChangeKeywordDto changeKeywordsDto) {
        keywordWriteService.changeSelect(ChangeKeywordRequest.builder()
                .changeKeywords(changeKeywordsDto.getChangeKeywords())
                .userId(userId)
                .build());
        return SuccessResponse.ok();
    }
}
