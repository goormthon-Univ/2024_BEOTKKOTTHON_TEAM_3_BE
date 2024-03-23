package com.univ.haksamo.domain.university.controller;

import com.univ.haksamo.domain.university.dto.UniversityDto;
import com.univ.haksamo.domain.university.dto.UniversityListDto;
import com.univ.haksamo.domain.university.service.UniversityService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "대학교 관련 api")
@RestController
@RequestMapping("${api.prefix.user}")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @Operation(summary = "학교 이메일 받는 api")
    @GetMapping("/universities")
    public SuccessResponse<UniversityListDto> univEmailController(){
        UniversityListDto universities = universityService.getUniversity();
        return new SuccessResponse(universities);
    }
}
