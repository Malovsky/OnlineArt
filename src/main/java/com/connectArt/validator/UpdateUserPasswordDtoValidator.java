package com.connectArt.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.connectArt.dto.UpdateUserPasswordDto;
import com.connectArt.model.User;
import com.connectArt.repository.UserRepository;

public class UpdateUserPasswordDtoValidator implements Validator {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateUserPasswordDto.class.isAssignableFrom(clazz);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void validate(Object obj, Errors errors) {
		UpdateUserPasswordDto dto = (UpdateUserPasswordDto)obj;
		User userToUpdate = userRepo.findById(dto.getId()).get();
		
		if (dto == null) {
			errors.reject("UpdateUserPasswordDto is null");
		} else {
			if (dto.getId() == null) {
				errors.reject("UpdateUserPasswordDto ID is null");
			} else {
				if (encoder.encode(dto.getPreviousPassword()) != userToUpdate.getPassword()) {
					errors.reject("Actual password is not good");
				} else {
					
				} if (encoder.encode(dto.getNewPassword()) == userToUpdate.getPassword()) {
					errors.reject("New password is the same that the actual password");
				}
			}
		}
	}
	
}
