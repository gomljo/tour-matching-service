package com.backpacking.global.security.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordUtil {

    private final PasswordEncoder passwordEncoder;

    public String encode(String password){
        return passwordEncoder.encode(password);
    }

    public boolean validatePassword(String requested, String saved){
        log.info(String.valueOf(passwordEncoder.matches(requested, saved)));

        return passwordEncoder.matches(requested, saved);
    }

}
