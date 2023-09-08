package com.backpacking.global.exception;

public abstract class CustomException extends RuntimeException {

    public abstract String getDescription();

   public abstract String getErrorCode();

}
