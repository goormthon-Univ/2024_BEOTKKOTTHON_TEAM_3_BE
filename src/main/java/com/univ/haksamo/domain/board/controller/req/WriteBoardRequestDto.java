package com.univ.haksamo.domain.board.controller.req;

import lombok.Getter;

@Getter
public class WriteBoardRequestDto {
    private Long keywordId;
    private String title;
    private String contents;
}
