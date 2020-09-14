package com.connectArt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectArt.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID>{
	
	
	
}
