package com.backpacking.global.redis.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RedisKey {

    RT("RT"),
    LOGOUT("logout");



    private final String value;

    public String getValue(){
        return this.value;
    }
}
