package com.backpacking.guide.guide.exception;

import com.backpacking.global.exception.CustomException;

public class GuideInfoException extends CustomException {

    private String guideInfoExceptionCode;
    private String description;

    public GuideInfoException(GuideInfoExceptionCode guideInfoExceptionCode){
        this.guideInfoExceptionCode = guideInfoExceptionCode.name();
        this.description = guideInfoExceptionCode.getDescription();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getErrorCode() {
        return this.guideInfoExceptionCode;
    }
}
