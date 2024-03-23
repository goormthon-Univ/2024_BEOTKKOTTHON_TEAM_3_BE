package com.univ.haksamo.domain.board.controller;

import com.univ.haksamo.domain.board.service.BoardReadService;
import com.univ.haksamo.domain.board.service.res.BoardListResponse;
import com.univ.haksamo.global.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.user}")
public class BoardReadController {
    private final BoardReadService boardReadService;

    @GetMapping("/boards")
    public SuccessResponse<BoardListResponse> boardList(@RequestParam Long groupId,
                                                        @RequestParam Long lastBoardId){
        BoardListResponse boards = boardReadService.getBoards(lastBoardId, groupId);
        return new SuccessResponse(boards);
    }

    @GetMapping("/boards/search")
    public SuccessResponse<BoardListResponse> boardSearchList(@RequestParam String input){
        System.out.println(input);
        BoardListResponse boards = boardReadService.getBoardsByInput(input);
        return new SuccessResponse(boards);
    }
}
