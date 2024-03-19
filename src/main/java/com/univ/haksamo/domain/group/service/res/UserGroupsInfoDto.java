package com.univ.haksamo.domain.group.service.res;

import com.univ.haksamo.domain.group.dto.GroupInfoDto;
import lombok.Builder;

import java.util.List;

@Builder
public class UserGroupsInfoDto {
    private List<GroupInfoDto> groupInfos;
}
