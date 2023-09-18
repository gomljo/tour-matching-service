package com.backpacking.tourplan.hourlyPlan.repository;

import com.backpacking.tourplan.hourlyPlan.domain.HourlyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourlyPlanRepository extends JpaRepository<HourlyPlan, Long> {
}
