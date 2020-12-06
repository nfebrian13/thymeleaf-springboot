package com.springboot.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springboot.entity.Person;

@Component
public class PersonValidator implements Validator {

	@Override
	public boolean supports(Class<?> type) {
		return Person.class.isAssignableFrom(type);
	}

	@Override
	public void validate(Object o, Errors errors) {
		validatePage1(o, errors);
//		validatePage2(o, errors);
	}

	public void validatePage1(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
	}

	public void validatePage2(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
	}

}
