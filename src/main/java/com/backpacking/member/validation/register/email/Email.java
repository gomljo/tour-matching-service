package com.backpacking.member.validation.register.email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EMailValidator.class)
public @interface Email {
    String message() default "이메일 형식이 올바르지 않습니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
