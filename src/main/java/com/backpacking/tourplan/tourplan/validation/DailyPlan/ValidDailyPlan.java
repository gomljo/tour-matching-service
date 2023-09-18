package com.backpacking.tourplan.tourplan.validation.DailyPlan;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DailyPlanValidator.class)
public @interface ValidDailyPlan {
    String message() default "여행 기간과 계획이 일치하지 않습니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
