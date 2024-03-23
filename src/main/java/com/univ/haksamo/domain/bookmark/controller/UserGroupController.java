package com.univ.haksamo.domain.bookmark.controller;

import com.univ.haksamo.domain.bookmark.service.UserGroupService;
import com.univ.haksamo.domain.group.controller.res.FavoriteGroup;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "학과 즐겨찾기 관련 api")
@RestController
@RequiredArgsConstructor
public class UserGroupController {
    private final UserGroupService userGroupService;

    @Operation(summary = "학과 즐겨찾기 추가 api")
    @PostMapping("/users/favorite/groups")
    public SuccessResponse<String> userFavoriteGroups(@RequestBody FavoriteGroup favoriteGroup){
        userGroupService.saveFavoriteGroup(favoriteGroup);
        return SuccessResponse.ok();
    }

    @Operation(summary = "학과 즐겨찾기 취소 api")
    @DeleteMapping("/users/favorite/groups")
    public SuccessResponse<String> deleteUserFavoriteGroups(@RequestBody FavoriteGroup favoriteGroup){
        userGroupService.deleteFavoriteGroup(favoriteGroup);
        return SuccessResponse.ok();
    }

}
