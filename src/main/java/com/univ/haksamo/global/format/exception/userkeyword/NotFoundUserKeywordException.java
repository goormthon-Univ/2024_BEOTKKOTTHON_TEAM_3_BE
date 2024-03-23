package com.univ.haksamo.global.format.exception.userkeyword;

import com.univ.haksamo.global.format.exception.ApplicationRunException;
import com.univ.haksamo.global.format.exception.ErrorEnumCode;

import static com.univ.haksamo.global.format.exception.userkeyword.code.UserKeywordEnum.NOT_FOUND_USER_KEYWORD;

public class NotFoundUserKeywordException extends ApplicationRunException {
    private static ErrorEnumCode CODE = NOT_FOUND_USER_KEYWORD;

    public NotFoundUserKeywordException() {
        this(CODE);
    }
    public NotFoundUserKeywordException(ErrorEnumCode code) {
        super(code);
    }
}
