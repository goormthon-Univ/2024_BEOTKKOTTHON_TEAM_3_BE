package com.univ.haksamo.login.service;

import com.univ.haksamo.domain.user.dto.UserDto;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.domain.user.repository.UserRepository;
import com.univ.haksamo.jwt.TokenDto;
import com.univ.haksamo.jwt.TokenProvider;
import com.univ.haksamo.jwt.TokenRequestDto;
import com.univ.haksamo.login.dto.AuthnMailDto;
import com.univ.haksamo.login.dto.EmailDto;
import com.univ.haksamo.login.dto.UserLoginDto;
import com.univ.haksamo.redis.RedisService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Random;

@Service
public class LoginService {
    private final JavaMailSender javaMailSender;
    private final RedisService redisService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Autowired
    public LoginService(JavaMailSender javaMailSender, RedisService redisService, AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository memberRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.javaMailSender = javaMailSender;
        this.redisService = redisService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public void send(EmailDto emailDto) {
        sendAuthnEmail(emailDto.getEmail());
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

    @Transactional
    public TokenDto login(UserLoginDto memberRequestDto) {
        Duration duration = Duration.ofDays(1);

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        redisService.setValues(authentication.getName(), tokenDto.getRefreshToken(), duration);
        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        Duration duration = Duration.ofDays(1);
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        String token = redisService.getValues(authentication.getName());

        // 4. Refresh Token 일치하는지 검사
        if (!token.equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        redisService.setValues(authentication.getName(),tokenDto.getRefreshToken(),duration);

        // 토큰 발급
        return tokenDto;
    }
}
