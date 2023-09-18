package com.backpacking.member.service;

import com.backpacking.global.security.exception.security.SecurityException;
import com.backpacking.global.security.util.AuthenticationUtil;
import com.backpacking.global.security.util.PasswordUtil;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.dto.VerificationDto;
import com.backpacking.member.exception.MemberException;
import com.backpacking.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.backpacking.global.security.exception.security.SecurityExceptionCode.INVALID_ACCESS;
import static com.backpacking.member.constants.VerifiedStatus.NOT_VERIFIED;
import static com.backpacking.member.exception.MemberExceptionCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordUtil passwordUtil;
    private final AuthenticationUtil authenticationUtil;
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
                .password(passwordUtil.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .verifiedStatus(NOT_VERIFIED)
                .authenticationCode(authenticationCode)
                .roles(request.getRoles())
                .build());
    }


    @Override
    @Transactional
    public Member verifyAuthenticationCode(VerificationDto.Request request) {

        if(authenticationUtil.isInvalidAccess(request.getUserEmail())){
            throw new SecurityException(INVALID_ACCESS);
        }

        Member member = findMemberBy(request.getUserEmail());

        if (!member.isSameAuthCode(request.getAuthenticationCode())) {
            throw new MemberException(INVALID_AUTHENTICATION_CODE);
        }

        return member;
    }

    @Override
    @Transactional
    public Member updateVerifiedStatus(Member member) {

        if(authenticationUtil.isInvalidAccess(member.getEmail())){
            throw new SecurityException(INVALID_ACCESS);
        }

        member.updateVerifiedStatus();

        return memberRepository.save(member);
    }

    @Override
    public Member findMemberBy(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(NO_SUCH_MEMBER));
    }

    @Override
    public Member registerGuideRole(Member member){
        member.registerGuideRole();
        return memberRepository.save(member);
    }

    @Override
    public Member deleteGuideRole(Member member){
        member.deleteGuideRole();
        return memberRepository.save(member);
    }
}
