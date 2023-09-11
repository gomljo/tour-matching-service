package com.backpacking.member.service;

import com.backpacking.member.domain.model.Member;
import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.dto.VerificationDto;
import com.backpacking.member.exception.MemberException;
import com.backpacking.member.repository.MemberRepository;
import com.backpacking.member.type.Roles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.backpacking.member.constants.VerifiedStatus.NOT_VERIFIED;
import static com.backpacking.member.exception.MemberExceptionCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member register(MemberRegisterDto.Request request, String authenticationCode) {
        log.info("여행객 회원 중복 여부 확인");
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(ALREADY_REGISTERED);
        }

        return memberRepository.save(Member.builder()
                .email(request.getEmail())
                .address(request.getAddress())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .verifiedStatus(NOT_VERIFIED)
                .authenticationCode(authenticationCode)
                .roles(List.of(Roles.ROLE_TOURIST.toString()))
                .build());
    }


    @Override
    @Transactional
    public Member verifyAuthenticationCode(VerificationDto.Request request) {

        Member member = memberRepository.findByEmail(request.getUserEmail())
                .orElseThrow(() -> new MemberException(NO_SUCH_MEMBER));

        if (!member.isSameAuthCode(request.getAuthenticationCode())) {
            throw new MemberException(INVALID_AUTHENTICATION_CODE);
        }

        return member;
    }

    @Override
    public Member updateVerifiedStatus(Member member) {
        member.updateVerifiedStatus();

        return memberRepository.save(member);
    }

}
