package com.univ.haksamo.global.format.exception.user.code;

import com.univ.haksamo.global.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorEnum implements ErrorEnumCode {
    NOT_FOUND_USER("U001", "사용자를 찾지 못했습니다");
    private final String code;
    private final String message;
}
