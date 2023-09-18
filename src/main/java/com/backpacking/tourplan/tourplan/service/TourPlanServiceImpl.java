package com.backpacking.tourplan.tourplan.service;

import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import com.backpacking.tourplan.tourplan.exception.TourPlanException;
import com.backpacking.tourplan.tourplan.repository.TourPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.backpacking.tourplan.tourplan.exception.TourPlanExceptionCode.NO_SUCH_TOUR_PLAN;

@Service
@RequiredArgsConstructor
public class TourPlanServiceImpl implements TourPlanService {

    private final TourPlanRepository tourPlanRepository;

    @Override
    public TourPlan generatePlan(TourPlan tourPlan) {
        return tourPlanRepository.save(tourPlan);
    }

    @Override
    public TourPlan findTourPlan(Long tourPlanId) {
        return tourPlanRepository.findTourPlanByTourPlanId(tourPlanId)
                .orElseThrow(() -> new TourPlanException(NO_SUCH_TOUR_PLAN));
    }

    @Override
    public TourPlan updatePlan(TourPlan tourPlan, TourPlan updatedTourPlan) {
        tourPlan.update(updatedTourPlan);
        return tourPlanRepository.save(tourPlan);
    }

    @Override
    public List<TourPlan> findTourPlansBy(Long memberId, Pageable pageable) {
        return tourPlanRepository.findTourPlanBy(LocalDateTime.now(), memberId, pageable)
                .toList();
    }
}
