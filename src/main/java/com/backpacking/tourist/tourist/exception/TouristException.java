package com.backpacking.tourist.tourist.exception;

import com.backpacking.global.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TouristException extends CustomException {

    private TouristExceptionCode touristExceptionCode;
    private String description;

    public TouristException(TouristExceptionCode touristExceptionCode){
        this.touristExceptionCode = touristExceptionCode;
        this.description = touristExceptionCode.getDescription();
    }


    @Override
    public String getErrorCode() {
        return touristExceptionCode.toString();
    }
    @Override
    public String getDescription(){
        return this.description;
    };
}
