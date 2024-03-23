package com.univ.haksamo.domain.user.service;

import com.univ.haksamo.domain.user.repository.UserRepository;
import com.univ.haksamo.global.format.exception.user.NotFoundUserException;
import com.univ.haksamo.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        return createUserDetails(memberRepository.findByEmail(username)
                .orElseThrow(NotFoundUserException::new));

    }

    private UserDetails createUserDetails(com.univ.haksamo.domain.user.entity.User member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthorities().toString());

        User user = new User(
                member.getUsername(),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
        return user;
    }
}
