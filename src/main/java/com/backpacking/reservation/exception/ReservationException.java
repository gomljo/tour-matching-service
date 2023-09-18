package com.backpacking.reservation.exception;

import com.backpacking.global.exception.CustomException;

public class ReservationException extends CustomException {

    private final String errorCode;
    private final String description;

    public ReservationException(ReservationExceptionCode reservationExceptionCode){
        this.errorCode = reservationExceptionCode.name();
        this.description= reservationExceptionCode.getDescription();
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
