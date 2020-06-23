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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectArt.model.Admin;
import com.connectArt.repository.AdminRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	AdminRepository amdinRepo;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("{/id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(amdinRepo.findById(id).get());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping()
	public ResponseEntity<List<Admin>> getAllUsers() {
		return ResponseEntity.ok(amdinRepo.findAll());
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("{/id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
		amdinRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
