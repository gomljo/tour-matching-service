package com.backpacking.global.tour.plan.dto;

import com.backpacking.tourplan.dailyPlan.domain.DailyPlan;
import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import com.backpacking.tourplan.tourplan.domain.vo.TourDate;
import com.backpacking.tourplan.tourplan.domain.vo.TourLocation;
import com.backpacking.tourplan.tourplan.validation.DailyPlan.ValidDailyPlan;
import com.backpacking.tourplan.tourplan.validation.date.ValidTourDate;
import com.backpacking.tourplan.tourplan.validation.location.ValidTourLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.List;

public class TourPlanDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        @Email
        private String touristEmail;
        @ValidTourLocation
        private TourLocation tourLocation;
        @ValidTourDate
        private TourDate tourDate;
        @ValidDailyPlan
        private List<DailyPlan> dailyPlans;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long tourPlanId;
        private String touristEmail;
        private TourLocation tourLocation;
        private TourDate tourDate;
        private List<DailyPlan> dailyPlans;

        public static Response from(TourPlan tourPlan) {
            return Response.builder()
                    .tourPlanId(tourPlan.getTourPlanId())
                    .touristEmail(tourPlan.getMember().getEmail())
                    .tourLocation(tourPlan.getTourLocation())
                    .tourDate(tourPlan.getTourDate())
                    .dailyPlans(tourPlan.getDailyPlans())
                    .build();
        }

    }
}
