package com.backpacking.global.exception.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
public class ErrorResponse {

    private final String errorCode;
    private final Set<String> descriptions;

    public ErrorResponse(String errorCode, String description) {
        this.errorCode = errorCode;
        this.descriptions = new HashSet<>();
        this.descriptions.add(description);
    }

    public ErrorResponse(String errorCode, Set<String> descriptions) {
        this.errorCode = errorCode;
        this.descriptions = descriptions;
    }
}
