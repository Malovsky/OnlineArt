package com.connectArt.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.connectArt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	Optional<User> findByUsername(String username);
	
	@Query(value = "select * from admin where id IN "
			+ "(SELECT user_id from user_roles where role_id IN "
				+ "(SELECT id from roles where name = 'ROLE_USER')"
			+ ")", nativeQuery = true)
	List<User> getAllUsersDtype();

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query(value = "SELECT * FROM admin WHERE id IN "
			+ "(SELECT user_id FROM art_work WHERE id = ?1)", nativeQuery = true)
	User findUsersDetailWithArtId(UUID artworkId);
	
}
