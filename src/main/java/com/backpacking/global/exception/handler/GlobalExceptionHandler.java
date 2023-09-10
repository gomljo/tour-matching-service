package com.backpacking.global.exception.handler;

import com.backpacking.global.exception.CustomException;
import com.backpacking.global.security.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException customException) {
        log.error(customException.getClass().getSimpleName() + " is occurred!");
        return new ResponseEntity<>(
                new ErrorResponse(
                        customException.getErrorCode(),
                        customException.getDescription()),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        log.error(methodArgumentNotValidException.getClass().getSimpleName() + " is occurred!");
        log.error("not runtime");
        BindingResult result = methodArgumentNotValidException.getBindingResult();

        List<String> errors = new ArrayList<>();
        for (FieldError fieldError: result.getFieldErrors()){
            errors.add("["+fieldError.getField() + "]: " + fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(
                new ErrorResponse(
                        methodArgumentNotValidException.getClass().getSimpleName(),
                        errors),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(Exception exception) {
        log.error(exception.getClass().getSimpleName() + " is occurred!");
        return new ResponseEntity<>(
                new ErrorResponse(
                        exception.getClass().getSimpleName(),
                        exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
