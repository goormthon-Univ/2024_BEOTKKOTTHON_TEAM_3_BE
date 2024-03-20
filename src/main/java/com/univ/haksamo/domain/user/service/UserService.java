package com.univ.haksamo.domain.user.service;

import com.univ.haksamo.domain.university.entity.University;
import com.univ.haksamo.domain.university.repository.UniversityRepository;
import com.univ.haksamo.domain.user.dto.UserDto;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private UniversityRepository universityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public  UserService(UserRepository userRepository, UniversityRepository universityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDto userDto){
        String univName = userDto.getUnivName();
        University university = universityRepository.findByName(univName).get(0);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(User.toEntity(userDto, university));
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }


}
