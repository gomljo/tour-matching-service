package com.backpacking.tourplan.dailyPlan.domain;

import com.backpacking.tourplan.hourlyPlan.domain.HourlyPlan;
import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_plan_id")
    private Long dailyPlanId;

    @OneToMany(mappedBy = "dailyPlan",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<HourlyPlan> hourlyPlan = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tour_plan_id")
    private TourPlan tourPlan;
}
