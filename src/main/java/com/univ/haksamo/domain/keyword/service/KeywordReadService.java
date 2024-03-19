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
    private final KeywordRepository keywordRepository;
    private final UserKeywordRepository userKeywordRepository;

    public List<Keyword> getKeywords() {
        return keywordRepository.findAll();
    }

    public UserKeywordsDto getUserKeywords(Long userId) {
        List<SelectedKeywordDTO> selectedKeywords = new ArrayList<>();
        List<UserKeword> userKewords = userKeywordRepository.findAllByUserId(userId);
        List<Keyword> keywords = getKeywords();

        for (Keyword keyword : keywords) {
            SelectedKeywordDTO selectedKeywordDTO = SelectedKeywordDTO.builder()
                    .description(keyword.getDescription())
                    .name(keyword.getName())
                    .id(keyword.getId())
                    .build();
            selectedKeywords.add(selectedKeywordDTO);
        }

        for (UserKeword userKeword : userKewords) {
            for (SelectedKeywordDTO selectedKeyword : selectedKeywords) {
                if (selectedKeyword.getId() == userKeword.getKeyword().getId()) {
                    selectedKeyword.decideSelected(true);
                    break;
                }
            }
        }

        return UserKeywordsDto.builder()
                .keywords(selectedKeywords)
                .build();
    }
}
