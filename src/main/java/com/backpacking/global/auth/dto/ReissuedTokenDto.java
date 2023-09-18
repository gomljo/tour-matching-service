package com.backpacking.global.auth.dto;

import com.backpacking.global.security.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ReissuedTokenDto {

    @Getter
    @AllArgsConstructor
    public static class Request {
        private String email;
        private TokenDto tokenDto;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String reissuedAccessToken;
        private String reissuedRefreshToken;
    }

}
