package com.connectArt.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectArt.dto.UpdateUserDto;
import com.connectArt.model.User;
import com.connectArt.repository.UserRepository;
import com.connectArt.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("{/id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(userRepo.findById(id).get());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userRepo.findAll());
	}
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@DeleteMapping("{/id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
		userRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PutMapping
	public ResponseEntity<Void> updateUser(UpdateUserDto uuDto) {
		userService.updateUser(uuDto);
		return ResponseEntity.ok().build();
	}
	
//	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//	@PutMapping
//	public ResponseEntity<Void> changeUserPhoto() {
//		userService.updateUserPhoto(); // TODO Service
//		return ResponseEntity.ok().build();
//	}
	
	
	
}
