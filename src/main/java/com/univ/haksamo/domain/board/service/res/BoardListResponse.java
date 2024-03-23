package com.univ.haksamo.domain.board.service.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BoardListResponse {
    private List<BoardDto> boards;
}
