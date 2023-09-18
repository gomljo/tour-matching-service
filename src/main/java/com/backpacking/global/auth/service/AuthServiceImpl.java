package com.backpacking.global.auth.service;

import com.backpacking.global.auth.dto.LoginDto;
import com.backpacking.global.security.dto.TokenDto;
import com.backpacking.global.security.exception.security.SecurityException;
import com.backpacking.global.security.exception.security.SecurityExceptionCode;
import com.backpacking.global.security.model.SecuredUser;
import com.backpacking.global.security.provider.token.JwtTokenProvider;
import com.backpacking.global.security.service.SecurityService;
import com.backpacking.global.security.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordUtil passwordUtil;
    private final SecurityService securityService;
    
    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_TOURIST')")
    public TokenDto reissue(String email, TokenDto tokenDto) {
        log.info("재발행 서비스 시작");
        SecuredUser securedUser = securityService.loadUserByUsername(email);
        log.info("사용자 정보 조회 성공");
        return this.jwtTokenProvider.regenerateByRefreshToken(securedUser,
                tokenDto);
    }


    @Override
    public TokenDto login(LoginDto.Request request) {
        log.info("여행객 회원 비밀번호 검증 시작");

        log.info("여행객 회원 조회 시작");
        SecuredUser securedUser = securityService.loadUserByUsername(request.getEmail());
        log.info("여행객 회원 조회 성공");

        if (!this.passwordUtil.validatePassword(request.getPassword(), securedUser.getPassword())) {
            throw new SecurityException(SecurityExceptionCode.PASSWORD_NOT_MATCHED);
        }

        log.info("여행객 회원 비밀번호 검증 성공");

        return this.jwtTokenProvider.generateToken(securedUser);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_TOURIST')")
    public String logout(String email) {
        log.info("로그아웃");
        this.jwtTokenProvider.deleteRefreshToken(email);
        log.info("로그아웃 정상 처리");
        return email;
    }


}
