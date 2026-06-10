// ValidUsername.java - Create in src/main/java/com/example/pokemonproject/common/validation/
package com.example.pokemonproject.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@Documented
public @interface ValidUsername {
    String message() default "{validation.username.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}