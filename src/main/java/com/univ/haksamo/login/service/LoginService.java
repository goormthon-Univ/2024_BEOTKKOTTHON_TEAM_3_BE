package com.univ.haksamo.login.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LoginService {
    private final JavaMailSender javaMailSender;

    public LoginService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String email) {
        sendAuthnEmail(email);
    }

    public void sendAuthnEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("haksamo.service@gmail.com");
        message.setTo(email);
        message.setSubject("학사모 이메일 인증");
        message.setText(createKey());
        javaMailSender.send(message);
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }
}
