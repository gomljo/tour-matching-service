package com.backpacking.tourplan.tourplan.repository;

import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TourPlanRepository extends JpaRepository<TourPlan, Long> {

    @Query("select t " +
            "from TourPlan t " +
            "inner join t.member " +
            "where t.member.memberId=:id ")
    Page<TourPlan> findTourPlanBy(@Param("currentDate") LocalDateTime currentDate,
                                  @Param("id") Long id,
                                  Pageable pageable);

    Optional<TourPlan> findTourPlanByTourPlanId(Long tourPlanId);

}
