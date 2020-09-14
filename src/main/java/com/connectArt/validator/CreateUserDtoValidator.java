package com.connectArt.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.connectArt.dto.CreateUserDto;
import com.connectArt.exception.CannotUpdateUser;
import com.connectArt.repository.UserRepository;


@Component
public class CreateUserDtoValidator implements Validator {
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CreateUserDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		CreateUserDto uuDto = (CreateUserDto)obj;
		
		if (uuDto == null ) {
			errors.reject("UpdateUserDto is null !");
		} else {
			if (uuDto.getEmail() == "") {
				errors.reject("Email is empty !");
			} else {
				if (uuDto.getUsername() == "") {
					errors.reject("Username is empty !");
				} else {
					if (uuDto.getPassword() == "") {
						errors.reject("Password is empty !");
					} else {
						if (userRepo.existsByUsername(uuDto.getUsername())) {
							throw new CannotUpdateUser("Ce nom d'utilisateur est déjà utilisé !");
						} else {
							if (userRepo.existsByEmail(uuDto.getEmail())) {
								throw new CannotUpdateUser("Cet email est déjà utilisé !");
							} else {
								if (uuDto.getUsername().equalsIgnoreCase(uuDto.getPassword())) {
									throw new CannotUpdateUser("Votre nom d'utilisateur et votre mot de passe ne peut être identique !");
								}
							}
						}
					}
				}
			
			}
		}
	}

	
}
