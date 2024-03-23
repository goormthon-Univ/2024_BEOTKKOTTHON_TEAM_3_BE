package com.univ.haksamo.domain.board.controller;

import com.univ.haksamo.domain.board.controller.req.WriteBoardRequestDto;
import com.univ.haksamo.domain.board.service.BoardWriteService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "공지글 쓰기 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.admin}")
public class BoardWriteController {

    private final BoardWriteService boardWriteService;

    @Operation(summary = "공지글 작성 api")
    @PostMapping("/groups/{groupId}/boards")
    public SuccessResponse<String> write(@PathVariable Long groupId,
                                 @RequestPart List<MultipartFile> images,
                                 @RequestPart WriteBoardRequestDto requestDto) throws IOException {
        boardWriteService.writeBoard(groupId,images,requestDto);
        return SuccessResponse.ok();
    }
}
