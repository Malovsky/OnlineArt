package com.connectArt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.connectArt.dto.UpdateUserDto;
import com.connectArt.dto.UpdateUserPasswordDto;
import com.connectArt.model.ArtWork;
import com.connectArt.model.User;
import com.connectArt.repository.ArtWorkRepository;
import com.connectArt.repository.UserRepository;
import com.connectArt.service.ArtWorkService;
import com.connectArt.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ArtWorkRepository artWorkRepo;
	
	@Autowired
	ArtWorkService artWorkService;
	
	Errors errors;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(userRepo.findById(id).get());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userRepo.findAll());
	}
	
	@GetMapping("/usersdtype")
	public ResponseEntity<List<User>> getAllUsersDtype() {
		return ResponseEntity.ok(userRepo.getAllUsersDtype());
	}
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) throws IOException {
		userRepo.findById(id).get();
		List<ArtWork> artWorkList = artWorkService.getAllUserArtWork(id);
		for (ArtWork art : artWorkList) {
			artWorkRepo.deleteById(art.getId());
			Files.delete(Paths.get(System.getProperty("user.home")+"/ecom/artworks/" + art.getId() + ".jpg"));
		}
		userRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping
	public ResponseEntity<Void> updateUser(@RequestBody UpdateUserDto uuDto) {
		log.info("id : {}", uuDto.getId());
		userService.updateUser(uuDto);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PutMapping("/updatePass")
	public ResponseEntity<Void> updateUserPass(@RequestBody UpdateUserPasswordDto updatePassword) {
		log.info("Pass in updateUserPass()");
//		updateUserPasswordValidator.validate(updatePassword, errors);  // Il faut régler ce soucis, ainsi que ajouter les messages d'erreurs au front
		userService.updateUserPassword(updatePassword);
		return ResponseEntity.ok().build();
	}
	
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping(path="/getPhotoProfile/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getPhotoProfile(@PathVariable("id") UUID id) throws Exception {
		User user = userRepo.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/users/"+ user.getPhotoProfile()));
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(path="/updatePhotoProfile/{id}")
	public ResponseEntity<Void> updatePhotoProfile(@RequestParam("file") MultipartFile file, @PathVariable("id") UUID id) throws Exception {
		log.info("id : {}", id);
		User user = userRepo.findById(id).get();
		user.setPhotoProfile(id + ".jpg");
		Files.write(Paths.get(System.getProperty("user.home") + "/ecom/users/" + user.getPhotoProfile()), file.getBytes());
		userRepo.save(user);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping(path="/getOwnerById/{id}")
	public User getOwnerById(@PathVariable("id") UUID id) {
		return userRepo.findUsersDetailWithArtId(id);
	}
	
	
}
