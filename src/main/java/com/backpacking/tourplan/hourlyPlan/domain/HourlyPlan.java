package com.backpacking.tourplan.hourlyPlan.domain;

import com.backpacking.tourplan.dailyPlan.domain.DailyPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HourlyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hourlyPlanId;

    private String destination;
    private int expectedTourTime;

    @ManyToOne
    @JoinColumn(name = "daily_plan_id")
    private DailyPlan dailyPlan;
}
