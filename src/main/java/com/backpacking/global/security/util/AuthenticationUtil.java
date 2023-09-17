package com.backpacking.global.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {


    private AuthenticationUtil(){

    }

    public boolean isInvalidAccess(String userEmail){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !authentication.getName().equals(userEmail);
    }

}
