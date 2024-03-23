package com.univ.haksamo.domain.keyword.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
public class ChangeKeywordRequest {
    private List<ChangeKeyword> changeKeywords;
    private Long userId;
}
