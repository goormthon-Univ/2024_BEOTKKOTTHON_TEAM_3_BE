package com.univ.haksamo.domain.user.service;

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

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private UniversityRepository universityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UniversityRepository universityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDto userDto) {
        String univName = userDto.getUnivName();
        University university = universityRepository.findByName(univName);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(User.toEntity(userDto, university));
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public UserPageDto findMe() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(NotFoundUserException::new);

        return UserPageDto.toDTO(user, user.getUniversity().getName());
    }


}
