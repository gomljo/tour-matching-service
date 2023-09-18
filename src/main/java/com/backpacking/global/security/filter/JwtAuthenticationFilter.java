package com.backpacking.global.security.filter;

import com.backpacking.global.security.exception.jwt.CustomJwtException;
import com.backpacking.global.security.provider.auth.AuthenticationProvider;
import com.backpacking.global.security.provider.token.JwtTokenProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.backpacking.global.security.constants.AuthorizationConstants.*;
import static com.backpacking.global.security.exception.jwt.CustomJwtExceptionCode.EMPTY_TOKEN;
import static com.backpacking.global.security.exception.jwt.CustomJwtExceptionCode.MALFORMED_JWT_REQUEST;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final List<String> EXCLUDE_PATH = List.of("/member/join", "/auth/login", "/member/verify");
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationProvider authenticationProvider;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken="";
        String refreshToken="";

        if(!isExcludePathContains(request.getRequestURI())){
            accessToken = this.resolveTokenFromRequest(request, TOKEN_HEADER);
            if(StringUtils.hasText(request.getHeader(REFRESH_HEADER))){
                refreshToken = this.resolveTokenFromRequest(request, REFRESH_HEADER);
            }
            this.tokenProvider.validateToken(accessToken, refreshToken);
            this.setSecurityContext(accessToken);
        }
        filterChain.doFilter(request, response);
    }

    private boolean isExcludePathContains(String uri){
        return EXCLUDE_PATH.contains(uri);
    }

    private void setSecurityContext(String accessToken){
        String userEmail = this.tokenProvider.getUserEmail(accessToken);
        Authentication auth = this.authenticationProvider.getAuthentication(userEmail);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String resolveTokenFromRequest(@NonNull HttpServletRequest request, String tokenHeader) {
        String token = request.getHeader(tokenHeader);

        if (ObjectUtils.isEmpty(token)) {
            throw new CustomJwtException(EMPTY_TOKEN);
        }

        if (!token.startsWith(TOKEN_PREFIX)) {
            throw new CustomJwtException(MALFORMED_JWT_REQUEST);
        }

        return token.substring(TOKEN_PREFIX.length());
    }
}
