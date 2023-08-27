package com.backpacking.tourist.tourist.service;

import com.backpacking.global.member.type.Roles;
import com.backpacking.global.security.dto.LoginDto;
import com.backpacking.global.security.exception.SecurityException;
import com.backpacking.global.security.exception.SecurityExceptionCode;
import com.backpacking.global.security.model.SecuredUser;
import com.backpacking.tourist.tourist.auth.service.TouristUserDetailsService;
import com.backpacking.tourist.tourist.auth.token.TouristJwtTokenProvider;
import com.backpacking.tourist.tourist.domain.Tourist;
import com.backpacking.tourist.tourist.dto.CreateTourist;
import com.backpacking.tourist.tourist.exception.TouristException;
import com.backpacking.tourist.tourist.exception.TouristExceptionCode;
import com.backpacking.tourist.tourist.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TouristServiceImpl implements TouristService {

    private final TouristRepository touristRepository;

    @Override
    public Tourist register(CreateTourist.Request request) {

        if (touristRepository.existsTouristByEmail(request.getEmail())) {
            throw new TouristException(TouristExceptionCode.ALREADY_REGISTERED);
        }
        return touristRepository.save(Tourist.builder()
                .email(request.getEmail())
                .address(request.getAddress())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .roles(List.of(Roles.ROLE_TOURIST.toString()))
                .build());
    }

    @Override
    public Tourist update(Tourist tourist) {
        return null;
    }

    public String authenticate(LoginDto.Request request) {
        return null;
    }


}
