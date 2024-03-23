package com.univ.haksamo.domain.group.controller;

import com.univ.haksamo.domain.group.controller.res.FavoriteGroup;
import com.univ.haksamo.domain.group.controller.res.GroupsInUnivDto;
import com.univ.haksamo.domain.group.dto.GroupInfoDto;
import com.univ.haksamo.domain.group.service.GroupReadService;
import com.univ.haksamo.domain.group.service.GroupWriteService;
import com.univ.haksamo.domain.group.service.res.UserGroupsInfoDto;
import com.univ.haksamo.global.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.user}")
public class GroupController {
    private final GroupReadService groupReadService;
    private final GroupWriteService groupWriteService;

    @GetMapping("/users/{userId}/groups")
    public SuccessResponse<UserGroupsInfoDto> userGroupList(@PathVariable Long userId){
        UserGroupsInfoDto userGroups = groupReadService.findUserGroups(userId);
        return new SuccessResponse<>(userGroups);
    }


    @GetMapping("/universities/{universityId}/groups")
    public SuccessResponse<GroupsInUnivDto>groupsInUniv(@PathVariable Long universityId){
        return new SuccessResponse<>(groupReadService.findGroupsInUniv(universityId));
    }

    @PostMapping("/users/favorite/groups")
    public void userFavoriteGroups(@RequestBody List<FavoriteGroup> favoriteGroups){
        groupWriteService.saveFavoriteGroups(favoriteGroups);
    }

}
