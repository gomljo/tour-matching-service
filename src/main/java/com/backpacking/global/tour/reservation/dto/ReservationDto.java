package com.backpacking.global.tour.reservation.dto;

import com.backpacking.global.redis.service.lock.config.ReservationLockId;
import com.backpacking.reservation.domain.model.Reservation;
import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReservationDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request implements ReservationLockId {

        private String userEmail;
        private long guideInfoId;
        private long tourPlanId;
        private int numberOfParty;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private long reservationId;
        private TourPlan tourPlan;
        private int numberOfParty;
        private long memberId;
        private long guideInfoId;

        public static ReservationDto.Response from(Reservation reservation){
            return Response.builder()
                    .reservationId(reservation.getReservationId())
                    .tourPlan(reservation.getTourPlan())
                    .numberOfParty(reservation.getNumberOfParty())
                    .memberId(reservation.getTourist().getMemberId())
                    .guideInfoId(reservation.getGuideInfo().getId())
                    .build();
        }
    }


}
