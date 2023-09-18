package com.backpacking.tourplan.tourplan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TourPlanExceptionCode {

    NO_SUCH_TOUR_PLAN("일치하는 여행 계획을 찾을 수 없습니다.");


    private final String description;
}
