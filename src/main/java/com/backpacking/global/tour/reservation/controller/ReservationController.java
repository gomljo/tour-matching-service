package com.backpacking.global.tour.reservation.controller;

import com.backpacking.global.redis.service.lock.annotation.ReservationLock;
import com.backpacking.global.tour.reservation.dto.ReservationDto;
import com.backpacking.global.tour.reservation.service.ReservationComponentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member/tour/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationComponentService reservationComponentService;

    @PostMapping
    @ReservationLock
    public ReservationDto.Response reserve(ReservationDto.Request request){
        return ReservationDto.Response.from(reservationComponentService.reserve(request));
    }


}
