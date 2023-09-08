package com.backpacking.global.exception.handler;

import com.backpacking.global.exception.CustomException;
import com.backpacking.global.security.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException customException) {
        log.error(customException.getClass().getSimpleName() + " is occurred!");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(
                new ErrorResponse(
                        customException.getErrorCode(),
                        customException.getDescription()),
                httpHeaders,
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException runtimeException) {
        log.error(runtimeException.getClass().getSimpleName() + " is occurred!");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(
                new ErrorResponse(
                        runtimeException.getClass().getSimpleName(),
                        runtimeException.getMessage()),
                httpHeaders,
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
