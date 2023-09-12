package com.backpacking.global.exception.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ErrorResponse {

    private final String errorCode;
    private final List<String> descriptions;

    public ErrorResponse(String errorCode, String description) {
        this.errorCode = errorCode;
        this.descriptions = new ArrayList<>();
        this.descriptions.add(description);
    }

    public ErrorResponse(String errorCode, List<String> descriptions){
        this.errorCode = errorCode;
        this.descriptions = descriptions;
    }
}
