package com.backpacking.tourplan.tourplan.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourDate {
    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;
}
