package com.univ.haksamo.domain.bookmark.dto;

import com.univ.haksamo.domain.board.service.res.BoardDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ScrapBoardsDto {
    List<BoardDto> scrapBoards;
}
