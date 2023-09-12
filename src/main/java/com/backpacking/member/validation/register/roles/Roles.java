package com.backpacking.member.validation.register.roles;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleValidator.class)
public @interface Roles {
    String message() default "회원 권한이 유효하지 않습니다";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
