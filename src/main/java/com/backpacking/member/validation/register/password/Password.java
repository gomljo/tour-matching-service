package com.backpacking.member.validation.register.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "비밀번호는 영소문자, 영대문자, 특수문자가 1개씩 포함된 8자 이상 20자여야 합니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
