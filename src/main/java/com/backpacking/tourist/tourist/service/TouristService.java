package com.backpacking.tourist.tourist.service;

import com.backpacking.global.security.dto.LoginDto;
import com.backpacking.tourist.tourist.domain.Tourist;
import com.backpacking.tourist.tourist.dto.CreateTourist;

public interface TouristService {

   Tourist register(CreateTourist.Request request);
   Tourist update(Tourist tourist);
   String authenticate(LoginDto.Request request);

}
