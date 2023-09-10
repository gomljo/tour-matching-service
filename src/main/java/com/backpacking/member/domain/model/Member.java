package com.backpacking.member.domain.model;

import com.backpacking.global.jpa.vo.Audit;
import com.backpacking.member.constants.VerifiedStatus;
import com.backpacking.member.domain.vo.Address;
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
@AllArgsConstructor
@SuperBuilder
@Table(name = "member")
public class Member extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VerifiedStatus verifiedStatus;

    @Column(nullable = false, updatable = false)
    private String authenticationCode;

    @Embedded
    @Builder.Default
    private Address address = new Address();

    public void updateVerifiedStatus(){

        if(this.verifiedStatus==VerifiedStatus.NOT_VERIFIED){
            this.verifiedStatus = VerifiedStatus.VERIFIED;
        }
    }
}
