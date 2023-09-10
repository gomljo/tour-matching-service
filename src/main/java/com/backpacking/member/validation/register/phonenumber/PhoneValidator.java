package com.backpacking.member.validation.register.phonenumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private static final String PHONE_NUMBER_PATTERN = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";

    @Override
    public void initialize(Phone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.matches(PHONE_NUMBER_PATTERN, value);
    }
}
