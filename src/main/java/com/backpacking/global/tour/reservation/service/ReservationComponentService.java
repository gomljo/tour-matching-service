package com.backpacking.global.tour.reservation.service;

import com.backpacking.global.tour.reservation.dto.ReservationDto;
import com.backpacking.global.security.exception.security.SecurityException;
import com.backpacking.global.security.util.AuthenticationUtil;
import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.guide.guide.service.GuideInfoService;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.service.MemberService;
import com.backpacking.reservation.domain.model.Reservation;
import com.backpacking.reservation.domain.type.ReservationStatus;
import com.backpacking.reservation.service.ReservationService;
import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import com.backpacking.tourplan.tourplan.service.TourPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.backpacking.global.security.exception.security.SecurityExceptionCode.INVALID_ACCESS;

@Service
@RequiredArgsConstructor
public class ReservationComponentService {

    private final MemberService memberService;
    private final GuideInfoService guideInfoService;
    private final ReservationService reservationService;
    private final TourPlanService tourPlanService;
    private final AuthenticationUtil authenticationUtil;

    @Transactional
    @PreAuthorize("hasRole('ROLE_TOURIST') and isAuthenticated()")
    public Reservation reserve(ReservationDto.Request request) {
        if(authenticationUtil.isInvalidAccess(request.getUserEmail())){
            throw new SecurityException(INVALID_ACCESS);
        }
        Member member = memberService.findMemberBy(request.getUserEmail());
        GuideInfo guideInfo = guideInfoService.findGuideInfo(request.getGuideInfoId());
        TourPlan tourPlan = tourPlanService.findTourPlan(request.getTourPlanId());

        return reservationService.reserve(Reservation.builder()
                                                    .reservationStatus(ReservationStatus.HOLD)
                                                    .numberOfParty(request.getNumberOfParty())
                                                    .tourist(member)
                                                    .guideInfo(guideInfo)
                                                    .tourPlan(tourPlan)
                                                    .build());


    }


}
