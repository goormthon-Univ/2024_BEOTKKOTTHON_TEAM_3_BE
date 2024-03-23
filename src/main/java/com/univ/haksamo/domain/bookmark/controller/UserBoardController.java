package com.univ.haksamo.domain.bookmark.controller;

import com.univ.haksamo.domain.bookmark.dto.ScrapBoardsDto;
import com.univ.haksamo.domain.bookmark.service.UserBoardService;
import com.univ.haksamo.global.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/users/{userId}/boards/{boardId}")
    public SuccessResponse<String> deleteUserBoard(@PathVariable Long userId,
                                                 @PathVariable Long boardId) {
        userBoardService.delete(userId, boardId);
        return SuccessResponse.ok();
    }
}
