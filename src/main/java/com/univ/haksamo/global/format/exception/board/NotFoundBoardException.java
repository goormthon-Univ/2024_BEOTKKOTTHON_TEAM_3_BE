package com.univ.haksamo.global.format.exception.board;

import com.univ.haksamo.global.format.exception.ApplicationRunException;
import com.univ.haksamo.global.format.exception.ErrorEnumCode;

import static com.univ.haksamo.global.format.exception.board.code.BoardErrorEnum.NOT_FOUND_BOARD;

public class NotFoundBoardException extends ApplicationRunException {
    private static ErrorEnumCode CODE = NOT_FOUND_BOARD;

    public NotFoundBoardException() {
        this(CODE);
    }
    public NotFoundBoardException(ErrorEnumCode code) {
        super(code);
    }
}
