package com.univ.haksamo.domain.keyword.service.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserKeywordsDto {
    private List<SelectedKeywordDTO> keywords;
}
