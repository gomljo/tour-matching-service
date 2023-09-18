package com.backpacking.global.security.provider.auth;

import com.backpacking.global.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider {

    private final SecurityService securityService;

    @Transactional
    public Authentication getAuthentication(String email){
        UserDetails userDetails = securityService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
