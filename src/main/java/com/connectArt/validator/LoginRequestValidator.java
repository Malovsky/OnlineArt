package com.connectArt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.connectArt.dto.LoginRequest;

@Component
public class LoginRequestValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		LoginRequest logRequest = (LoginRequest)obj;
		
		if (logRequest == null) {
			errors.reject("LoginRequest is null");
		} else {
			
		}
		
	}
	
}
