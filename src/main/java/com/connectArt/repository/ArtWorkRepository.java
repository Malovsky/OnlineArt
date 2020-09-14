package com.connectArt.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.connectArt.enumeration.EnumCategory;
import com.connectArt.enumeration.EnumSubcategory;
import com.connectArt.model.ArtWork;

public interface ArtWorkRepository extends JpaRepository<ArtWork, UUID>{
	
	@Query(value = "SELECT * FROM art_work WHERE user_id = ?1", nativeQuery = true)
	List<ArtWork> findMyArtworks(UUID userId);

	@Query(value = "SELECT * FROM art_work WHERE name LIKE %?1%", nativeQuery = true)
	List<ArtWork> findByName(String name);
	
	@Query(value = "SELECT * FROM art_work WHERE category = ?1", nativeQuery = true)
	List<ArtWork> findByCategory(EnumCategory enumCat);
	
	@Query(value = "SELECT * FROM art_work WHERE subcategory = ?1", nativeQuery = true)
	List<ArtWork> findBySubCategory(EnumSubcategory enumSubCat);
}
