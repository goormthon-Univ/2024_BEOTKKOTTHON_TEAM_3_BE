package com.univ.haksamo.domain.common.fcm.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.univ.haksamo.domain.common.fcm.dto.FcmMessage;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageSender {

    @Value("${fcm.api.uri}")
    private String API_URI;
    private final ObjectMapper objectMapper;
    private static final String PUSH_TITLE = "학 사 모";
    private static final String PUSH_BODY = "";

    /**
     *
     * @param targetToken 사용자 엔티티에 저장되어있는 fcm토큰
     * @param keywordName 새로운 글의 키워드명
     * @throws IOException
     */
    public void sendMessageTo(String targetToken,String keywordName,Long boardId) throws IOException {
        System.out.println(targetToken);
        String message = makeMessage(targetToken, keywordName,boardId);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json;charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URI).post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json;UTD-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(request.body().toString());
    }

    private String makeMessage(String targetToken, String keyword,Long boardId) throws JsonParseException, JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                        .token(targetToken)
                        .notification(FcmMessage.Notification.builder()
                                .title(PUSH_TITLE)
                                .body(keyword+PUSH_BODY)
                                .image(null)
                                .build()
                        ).build()).validateOnly(false).build();

        return objectMapper.writeValueAsString(fcmMessage);
    }
    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/firebase_service_key.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

}
