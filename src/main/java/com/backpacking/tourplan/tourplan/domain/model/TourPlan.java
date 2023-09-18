package com.backpacking.tourplan.tourplan.domain.model;

import com.backpacking.global.jpa.vo.Audit;
import com.backpacking.member.domain.model.Member;
import com.backpacking.tourplan.dailyPlan.domain.DailyPlan;
import com.backpacking.tourplan.tourplan.domain.vo.TourDate;
import com.backpacking.tourplan.tourplan.domain.vo.TourLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TourPlan extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_plan_id")
    private Long tourPlanId;

    @Embedded
    private TourLocation tourLocation;

    @Embedded
    private TourDate tourDate;

    @OneToMany(mappedBy = "tourPlan",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @Builder.Default
    private List<DailyPlan> dailyPlans = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void update(TourPlan plan){

        this.tourLocation = plan.tourLocation;
        this.tourDate = plan.tourDate;
        this.dailyPlans = plan.dailyPlans;
    }


}
