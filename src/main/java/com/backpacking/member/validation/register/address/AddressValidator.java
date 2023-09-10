package com.backpacking.member.validation.register.address;

import com.backpacking.member.domain.vo.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<ValidAddress, Address> {
    @Override
    public void initialize(ValidAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Address value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getNation()) && StringUtils.hasText(value.getCity()) && StringUtils.hasText(value.getDetailAddress());
    }
}
