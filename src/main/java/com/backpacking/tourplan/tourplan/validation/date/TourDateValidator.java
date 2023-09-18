package com.backpacking.tourplan.tourplan.validation.date;

import com.backpacking.tourplan.tourplan.domain.vo.TourDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TourDateValidator implements ConstraintValidator<ValidTourDate, TourDate> {
    @Override
    public void initialize(ValidTourDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TourDate value, ConstraintValidatorContext context) {
        return value.getStartDate().isBefore(value.getEndDate());
    }
}
