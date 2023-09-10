package com.backpacking.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(nullable = false)
    private String nation;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String detailAddress;
}
