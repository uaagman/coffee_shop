package com.ashokn.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ashok on 6/21/17.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullPasswordValidator.class)
public @interface NotNullPassword {
    String message() default "Password should be set while creating the user";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
