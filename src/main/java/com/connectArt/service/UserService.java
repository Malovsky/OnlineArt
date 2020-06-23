package com.connectArt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.connectArt.controller.UserController;
import com.connectArt.dto.UpdateUserDto;
import com.connectArt.model.User;
import com.connectArt.repository.UserRepository;

/**
 * @author Kevin Maslowski
 * 
 * @apiNote Service for the {@link UserController}
 *
 */
@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	// TODO : méthode dans Validator pour checker que l'update ne prend pas
	//		  le même usernme ou email qu'un autre utilisateur
	public User updateUser(UpdateUserDto uuDto) {
		User userToUpdate = userRepo.getOne(uuDto.getId());
		userToUpdate.setUsername(uuDto.getUserName());
		userToUpdate.setPassword(encoder.encode(uuDto.getPassword()));
		userToUpdate.setEmail(uuDto.getEmail());
		userToUpdate.setFirstName(uuDto.getFirstName());
		userToUpdate.setLastName(uuDto.getFirstName());
		userToUpdate.setAddress(uuDto.getAddress());
		userToUpdate.setBiography(uuDto.getBiography());
		return userRepo.save(userToUpdate);
	}
	
//	public void updateUserPhoto() {
//		// TODO
//	}
	
}
