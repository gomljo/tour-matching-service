package com.backpacking.global.security.exception.security;

import com.backpacking.global.exception.CustomException;

public class SecurityException extends CustomException {
    private final SecurityExceptionCode securityExceptionCode;
    private final String description;

    public SecurityException(SecurityExceptionCode securityExceptionCode) {
        this.securityExceptionCode = securityExceptionCode;
        this.description = securityExceptionCode.getDescription();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
    @Override
    public String getErrorCode(){
        return securityExceptionCode.toString();
    }

}
