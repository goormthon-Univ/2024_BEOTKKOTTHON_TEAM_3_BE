package com.univ.haksamo.domain.group.service;

import com.univ.haksamo.domain.bookmark.entity.UserGroup;
import com.univ.haksamo.domain.bookmark.repository.UserGroupRespository;
import com.univ.haksamo.domain.group.dto.GroupInfoDto;
import com.univ.haksamo.domain.group.entity.Group;
import com.univ.haksamo.domain.group.service.res.UserGroupsInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupReadService {
    private final UserGroupRespository userGroupRespository;

    public UserGroupsInfoDto findUserGroups(Long userId) {
        List<UserGroup> userGroups = userGroupRespository.findByUserId(userId);
        List<GroupInfoDto> groupInfoList = new ArrayList<>();
        for (UserGroup userGroup : userGroups) {
            Group group = userGroup.getGroup();
            GroupInfoDto groupInfo = GroupInfoDto.builder()
                    .id(group.getId())
                    .name(group.getName())
                    .build();
            groupInfoList.add(groupInfo);
        }
        return UserGroupsInfoDto.builder()
                .groupInfos(groupInfoList)
                .build();
    }
}
