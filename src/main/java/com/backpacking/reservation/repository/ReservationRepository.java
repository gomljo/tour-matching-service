package com.backpacking.reservation.repository;

import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.reservation.domain.model.Reservation;
import com.backpacking.reservation.domain.type.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("select r " +
            "from Reservation r " +
            "inner join r.tourist " +
            "where r.modifiedAt <= :today " +
            "and r.tourist.email = :userEmail " +
            "and r.reservationStatus = :reservationStatus")
    List<Reservation> findReservationList(@Param("today") LocalDateTime today,
                                          @Param("userEmail") String userEmail,
                                          @Param("reservationStatus") ReservationStatus reservationStatus);

    @Query("select r " +
            "from Reservation r " +
            "inner join r.guideInfo " +
            "where r.guideInfo.id = :guideId")
    Optional<Reservation> findReservationByGuideId(@Param("guideId") Long guideId);

    @Query("select distinct r.guideInfo " +
            "from Reservation r " +
            "inner join r.tourPlan " +
            "where r.tourPlan.tourDate.endDate < :startDate " +
            "or r.tourPlan.tourDate.startDate > :endDate ")
    Page<GuideInfo> findGuideInfoList(Pageable pageable,
                                      @Param("startDate")LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);

    @Query("select r.reservationId " +
            "from Reservation r " +
            "where  r.tourPlan.tourDate.startDate=:startDate " +
            "and r.tourPlan.tourDate.endDate=:endDate")
    Long findFirstByDate(@Param("startDate") LocalDateTime startDate,
                              @Param("endDate") LocalDateTime endDate);
}
