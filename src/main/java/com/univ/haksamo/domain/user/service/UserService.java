package com.univ.haksamo.domain.user.service;

import com.univ.haksamo.domain.bookmark.entity.UserKeword;
import com.univ.haksamo.domain.bookmark.repository.UserKeywordRepository;
import com.univ.haksamo.domain.keyword.entity.Keyword;
import com.univ.haksamo.domain.keyword.repository.KeywordRepository;
import com.univ.haksamo.domain.university.entity.University;
import com.univ.haksamo.domain.university.repository.UniversityRepository;
import com.univ.haksamo.domain.user.dto.UserDto;
import com.univ.haksamo.domain.user.dto.UserPageDto;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.domain.user.repository.UserRepository;
import com.univ.haksamo.global.format.exception.user.NotFoundUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private UniversityRepository universityRepository;
    private KeywordRepository keywordRepository;
    private UserKeywordRepository userKeywordRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, UniversityRepository universityRepository, BCryptPasswordEncoder passwordEncoder, KeywordRepository keywordRepository, UserKeywordRepository userKeywordRepository) {
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.passwordEncoder = passwordEncoder;
        this.keywordRepository = keywordRepository;
        this.userKeywordRepository = userKeywordRepository;
    }

    public void saveUser(UserDto userDto) {
        Long univId = userDto.getUnivId();
        University university = universityRepository.findById(univId).orElseThrow(()->new IllegalArgumentException());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(User.toEntity(userDto, university));
    }

    public UserPageDto findMe() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(NotFoundUserException::new);

        return UserPageDto.toDTO(user, user.getUniversity().getName());
    }

    public void saveUserKeyword(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(NotFoundUserException::new);

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
