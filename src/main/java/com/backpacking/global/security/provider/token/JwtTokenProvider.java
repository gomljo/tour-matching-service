package com.backpacking.global.security.provider.token;

import com.backpacking.global.redis.service.RedisService;
import com.backpacking.global.security.dto.TokenDto;
import com.backpacking.global.security.exception.jwt.CustomJwtException;
import com.backpacking.global.security.model.SecuredUser;
import com.backpacking.global.security.provider.token.constants.TokenExpiredConstant;
import com.backpacking.global.security.provider.token.dto.CreateTokenDto;
import com.backpacking.member.type.Roles;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.backpacking.global.security.exception.jwt.CustomJwtExceptionCode.*;
import static com.backpacking.global.security.provider.token.constants.TokenType.ACCESS_TOKEN;
import static com.backpacking.global.security.provider.token.constants.TokenType.REFRESH_TOKEN;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String KEY_ROLES = "roles";
    private static final String TOKEN_TYPE = "token_type";

    private final TokenExpiredConstant tokenExpiredConstant;
    private final RedisService redisService;

    @Value("${spring.jwt.access.secret}")
    private String accessSecretKey;

    public String createAccessToken(String email, List<String> roles) {
        return setToken(CreateTokenDto.builder()
                .email(email)
                .roles(roles)
                .keyRoles(KEY_ROLES)
                .keyTokenType(TOKEN_TYPE)
                .tokenType(ACCESS_TOKEN)
                .tokenExpiredTime(tokenExpiredConstant.getAccessTokenExpiredTime())
                .secretKey(accessSecretKey)
                .build());
    }

    public String createRefreshToken(String email, List<String> roles) {
        return setToken(CreateTokenDto.builder()
                .email(email)
                .roles(roles)
                .keyRoles(KEY_ROLES)
                .keyTokenType(TOKEN_TYPE)
                .tokenType(REFRESH_TOKEN)
                .tokenExpiredTime(tokenExpiredConstant.getRefreshTokenExpiredTime())
                .secretKey(accessSecretKey)
                .build());
    }

    public TokenDto generateToken(SecuredUser securedUser) {

        String userEmail = securedUser.getEmail();
        List<String> roles = this.transformRoleTypeFrom(securedUser.getRoles());

        log.info("여행객 접근 토큰 발행");
        String accessToken = createAccessToken(userEmail, roles);
        String refreshToken = createRefreshToken(userEmail, roles);

        log.info("refresh token 저장");
        redisService.setRefreshTokenTimeout(userEmail, refreshToken, tokenExpiredConstant.getRefreshTokenExpiredTime());

        return new TokenDto(accessToken, refreshToken);
    }

    private List<String> transformRoleTypeFrom(List<Roles> roles) {
        return roles.stream().map(String::valueOf).collect(Collectors.toList());
    }

    public TokenDto regenerateByRefreshToken(SecuredUser securedUser, TokenDto tokenDto) {
        log.info("jwt 토큰 재발급 시작");
        Claims accessTokenClaims = this.parseClaims(tokenDto.getAccessToken());

        if(!isTokenExpired(accessTokenClaims)){
            return tokenDto;
        }

        if (!redisService.existsRefreshToken(securedUser.getEmail())) {
            log.error("refresh token을 찾을 수 없습니다!");
            throw new CustomJwtException(REFRESH_TOKEN_NOT_FOUND);
        }

        if(!isValidRefreshToken(securedUser.getEmail(), tokenDto.getRefreshToken())){
            log.error("refresh token이 일치하지 않습니다!");
            throw new CustomJwtException(INVALID_REFRESH_TOKEN);
        }

        redisService.deleteRefreshToken(securedUser.getEmail());

        return this.generateToken(securedUser);
    }

    public boolean isValidRefreshToken(String email, String refreshToken){
        String savedRefreshToken = redisService.getRefreshToken(email);
        log.info(savedRefreshToken);
        log.info(refreshToken);
        return savedRefreshToken.equals(refreshToken);
    }

    public void deleteRefreshToken(String email) {
        if (redisService.existsRefreshToken(email)) {
            redisService.deleteRefreshToken(email);
        }
    }

    public String setToken(CreateTokenDto createTokenDto) {
        Claims claims = Jwts.claims().setSubject(createTokenDto.getEmail());
        claims.put(createTokenDto.getKeyRoles(), createTokenDto.getRoles());
        claims.put(createTokenDto.getKeyTokenType(), createTokenDto.getTokenType());

        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + createTokenDto.getTokenExpiredTime());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, createTokenDto.getSecretKey())
                .compact();
    }


    public String getUserEmail(String token) {
        return this.parseClaims(token).getSubject();
    }

    public void validateToken(String accessToken, String refreshToken) {
        if (!StringUtils.hasText(accessToken)) {
            throw new CustomJwtException(MALFORMED_JWT_REQUEST);
        }
        Claims claims = this.parseClaims(accessToken);

        if(isTokenExpired(claims)){
            if(!this.existRefreshToken(refreshToken)){
                throw new CustomJwtException(ACCESS_TOKEN_EXPIRED);
            }

            Claims refreshClaims = this.parseClaims(refreshToken);

            if(this.isTokenExpired(refreshClaims)){
                throw new CustomJwtException(ALL_TOKEN_EXPIRED);
            }
        }
    }

    private boolean existRefreshToken(String refreshToken){
        return StringUtils.hasText(refreshToken);
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(this.accessSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException expiredJwtException) {
            return expiredJwtException.getClaims();
        } catch (MalformedJwtException malformedJwtException) {
            throw new MalformedJwtException(MALFORMED_JWT.getDescription());
        } catch (SignatureException signatureException) {
            throw new SignatureException(INVALID_SIGNATURE.getDescription());
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new UnsupportedJwtException(INVALID_JWT_COMPONENT.getDescription());
        }
    }
}