package com.backpacking.tourplan.dailyPlan.repository;

import com.backpacking.tourplan.dailyPlan.domain.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {
}
