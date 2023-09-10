package com.backpacking.member.exception;

import com.backpacking.global.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberException extends CustomException {

    private MemberExceptionCode memberExceptionCode;
    private String description;

    public MemberException(MemberExceptionCode memberExceptionCode) {
        this.memberExceptionCode = memberExceptionCode;
        this.description = memberExceptionCode.getDescription();
    }


    @Override
    public String getErrorCode() {
        return memberExceptionCode.name();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    ;
}
