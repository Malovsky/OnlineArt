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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectArt.dto.CreateArtWorkDto;
import com.connectArt.model.ArtWork;
import com.connectArt.repository.ArtWorkRepository;
import com.connectArt.service.ArtWorkService;

/**
 * @author Kevin Maslowski
 * 
 * @apiNote Controller of {@link ArtWork}
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/artwork")
public class ArtWorkController {
	
	@Autowired
	ArtWorkRepository artWorkRepo;
	
	@Autowired
	ArtWorkService artWorkService;
	
	@GetMapping
	public ResponseEntity<List<ArtWork>> getAll() {
		List <ArtWork> allArtWorkList = artWorkRepo.findAll();
		return ResponseEntity.ok(allArtWorkList);
	}
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ArtWork> getOneById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(artWorkRepo.getOne(id));
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<Void> updateArtWork(@RequestBody ArtWork artWork) {
		artWorkRepo.save(artWork);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteArtWorkById(@PathVariable("id") UUID id) {
		artWorkRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> createArtWork(@RequestBody CreateArtWorkDto cawDto) {
		artWorkService.createArtWork(cawDto);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/myArt/{id}")
	public ResponseEntity<List<ArtWork>> getAllUserArtWorkById(@PathVariable("id") UUID id) {
		List<ArtWork> artWorkList = artWorkService.getAllUserArtWork(id);
		return ResponseEntity.ok(artWorkList);
	}
	
}
