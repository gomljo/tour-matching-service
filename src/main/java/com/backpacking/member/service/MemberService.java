package com.backpacking.member.service;

import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.dto.VerificationDto;
import com.backpacking.member.mail.dto.MailDto;

public interface MemberService {

    Member register(MemberRegisterDto.Request request, String authenticationCode);

    Member verify(VerificationDto.Request request);
}
