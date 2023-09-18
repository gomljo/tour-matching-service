package com.backpacking.tourplan.tourplan.exception;

import com.backpacking.global.exception.CustomException;

public class TourPlanException extends CustomException {

    private final String errorCode;
    private final String description;

    public TourPlanException(TourPlanExceptionCode tourPlanExceptionCode) {
        this.errorCode = tourPlanExceptionCode.name();
        this.description = tourPlanExceptionCode.getDescription();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }
}
