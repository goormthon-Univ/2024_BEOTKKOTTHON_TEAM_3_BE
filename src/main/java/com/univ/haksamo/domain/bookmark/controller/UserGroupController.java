package com.univ.haksamo.domain.bookmark.controller;

import com.univ.haksamo.domain.bookmark.service.UserGroupService;
import com.univ.haksamo.domain.bookmark.dto.FavoriteGroupDto;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "학과 즐겨찾기 관련 api")
@RestController
@RequiredArgsConstructor
public class UserGroupController {
    private final UserGroupService userGroupService;

    @Operation(summary = "학과 즐겨찾기 추가 api")
    @PostMapping("/users/{userId}/favorite/groups")
    public SuccessResponse<String> userFavoriteGroups(@PathVariable Long userId, @RequestBody FavoriteGroupDto favoriteGroupDto){
        userGroupService.saveFavoriteGroup(userId, favoriteGroupDto);
        return SuccessResponse.ok();
    }

    @Operation(summary = "학과 즐겨찾기 취소 api")
    @DeleteMapping("/users/{userId}/favorite/groups")
    public SuccessResponse<String> deleteUserFavoriteGroups(@PathVariable Long userId, @RequestBody FavoriteGroupDto favoriteGroupDto){
        userGroupService.deleteFavoriteGroup(userId, favoriteGroupDto);
        return SuccessResponse.ok();
    }

}
