package com.mbfinalassignment.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIdValidator implements ConstraintValidator<ValidProductId, String> {

	private String regex = "^([a-z0-9]+$";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if ("".equals(value )) {
			return true;
		}
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(value).matches();
	}

}
