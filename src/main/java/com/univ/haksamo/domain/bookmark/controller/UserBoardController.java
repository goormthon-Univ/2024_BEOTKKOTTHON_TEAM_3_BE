package com.univ.haksamo.domain.bookmark.controller;

import com.univ.haksamo.domain.bookmark.dto.ScrapBoardsDto;
import com.univ.haksamo.domain.bookmark.service.UserBoardService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시글 스크랩 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.user}")
public class UserBoardController {
    private final UserBoardService userBoardService;

    /**
     * 사용자가 스크랩한 게시글 조회 컨트롤러
     *
     * @param userId
     * @return
     */
    @Operation(summary = "게시글 스크랩 목록 조회 api")
    @GetMapping("/users/{userId}/boards")
    public SuccessResponse<ScrapBoardsDto> scrapBoards(@PathVariable Long userId) {
        ScrapBoardsDto userBoard = userBoardService.findUserBoard(userId);
        return new SuccessResponse(userBoard);
    }

    /**
     * 게시글 스크랩 컨트롤러
     * @param userId
     * @param boardId
     * @return
     */
    @Operation(summary = "게시글 스크랩 api")
    @PostMapping("/users/{userId}/boards/{boardId}")
    public SuccessResponse<String> saveUserBoard(@PathVariable Long userId,
                                                 @PathVariable Long boardId) {
        userBoardService.save(userId, boardId);
        return SuccessResponse.ok();
    }

    /**
     * 게시글 스크랩 취소 컨트롤러
     * @param userId
     * @param boardId
     * @return
     */
    @Operation(summary = "게시글 스크랩 취소 조회 api")
    @DeleteMapping("/users/{userId}/boards/{boardId}")
    public SuccessResponse<String> deleteUserBoard(@PathVariable Long userId,
                                                 @PathVariable Long boardId) {
        userBoardService.delete(userId, boardId);
        return SuccessResponse.ok();
    }
}
