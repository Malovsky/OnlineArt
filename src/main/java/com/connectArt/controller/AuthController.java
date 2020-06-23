package com.connectArt.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectArt.dto.CreateUserDto;
import com.connectArt.dto.JwtResponse;
import com.connectArt.dto.LoginRequest;
import com.connectArt.model.User;
import com.connectArt.repository.RoleRepository;
import com.connectArt.repository.UserRepository;
import com.connectArt.security.jwt.JwtUtils;
import com.connectArt.security.service.UserDetailsImpl;
import com.connectArt.service.AuthService;
import com.connectArt.validator.CreateUserDtoValidator;

@CrossOrigin	/*(origins = "*", maxAge = 3600)*/
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	CreateUserDtoValidator cuDtoValid;
	
	Errors errors;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	/**
	 * @param createUserDto
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDto createUserDto) {

		cuDtoValid.validate(createUserDto, errors);
		
		// Create new user's account
		User user = authService.createUser(createUserDto);

		return ResponseEntity.ok(user);
	}
	
}
