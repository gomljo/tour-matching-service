package com.backpacking.guide.guide.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuideInfoExceptionCode {

    ALREADY_REGISTERED_GUIDE_INFO("이미 가이드 정보를 등록하셨습니다"),
    GUIDE_INFO_NOT_FOUND("가이드 정보를 찾을 수 없습니다."),
    NO_SUCH_GUIDE("가이드를 찾을 수 없습니다."),
    INVALID_ACCESS("잘못된 접근입니다.");

    private final String description;

}
