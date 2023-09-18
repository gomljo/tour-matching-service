package com.backpacking.reservation.domain.model;

import com.backpacking.global.jpa.vo.Audit;
import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.member.domain.model.Member;
import com.backpacking.reservation.domain.type.ReservationStatus;
import com.backpacking.tourplan.tourplan.domain.model.TourPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static com.backpacking.reservation.domain.type.ReservationStatus.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Reservation extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;

    private int numberOfParty;
    private ReservationStatus reservationStatus;

    @OneToOne
    private TourPlan tourPlan;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member tourist;

    @ManyToOne
    @JoinColumn(name = "guide_info_id")
    private GuideInfo guideInfo;


    public void approveReservation() {
        if (this.reservationStatus == HOLD) {
            this.reservationStatus = APPROVAL;
        }
    }

    public void denyReservation() {
        if (this.reservationStatus == HOLD) {
            this.reservationStatus = DENIAL;
        }
    }

    public void cancelReservation() {
        if (this.reservationStatus == HOLD || this.reservationStatus == APPROVAL){
            this.reservationStatus = CANCEL;
        }
    }
}
