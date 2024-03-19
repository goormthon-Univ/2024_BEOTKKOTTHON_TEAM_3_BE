package com.univ.haksamo.domain.group.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GroupInfoDto {
    private String name;
    private Long id;
}
