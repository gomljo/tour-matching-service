package com.backpacking.member.service;

import com.backpacking.member.domain.model.Member;
import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.dto.VerificationDto;

public interface MemberService {

    Member register(MemberRegisterDto.Request request, String authenticationCode);

    Member verifyAuthenticationCode(VerificationDto.Request request);

    Member updateVerifiedStatus(Member member);
    Member registerGuideRole(Member member);
    Member findMemberBy(String email);
    Member deleteGuideRole(Member member);
}
