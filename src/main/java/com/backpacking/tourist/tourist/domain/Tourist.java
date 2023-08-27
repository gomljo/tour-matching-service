package com.backpacking.tourist.tourist.domain;

import com.backpacking.global.member.vo.BaseMember;
import com.backpacking.tourist.log.domain.TouristLog;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("tourist")
@PrimaryKeyJoinColumn(name = "tourist_id")
public class Tourist extends BaseMember{

    @OneToMany(mappedBy = "tourist", fetch = FetchType.LAZY)
    @JsonManagedReference
    @Builder.Default
    private List<TouristLog> touristLog = new ArrayList<>();
}
