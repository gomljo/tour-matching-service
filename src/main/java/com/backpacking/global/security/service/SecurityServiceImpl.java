package com.backpacking.global.security.service;

import com.backpacking.global.security.model.SecuredUser;
import com.backpacking.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.backpacking.global.security.exception.security.SecurityExceptionCode.NO_SUCH_MEMBER;


@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final MemberRepository memberRepository;

    @Override
    public SecuredUser loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("회원 정보 로딩 시작");

        return new SecuredUser(memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(NO_SUCH_MEMBER.getDescription()))
                );
    }

}
