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
        System.out.println("여기까진 됌2");

        List<UserKeword> userKewords = userKeywordRepository.findAllByKeywordId(keywordId);
        System.out.println("여기까진 됌3");

        for (UserKeword userKeword : userKewords) {
            if(!userKeword.isSelected()){
                continue;
            }
            System.out.println("여기까진 됌4");

            String userFcmToken = userKeword.getUser().getFcmToken();
            System.out.println("여기까진 됌4");

            messageSender.sendMessageTo(userFcmToken,keyword.getName(),boardId);
        }
    }
}
