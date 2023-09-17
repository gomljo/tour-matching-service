package com.backpacking.global.security.exception.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SecurityExceptionCode {

    NO_SUCH_MEMBER("회원가입된 적 없는 회원입니다."),
    PASSWORD_NOT_MATCHED("비밀번호 또는 이메일이 일치하지 않습니다."),
    INVALID_ACCESS("허용되지 않는 접근입니다.");

    private final String description;

}
