package com.connectArt.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.connectArt.dto.CreateUserDto;
import com.connectArt.email.EmailSignIn;
import com.connectArt.enumeration.ERole;
import com.connectArt.model.ArtWork;
import com.connectArt.model.Order;
import com.connectArt.model.Role;
import com.connectArt.model.User;
import com.connectArt.repository.AdminRepository;
import com.connectArt.repository.RoleRepository;
import com.connectArt.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {
	
	UserRepository userRepo;
	
	RoleRepository roleRepo;
	
	PasswordEncoder encoder;
	
	AdminRepository adminRepo;
	
	@Autowired
	public AuthService(AdminRepository adminRepo, UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
		this.adminRepo = adminRepo;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.encoder = encoder;
	}

	/**
	 * Create new {@link User}
	 * 
	 * @param createUserDto
	 * @return
	 */
	public User createUser(CreateUserDto createUserDto) {
		log.info("Call createUser()");
		
		Role userRole = roleRepo.findByName(ERole.ROLE_USER);
		Set<Role> rolesList = new HashSet<>();
		rolesList.add(userRole);
		
		User user = new User(null, createUserDto.getUsername(),
				encoder.encode(createUserDto.getPassword()),
				createUserDto.getEmail(),
				rolesList, new ArrayList<Order>(),
				new ArrayList<ArtWork>());
		userRepo.save(user);
		
		EmailSignIn.valideSignIn(user);
		
		return user;
	}
	
	public User createAdmin(CreateUserDto createUserDto) {
		log.info("Call createAdmin()");
		
		Role role = roleRepo.findByName(ERole.ROLE_ADMIN);
		Set<Role> rolesList = new HashSet<>();
		rolesList.add(role);
		
		User user = new User(null, createUserDto.getUsername(), encoder.encode(createUserDto.getPassword()), createUserDto.getEmail(), rolesList);
		userRepo.save(user);
		
		return user;
	}
	
}
