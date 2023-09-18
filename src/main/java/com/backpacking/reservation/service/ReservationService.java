package com.backpacking.reservation.service;

import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.reservation.domain.model.Reservation;
import com.backpacking.tourplan.tourplan.domain.vo.TourDate;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    Reservation reserve(Reservation reservation);

    Reservation approve(Reservation reservation);

    Reservation deny(Reservation reservation);

    Reservation cancel(Reservation reservation);
    Reservation findReservationBy(Long guideId);

    List<Reservation> findPastReservations(String userEmail, LocalDateTime currentDate);

    List<GuideInfo> findGuideInfoList(TourDate tourDate, Pageable pageable);
}
