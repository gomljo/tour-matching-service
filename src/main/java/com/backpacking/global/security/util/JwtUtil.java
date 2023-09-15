package com.backpacking.global.security.util;

import com.backpacking.global.security.exception.jwt.CustomJwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import static com.backpacking.global.security.constants.AuthorizationConstants.TOKEN_HEADER;
import static com.backpacking.global.security.constants.AuthorizationConstants.TOKEN_PREFIX;
import static com.backpacking.global.security.exception.jwt.CustomJwtExceptionCode.EMPTY_TOKEN;
import static com.backpacking.global.security.exception.jwt.CustomJwtExceptionCode.MALFORMED_JWT_REQUEST;

@Component
public class JwtUtil {

    public String extractTokenFrom(String token, String prefix){

        if (ObjectUtils.isEmpty(token)) {
            throw new CustomJwtException(EMPTY_TOKEN);
        }

        if (!token.startsWith(TOKEN_PREFIX)) {
            throw new CustomJwtException(MALFORMED_JWT_REQUEST);
        }

        return token.substring(prefix.length()).trim();
    }

}
