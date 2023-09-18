package com.backpacking.global.tour.plan.controller;

import com.backpacking.global.tour.plan.dto.TourPlanDto;
import com.backpacking.global.tour.plan.dto.TourPlanListDto;
import com.backpacking.global.tour.plan.service.TourPlanComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member/tour/plan")
@RequiredArgsConstructor
public class TourPlanController {

    private final TourPlanComponentService tourPlanComponentService;

    @PostMapping
    public TourPlanDto.Response register(@RequestBody TourPlanDto.Request request){
        return TourPlanDto.Response.from(tourPlanComponentService.generatePlan(request));
    }

    @GetMapping("/{userEmail}")
    public TourPlanListDto.Response findTourPlanList(@PathVariable String userEmail,
                                                     Pageable pageable){
        return TourPlanListDto.Response.from(tourPlanComponentService.findTourPlanList(userEmail, pageable));
    }

    @GetMapping("/{tourPlanId}")
    public TourPlanDto.Response findTourPlan(@PathVariable Long tourPlanId){
        return TourPlanDto.Response.from(tourPlanComponentService.findTourPlan(tourPlanId));
    }



}
