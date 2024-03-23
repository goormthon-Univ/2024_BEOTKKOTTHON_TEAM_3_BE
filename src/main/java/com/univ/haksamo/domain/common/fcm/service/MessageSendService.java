package com.univ.haksamo.domain.common.fcm.service;

import com.univ.haksamo.domain.bookmark.entity.UserKeword;
import com.univ.haksamo.domain.bookmark.repository.UserKeywordRepository;
import com.univ.haksamo.domain.keyword.entity.Keyword;
import com.univ.haksamo.domain.keyword.repository.KeywordRepository;
import com.univ.haksamo.global.format.exception.userkeyword.NotFoundUserKeywordException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageSendService {
    private final MessageSender messageSender;
    private final UserKeywordRepository userKeywordRepository;
    private final KeywordRepository keywordRepository;

    @Async
    public void send(Long boardId,Long keywordId) throws IOException {
        Keyword keyword = keywordRepository.findById(keywordId)
                .orElseThrow(NotFoundUserKeywordException::new);
        List<UserKeword> userKewords = userKeywordRepository.findAllByKeywordId(keywordId);
        for (UserKeword userKeword : userKewords) {
            if(!userKeword.isSelected()){
                continue;
            }
            String userFcmToken = userKeword.getUser().getFcmToken();
            messageSender.sendMessageTo(userFcmToken,keyword.getName(),boardId);
        }
    }
}
