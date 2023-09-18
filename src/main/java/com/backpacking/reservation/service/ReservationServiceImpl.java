package com.backpacking.reservation.service;

import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.reservation.domain.model.Reservation;
import com.backpacking.reservation.exception.ReservationException;
import com.backpacking.reservation.repository.ReservationRepository;
import com.backpacking.tourplan.tourplan.domain.vo.TourDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.backpacking.reservation.domain.type.ReservationStatus.APPROVAL;
import static com.backpacking.reservation.exception.ReservationExceptionCode.NO_SUCH_RESERVATION;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation reserve(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findReservationBy(Long guideId) {
        return reservationRepository.findReservationByGuideId(guideId)
                .orElseThrow(()->new ReservationException(NO_SUCH_RESERVATION));
    }

    @Override
    public Reservation approve(Reservation reservation) {
        reservation.approveReservation();
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation deny(Reservation reservation) {
        reservation.denyReservation();
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation cancel(Reservation reservation) {
        reservation.cancelReservation();
        return reservationRepository.save(reservation);
    }

    @Override
    public List<GuideInfo> findGuideInfoList(TourDate tourDate, Pageable pageable){
        return reservationRepository.findGuideInfoList(pageable, tourDate.getStartDate(), tourDate.getEndDate()).toList();
    }

    @Override
    public List<Reservation> findPastReservations(String userEmail, LocalDateTime currentDate) {
        return reservationRepository.findReservationList(currentDate, userEmail, APPROVAL);
    }

}

