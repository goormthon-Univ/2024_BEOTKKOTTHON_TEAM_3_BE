package com.univ.haksamo.domain.keyword.service;

import com.univ.haksamo.domain.bookmark.entity.UserKeword;
import com.univ.haksamo.domain.bookmark.repository.UserKeywordRepository;
import com.univ.haksamo.domain.keyword.entity.Keyword;
import com.univ.haksamo.domain.keyword.repository.KeywordRepository;
import com.univ.haksamo.domain.keyword.service.res.SelectedKeywordDTO;
import com.univ.haksamo.domain.keyword.service.res.UserKeywordsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordReadService {
    private final UserKeywordRepository userKeywordRepository;

    public UserKeywordsDto getUserKeywords(Long userId) {
        List<SelectedKeywordDTO> selectedKeywords = new ArrayList<>();

        List<UserKeword> userKewords = userKeywordRepository.findAllByUserId(userId);

        for (UserKeword userKeword : userKewords) {
            Keyword keyword = userKeword.getKeyword();
            boolean selected = userKeword.isSelected();
            selectedKeywords.add(SelectedKeywordDTO.builder()
                    .description(keyword.getDescription())
                    .id(keyword.getId())
                    .isSelected(selected)
                    .name(keyword.getName())
                    .build());
        }

        return UserKeywordsDto.builder()
                .keywords(selectedKeywords)
                .build();
    }
}
