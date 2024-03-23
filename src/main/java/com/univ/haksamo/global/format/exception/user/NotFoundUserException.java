package com.univ.haksamo.global.format.exception.user;

import com.univ.haksamo.global.format.exception.ApplicationRunException;
import com.univ.haksamo.global.format.exception.ErrorEnumCode;

import static com.univ.haksamo.global.format.exception.user.code.UserErrorEnum.NOT_FOUND_USER;

public class NotFoundUserException extends ApplicationRunException {
    private static ErrorEnumCode CODE = NOT_FOUND_USER;

    public NotFoundUserException() {
        this(CODE);
    }
    public NotFoundUserException(ErrorEnumCode code) {
        super(code);
    }

}
