package com.backpacking.global.security.exception.jwt;

import com.backpacking.global.exception.CustomException;

public class CustomJwtException extends CustomException {

    private final CustomJwtExceptionCode customJwtExceptionCode;
    private final String description;

    public CustomJwtException(CustomJwtExceptionCode customJwtExceptionCode) {
        this.customJwtExceptionCode = customJwtExceptionCode;
        this.description = customJwtExceptionCode.getDescription();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
    @Override
    public String getErrorCode(){
        return customJwtExceptionCode.name();
    }

}
