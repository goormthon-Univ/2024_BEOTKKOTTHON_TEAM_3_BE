package com.univ.haksamo.login.service;

import com.univ.haksamo.login.dto.AuthnMailDto;
import com.univ.haksamo.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Service
public class LoginService {
    private final JavaMailSender javaMailSender;
    private final RedisService redisService;

    @Autowired
    public LoginService(JavaMailSender javaMailSender, RedisService redisService) {
        this.javaMailSender = javaMailSender;
        this.redisService = redisService;
    }

    public void send(String email) {
        sendAuthnEmail(email);
    }
    public boolean checkEmailAuthn(AuthnMailDto authnMailDto) {
        if(authnMailDto.getAuthnCode().equals(redisService.getValues(authnMailDto.getEmail()))){
            return true;
        }else{
            return false;
        }
    }


    public void sendAuthnEmail(String email) {
        String authnKey = createKey();
        Duration duration = Duration.ofMinutes(5);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("haksamo.service@gmail.com");
        message.setTo(email);
        message.setSubject("학사모 이메일 인증");
        message.setText(authnKey);
        redisService.setValues(email, authnKey, duration);
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
