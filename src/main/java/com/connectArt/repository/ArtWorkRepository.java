package com.connectArt.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.connectArt.enumeration.EnumCategory;
import com.connectArt.enumeration.EnumSubcategory;
import com.connectArt.model.ArtWork;
import com.connectArt.model.User;

public interface ArtWorkRepository extends JpaRepository<ArtWork, UUID>{
	
	@Query(value = "SELECT * FROM art_work WHERE user_id = ?1", nativeQuery = true)
	List<ArtWork> findMyArtworks(UUID userId);
	
}