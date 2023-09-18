package com.backpacking.guide.guide.domain.model;

import com.backpacking.global.jpa.vo.Audit;
import com.backpacking.member.domain.model.Member;
import com.backpacking.reservation.domain.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class GuideInfo extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guide_info_id")
    private long id;

    @Min(0)
    @Builder.Default
    private int cumulatedReservation = 0;

    @Max(5)
    @Min(0)
    @Builder.Default
    private double starRating = 0.0;

    @NotBlank
    private String introduce;

    @ElementCollection
    @Builder.Default
    private Set<String> mainThemes = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "guideInfo", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Reservation> reservationList = new ArrayList<>();

    public void updateIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void updateMainThemes(Set<String> mainThemes) {
        this.mainThemes = mainThemes;
    }

    public void updateStarRating(double starRating) {
        this.starRating = starRating;
    }

    public void increaseCumulatedReservation() {
        this.cumulatedReservation++;
    }

    public void decreaseCumulatedReservation() {
        this.cumulatedReservation--;
    }


}
