package com.univ.haksamo.domain.board.controller.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class WriteBoardRequestDto {

    private Long keywordId;
    @NotBlank(message = "제목을 채워주세요")
    private String title;
    @NotBlank(message = "글을 채워주세요.")
    private String contents;

}
