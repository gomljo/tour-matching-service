package com.backpacking.tourplan.tourplan.validation.location;

import com.backpacking.tourplan.tourplan.domain.vo.TourLocation;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TourLocationValidator implements ConstraintValidator<ValidTourLocation, TourLocation> {

    @Override
    public void initialize(ValidTourLocation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TourLocation value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getCity()) && StringUtils.hasText(value.getCity());
    }
}
