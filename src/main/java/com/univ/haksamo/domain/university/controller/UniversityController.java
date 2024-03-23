package com.univ.haksamo.domain.university.controller;

import com.univ.haksamo.domain.university.dto.UniversityEmailDto;
import com.univ.haksamo.domain.university.service.UniversityService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name = "대학교 관련 api")
@Controller
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @Operation(summary = "학교 이메일 받는 api")
    @GetMapping("/haksamo/authn/users/{university}/email")
    @ResponseBody
    public SuccessResponse<UniversityEmailDto> univEmailController(@PathVariable String university){
        System.out.println(universityService.getUnivEmailByName(university));
        UniversityEmailDto universityEmailDto = new UniversityEmailDto(universityService.getUnivEmailByName(university));
        return new SuccessResponse(universityEmailDto);
    }
}
