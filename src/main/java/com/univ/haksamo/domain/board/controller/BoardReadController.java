package com.univ.haksamo.domain.board.controller;

import com.univ.haksamo.domain.board.service.BoardReadService;
import com.univ.haksamo.domain.board.service.res.BoardDto;
import com.univ.haksamo.domain.board.service.res.BoardListResponse;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공지글 읽기 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.user}")
public class BoardReadController {
    private final BoardReadService boardReadService;

    @Operation(summary = "공지글 호출 api")
    @GetMapping("/boards")
    public SuccessResponse<BoardListResponse> boardList(@RequestParam Long groupId,
                                                        @RequestParam Long lastBoardId){
        BoardListResponse boards = boardReadService.getBoards(lastBoardId, groupId);
        return new SuccessResponse(boards);
    }

    @Operation(summary = "공지글 검색 api")
    @GetMapping("/boards/search")
    public SuccessResponse<BoardListResponse> boardSearchList(@RequestParam String input){
        System.out.println(input);
        BoardListResponse boards = boardReadService.getBoardsByInput(input);
        return new SuccessResponse(boards);
    }

    @Operation(summary = "공지글 id를 통해 상세글 조회 api")
    @GetMapping("/boards/{boardId}")
    public SuccessResponse<BoardDto>board(@PathVariable Long boardId){
        return new SuccessResponse(boardReadService.getBoard(boardId));
    }
}
