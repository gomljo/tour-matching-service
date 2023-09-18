package com.backpacking.global.tour.plan.service;

import com.backpacking.global.tour.plan.dto.TourPlanDto;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.service.MemberService;
import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import com.backpacking.tourplan.tourplan.service.TourPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourPlanComponentService {
    private final TourPlanService tourPlanService;
    private final MemberService memberService;

    @Transactional
    @PreAuthorize("hasRole('ROLE_TOURIST') and isAuthenticated()")
    public TourPlan generatePlan(TourPlanDto.Request request) {

        Member tourist = memberService.findMemberBy(request.getTouristEmail());
        return tourPlanService.generatePlan(TourPlan.builder()
                .tourDate(request.getTourDate())
                .tourLocation(request.getTourLocation())
                .dailyPlans(request.getDailyPlans())
                .member(tourist)
                .build());
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_TOURIST') and isAuthenticated()")
    public List<TourPlan> findTourPlanList(String email, Pageable pageable){

        Member member = memberService.findMemberBy(email);

        return tourPlanService.findTourPlansBy(member.getMemberId(), pageable);

    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_TOURIST') and isAuthenticated()")
    public TourPlan findTourPlan(Long tourPlanId){
        return tourPlanService.findTourPlan(tourPlanId);
    }




}
