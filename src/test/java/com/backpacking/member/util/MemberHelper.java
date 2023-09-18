package com.backpacking.member.util;

import com.backpacking.member.constants.VerifiedStatus;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.domain.vo.Address;
import com.backpacking.member.type.Roles;

import java.util.List;

public class MemberHelper {

    public Member generateMember(){
        return Member.builder()
                .memberId(1L)
                .email("lifi.jw@gmail.com")
                .password("1q!W231234")
                .address(new Address("korea", "seoul", "detail Address"))
                .phoneNumber("010-111-1111")
                .verifiedStatus(VerifiedStatus.NOT_VERIFIED)
                .authenticationCode("123ER21122")
                .roles(List.of(Roles.ROLE_TOURIST))
                .build();
    }

    public Member generateVerifiedMember(){
        return Member.builder()
                .memberId(1L)
                .email("gildong@gmail.com")
                .password("1q!W231234")
                .address(new Address("korea", "seoul", "detail Address"))
                .phoneNumber("010-111-1111")
                .verifiedStatus(VerifiedStatus.VERIFIED)
                .authenticationCode("123ER21122")
                .roles(List.of(Roles.ROLE_TOURIST))
                .build();
    }

    public Member generateMemberRegisteredGuideRole(){
        return Member.builder()
                .memberId(1L)
                .email("gildong@gmail.com")
                .password("1q!W231234")
                .address(new Address("korea", "seoul", "detail Address"))
                .phoneNumber("010-111-1111")
                .verifiedStatus(VerifiedStatus.VERIFIED)
                .authenticationCode("123ER21122")
                .roles(List.of(Roles.ROLE_TOURIST, Roles.ROLE_GUIDE))
                .build();
    }

}
