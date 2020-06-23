package com.connectArt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connectArt.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, UUID>{
	
	
	
}
