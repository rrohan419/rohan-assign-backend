package com.mbfinalassignment.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE })
@Constraint(validatedBy = ProductIdValidator.class)
public @interface ValidProductId {
	String message() default "{ProductId.invalid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
