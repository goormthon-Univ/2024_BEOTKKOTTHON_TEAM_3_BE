package com.univ.haksamo.domain.group.controller;

import com.univ.haksamo.domain.group.service.GroupReadService;
import com.univ.haksamo.domain.group.service.res.UserGroupsInfoDto;
import com.univ.haksamo.global.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix.user}")
public class GroupController {
    private final GroupReadService groupReadService;

    @GetMapping("/users/{userId}/groups")
    public SuccessResponse<UserGroupsInfoDto> userGroupList(@PathVariable Long userId){
        UserGroupsInfoDto userGroups = groupReadService.findUserGroups(userId);
        return new SuccessResponse<>(userGroups);
    }
}
