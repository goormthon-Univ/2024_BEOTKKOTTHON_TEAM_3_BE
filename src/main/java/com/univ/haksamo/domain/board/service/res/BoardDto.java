package com.univ.haksamo.domain.board.service.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class BoardDto {
    private Long boardId;
    private String title;
    private String contents;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Seoul")
    private LocalDateTime createdDateTime;
    private List<String> imageUrl;
}
