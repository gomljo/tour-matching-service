package com.backpacking.global.auth.service;

import com.backpacking.global.auth.dto.LoginDto;
import com.backpacking.global.auth.dto.ReissuedTokenDto;
import com.backpacking.global.security.dto.TokenDto;

public interface AuthService {

    TokenDto reissue(String email, TokenDto tokenDto);

    TokenDto login(LoginDto.Request request);

    String logout(String email);
}
