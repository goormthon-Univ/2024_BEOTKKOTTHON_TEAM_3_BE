package com.univ.haksamo.domain.group.service.res;

import com.univ.haksamo.domain.group.dto.GroupInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserGroupsInfoDto {
    private List<GroupInfoDto> groupInfos;
}
