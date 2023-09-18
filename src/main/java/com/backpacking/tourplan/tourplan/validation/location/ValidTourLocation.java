package com.backpacking.tourplan.tourplan.validation.location;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TourLocationValidator.class)
public @interface ValidTourLocation {

    String message() default "여행 목적지를 입력해주세요";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
