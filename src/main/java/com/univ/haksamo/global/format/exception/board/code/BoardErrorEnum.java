package com.univ.haksamo.global.format.exception.board.code;

import com.univ.haksamo.global.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardErrorEnum implements ErrorEnumCode {
    NOT_FOUND_BOARD("B001", "게시글을 찾을수 없습니다.");
    private final String code;
    private final String message;
}
