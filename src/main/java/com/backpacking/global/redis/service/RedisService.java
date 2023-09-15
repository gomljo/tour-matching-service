package com.backpacking.global.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

import static com.backpacking.global.redis.constant.RedisKey.RT;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public String getRefreshToken(String identifier){
        return redisTemplate.opsForValue().get(RT.getValue() + identifier);
    }

    public void setRefreshTokenTimeout(String identifier, String value, long timeout) {
        redisTemplate.opsForValue().set(RT.getValue() + identifier, value, timeout, TimeUnit.MILLISECONDS);
    }

    public boolean existsRefreshToken(String identifier) {
        String refreshToken = redisTemplate.opsForValue().get(RT.getValue() + identifier);
        return StringUtils.hasText(refreshToken);
    }

    public void deleteRefreshToken(String identifier) {
        redisTemplate.delete(RT.getValue() + identifier);
    }

}
