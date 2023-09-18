package com.backpacking.tourplan.tourplan.validation.DailyPlan;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DailyPlanExceptionCode {
    HOURLY_PLAN_DESTINATION_EMPTY("시간 계획의 목적지가 없습니다."),
    HOURLY_PLAN_OVER_TIME_LIMIT("시간 계획의 합이 24시간을 초과했습니다.");
    private final String description;

}
