package com.backpacking.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationExceptionCode {

    NO_SUCH_RESERVATION("예약을 찾을 수 없습니다."),
    RESERVATION_TRANSACTION_LOCK("요청하신 예약 시간이 이미 예약되었습니다.");

    private final String description;
}
