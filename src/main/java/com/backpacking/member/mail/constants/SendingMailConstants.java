package com.backpacking.member.mail.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
public enum SendingMailConstants {

    SUBJECT("[해외 배낭 여행 매칭 서비스]: 회원 가입 확인"),
    CONTENT("안녕하세요!\n 해외 배낭 여행 서비스에 가입하기 위한 아래 인증 링크를 입력해주세요.\n"),
    LINK("인증 링크: http://localhost:8080/member/verify?"),
    EMAIL_QUERY("email="),
    CODE_QUERY("&code=");

    private final String value;

}
