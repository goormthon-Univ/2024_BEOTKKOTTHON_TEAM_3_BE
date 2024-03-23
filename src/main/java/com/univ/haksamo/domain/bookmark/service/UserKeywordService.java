package com.univ.haksamo.domain.bookmark.service;

import com.univ.haksamo.domain.bookmark.entity.UserKeword;
import com.univ.haksamo.domain.bookmark.repository.UserKeywordRepository;
import com.univ.haksamo.domain.keyword.entity.Keyword;
import com.univ.haksamo.domain.keyword.repository.KeywordRepository;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.domain.user.repository.UserRepository;
import com.univ.haksamo.global.format.exception.user.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserKeywordService {
    private final UserKeywordRepository userKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final UserRepository userRepository;

    public void FirstLoginSaveKeyword() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(NotFoundUserException::new);

        if (userKeywordRepository.findAllByUserId(user.getId()).isEmpty()) {
            for (Keyword keyword : keywordRepository.findAll()) {
                UserKeword userKeword = UserKeword.builder()
                        .user(user)
                        .keyword(keyword)
                        .selected(false)
                        .build();
                userKeywordRepository.save(userKeword);
            }
        }

    }

}
