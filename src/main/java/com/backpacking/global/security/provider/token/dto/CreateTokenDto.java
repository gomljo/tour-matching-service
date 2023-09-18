package com.backpacking.global.security.provider.token.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTokenDto {

    private String email;

    private List<String> roles;

    private String keyRoles;
    private String tokenType;

    private String keyTokenType;

    private long tokenExpiredTime;

    private String secretKey;


}
