package com.univ.haksamo.global.format.exception.userkeyword.code;

import com.univ.haksamo.global.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserKeywordEnum implements ErrorEnumCode {
    NOT_FOUND_USER_KEYWORD("UKE001", "일치하는 즐겨찾기된 키워드가 없습니다.");
    private final String code;
    private final String message;
}
