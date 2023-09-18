package com.backpacking.global.auth.controller;

import com.backpacking.global.auth.dto.LoginDto;
import com.backpacking.global.auth.dto.LogoutDto;
import com.backpacking.global.auth.dto.ReissuedTokenDto;
import com.backpacking.global.auth.service.AuthService;
import com.backpacking.global.security.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginDto.Response login(@RequestBody LoginDto.Request request) {
        return LoginDto.Response.from(authService.login(request));
    }

    @PostMapping("/reissue/{email:.+}")
    public TokenDto reissue(@PathVariable String email, TokenDto tokenDto) {
        log.info("재발행 컨트롤러 실행");
        return authService.reissue(email, tokenDto);
    }


    @PostMapping("/logout/{email}")
    public LogoutDto.Response logout(@PathVariable String email) {

        return new LogoutDto.Response(authService.logout(email));
    }

}
