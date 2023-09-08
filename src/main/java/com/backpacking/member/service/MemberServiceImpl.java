package com.backpacking.member.service;

import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.exception.MemberException;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.repository.MemberRepository;
import com.backpacking.member.type.Roles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.backpacking.member.exception.MemberExceptionCode.ALREADY_REGISTERED;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member register(MemberRegisterDto.Request request) {
        log.info("여행객 회원 중복 여부 확인");
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(ALREADY_REGISTERED);
        }

        return memberRepository.save(Member.builder()
                .email(request.getEmail())
                .address(request.getAddress())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .roles(List.of(Roles.ROLE_TOURIST.toString()))
                .build());
    }
}
