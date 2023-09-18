package com.backpacking.global.tour.plan.dto;

import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class TourPlanListDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private List<TourPlanDto.Response> tourPlanList;

        public static Response from(List<TourPlan> tourPlanPage){
            return new Response(tourPlanPage.stream()
                    .map(TourPlanDto.Response::from)
                    .collect(Collectors.toList()));
        }

    }


}
