package com.univ.haksamo.domain.group.controller.res;

import com.univ.haksamo.domain.group.dto.GroupInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GroupsInUnivDto {
    private List<GroupInfoDto> groupsInUniv;
}
