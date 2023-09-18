package com.backpacking.tourplan.tourplan.service;

import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourPlanService {

    TourPlan generatePlan(TourPlan tourPlan);

    TourPlan updatePlan(TourPlan tourPlan, TourPlan updatedTourPlan);

    List<TourPlan> findTourPlansBy(Long memberId, Pageable pageable);

    TourPlan findTourPlan(Long tourPlanId);

}
