package com.connectArt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectArt.enumeration.ERole;
import com.connectArt.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
	
	Role findByName(ERole name);
	
}
