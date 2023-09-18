package com.backpacking.global.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LogoutDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response {
        private String email;
    }

}
