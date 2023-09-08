package com.backpacking.member.service;

import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.domain.model.Member;

public interface MemberService {

    Member register(MemberRegisterDto.Request request);
}
