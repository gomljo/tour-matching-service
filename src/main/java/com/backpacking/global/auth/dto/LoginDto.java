package com.backpacking.global.auth.dto;

import com.backpacking.global.security.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LoginDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Request {
        private String email;
        private String password;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response{
        private String accessToken;
        private String refreshToken;
        public static Response from(TokenDto tokenDto){
            return new Response(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
        }
    }



}
