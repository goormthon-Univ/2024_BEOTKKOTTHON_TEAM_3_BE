package com.univ.haksamo.domain.keyword.controller;

import com.univ.haksamo.domain.keyword.service.KeywordReadService;
import com.univ.haksamo.domain.keyword.service.res.UserKeywordsDto;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "키워드 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.user}")
public class KeywordController {
    private final KeywordReadService keywordReadService;

    @Operation(summary = "즐겨찾기한 키워드 조회 api")
    @GetMapping("/users/{userId}/keywords")
    public SuccessResponse<UserKeywordsDto> keywordsWithSelected(@PathVariable Long userId){
        return new SuccessResponse(keywordReadService.getUserKeywords(userId));
    }
}
