package com.univ.haksamo.domain.board.controller;

import com.univ.haksamo.domain.board.controller.req.WriteBoardRequestDto;
import com.univ.haksamo.domain.board.service.BoardWriteService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.admin}")
public class BoardWriteController {

    private final BoardWriteService boardWriteService;
    @PostMapping("/groups/{groupId}/boards")
    public SuccessResponse<String> write(@PathVariable Long groupId,
                                 @RequestPart List<MultipartFile> images,
                                 @RequestPart WriteBoardRequestDto requestDto) throws IOException {
        boardWriteService.writeBoard(groupId,images,requestDto);
        return SuccessResponse.ok();
    }
}
