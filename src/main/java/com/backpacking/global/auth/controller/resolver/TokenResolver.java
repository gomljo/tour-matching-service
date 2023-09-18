package com.backpacking.global.auth.controller.resolver;

import com.backpacking.global.security.constants.AuthorizationConstants;
import com.backpacking.global.security.dto.TokenDto;
import com.backpacking.global.security.exception.jwt.CustomJwtException;
import com.backpacking.global.security.exception.jwt.CustomJwtExceptionCode;
import com.backpacking.global.security.util.JwtUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.backpacking.global.security.constants.AuthorizationConstants.*;
import static com.backpacking.global.security.exception.jwt.CustomJwtExceptionCode.EMPTY_TOKEN;

@Component
@RequiredArgsConstructor
public class TokenResolver implements HandlerMethodArgumentResolver {

    private final JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(TokenDto.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String accessTokenValue = request.getHeader(TOKEN_HEADER);
        String refreshTokenValue = request.getHeader(REFRESH_HEADER);
        if(!StringUtils.hasText(accessTokenValue) || !StringUtils.hasText(refreshTokenValue)){
            throw new CustomJwtException(EMPTY_TOKEN);
        }
        String accessToken = jwtUtil.extractTokenFrom(accessTokenValue, TOKEN_PREFIX);
        String refreshToken = jwtUtil.extractTokenFrom(refreshTokenValue, TOKEN_PREFIX);

        return new TokenDto(accessToken, refreshToken);
    }
}
