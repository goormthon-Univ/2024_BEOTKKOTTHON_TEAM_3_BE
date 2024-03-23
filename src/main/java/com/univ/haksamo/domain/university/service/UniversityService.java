package com.univ.haksamo.domain.university.service;


import com.univ.haksamo.domain.university.dto.UniversityDto;
import com.univ.haksamo.domain.university.dto.UniversityListDto;
import com.univ.haksamo.domain.university.entity.University;
import com.univ.haksamo.domain.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityListDto getUniversity() {
        List<University> universities = universityRepository.findAll();
        List<UniversityDto> universityDtos = new ArrayList<>();
        for (University university : universities) {
            universityDtos.add(UniversityDto.builder()
                    .email(university.getUnivEmail())
                    .univId(university.getId())
                    .univName(university.getName())
                    .build());
        }
        return UniversityListDto.builder()
                .universities(universityDtos)
                .build();
    }
}
