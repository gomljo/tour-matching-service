package com.backpacking.tourist.tourist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TouristExceptionCode {

    NO_SUCH_TOURIST("여행객을 찾을 수 없습니다."),
    ALREADY_REGISTERED("이미 가입한 적이 있습니다.");

    private final String description;

}
