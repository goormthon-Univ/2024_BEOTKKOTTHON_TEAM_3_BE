package com.univ.haksamo.domain.university.service;


import com.univ.haksamo.domain.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;

    public String getUnivEmailByName(String name) {
        return universityRepository.findByName(name).getUnivEmail();
    }
}
