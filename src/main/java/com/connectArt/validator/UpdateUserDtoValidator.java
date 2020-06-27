package com.connectArt.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.connectArt.dto.UpdateUserDto;
import com.connectArt.exception.CannotUpdateUser;
import com.connectArt.repository.UserRepository;

@Component
public class UpdateUserDtoValidator implements Validator {
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateUserDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		UpdateUserDto uuDto = (UpdateUserDto)obj;
		
		if (uuDto == null ) {
			errors.reject("UpdateUserDto is null");
		} else {
			if (uuDto.getId() == null) {
				errors.reject("UpdateUserDto ID is null");
			} else {
				if (userRepo.existsByUsername(uuDto.getUsername())) {
					throw new CannotUpdateUser("Ce nom d'utilisateur est déjà utilisé !");
				} else {
					if (userRepo.existsByEmail(uuDto.getEmail())) {
						throw new CannotUpdateUser("Cet email est déjà utilisé !");
					}
				}
			}
		}
	}
	
}
