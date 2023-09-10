package com.backpacking.member.validation.register.roles;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleValidator implements ConstraintValidator<Roles, List<String>> {

    private static final int ZERO = 0;

    @Override
    public void initialize(Roles constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        Set<String> preDefinedRoles = Arrays.stream(com.backpacking.member.type.Roles.values())
                                                    .map(String::valueOf).collect(Collectors.toSet());
        Set<String> requestRoles = new HashSet<>(value);

        requestRoles.removeAll(preDefinedRoles);

        return requestRoles.size() == ZERO;
    }
}
