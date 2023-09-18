package com.backpacking.tourplan.tourplan.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourLocation {
    @NotBlank
    private String nation;
    @NotBlank
    private String city;
}
