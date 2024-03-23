package com.univ.haksamo.domain.keyword.service;

import com.univ.haksamo.domain.bookmark.entity.UserKeword;
import com.univ.haksamo.domain.bookmark.repository.UserKeywordRepository;
import com.univ.haksamo.domain.keyword.dto.ChangeKeyword;
import com.univ.haksamo.domain.keyword.dto.ChangeKeywordRequest;
import com.univ.haksamo.global.format.exception.userkeyword.NotFoundUserKeywordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordWriteService {
    private final UserKeywordRepository userKeywordRepository;
    public void changeSelect(ChangeKeywordRequest changeKeywordsRequest) {
        Long userId = changeKeywordsRequest.getUserId();
        for (ChangeKeyword changeKeyword : changeKeywordsRequest.getChangeKeywords()) {
            UserKeword userKeyword = userKeywordRepository.findAllByUserIdAndKeywordId(userId, changeKeyword.getKeywordId())
                    .orElseThrow(NotFoundUserKeywordException::new);
            userKeyword.changeSelect(changeKeyword.isSelect());
        }
    }
}
