package com.backpacking.global.security.service;

import com.backpacking.global.security.model.SecuredUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    SecuredUser loadUserByUsername(String email);

}