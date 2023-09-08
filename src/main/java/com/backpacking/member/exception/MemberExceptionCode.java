package com.backpacking.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberExceptionCode {

    NO_SUCH_TOURIST("여행객을 찾을 수 없습니다."),
    ALREADY_REGISTERED("이미 가입한 적이 있습니다."),
    INVALID_AUTHENTICATION_CODE("인증 코드가 일치하지 않습니다.");

    private final String description;

}
