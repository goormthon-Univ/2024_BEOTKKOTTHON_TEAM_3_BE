package com.univ.haksamo.domain.bookmark.dto;

import lombok.Builder;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonCreator;

@Builder
@Getter
public class FavoriteGroupDto {

    private String name;

    @JsonCreator
    public FavoriteGroupDto(String name) {
        this.name = name;
    }

}
