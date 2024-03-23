package com.univ.haksamo.domain.group.controller;

import com.univ.haksamo.domain.group.controller.res.FavoriteGroup;
import com.univ.haksamo.domain.group.controller.res.GroupsInUnivDto;
import com.univ.haksamo.domain.group.dto.GroupInfoDto;
import com.univ.haksamo.domain.group.service.GroupReadService;
import com.univ.haksamo.domain.group.service.GroupWriteService;
import com.univ.haksamo.domain.group.service.res.UserGroupsInfoDto;
import com.univ.haksamo.global.format.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "학과 관련 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.user}")
public class GroupController {
    private final GroupReadService groupReadService;
    private final GroupWriteService groupWriteService;

    @Operation(summary = "즐겨찾기한 학과 조회 api")
    @GetMapping("/users/{userId}/groups")
    public SuccessResponse<UserGroupsInfoDto> userGroupList(@PathVariable Long userId){
        UserGroupsInfoDto userGroups = groupReadService.findUserGroups(userId);
        return new SuccessResponse<>(userGroups);
    }


    @Operation(summary = "대학교에 속한 학과 조회 api")
    @GetMapping("/universities/{universityId}/groups")
    public SuccessResponse<GroupsInUnivDto>groupsInUniv(@PathVariable Long universityId){
        return new SuccessResponse<>(groupReadService.findGroupsInUniv(universityId));
    }

    @Operation(summary = "학과 즐겨찾기 추가 api")
    @PostMapping("/users/favorite/groups")
    public void userFavoriteGroups(@RequestBody List<FavoriteGroup> favoriteGroups){
        groupWriteService.saveFavoriteGroups(favoriteGroups);
    }

}
