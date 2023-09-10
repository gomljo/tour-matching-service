package com.backpacking.member.mail.constants;

public final class SendingMailConstants {

    public static final String SUBJECT = "[해외 배낭 여행 매칭 서비스]: 회원 가입 확인";
    public static final String CONTENT = "안녕하세요!\n 해외 배낭 여행 서비스에 가입하기 위한 아래 인증 링크를 입력해주세요.\n";

    public static final String LINK = "인증 링크: ";
    public static final String LINK_END_POINT = "/member/verify";
    public static final String EMAIL_QUERY = "?email=";
    public static final String AUTH_CODE_QUERY = "&code=";

    private SendingMailConstants() {

    }

}
