package com.backpacking.global.security.provider.token.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class TokenExpiredConstant {

    @Value("${token-expired.millisecond-unit}")
    private long millisecondUnit;

    @Value("${token-expired.access.second}")
    private long accessSecond;

    @Value("${token-expired.access.minute}")
    private long accessMinute;

    @Value("${token-expired.access.hour}")
    private long accessHour;

    @Value("${token-expired.refresh.second}")
    private long refreshSecond;

    @Value("${token-expired.refresh.minute}")
    private long refreshMinute;

    @Value("${token-expired.refresh.hour}")
    private long refreshHour;

    private TokenExpiredConstant(){

    }

    public long getAccessTokenExpiredTime(){
        return accessHour * accessMinute * accessSecond * millisecondUnit;
    }

    public long getRefreshTokenExpiredTime(){
        return refreshHour * refreshMinute * refreshSecond * millisecondUnit;
    }


}
