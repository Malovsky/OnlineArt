package com.connectArt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.connectArt.dto.ArtworkPanierDto;
import com.connectArt.dto.CreateArtWorkDto;
import com.connectArt.model.ArtWork;
import com.connectArt.model.User;
import com.connectArt.repository.ArtWorkRepository;
import com.connectArt.repository.UserRepository;
import com.connectArt.service.ArtWorkService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Kevin Maslowski
 * 
 * @apiNote Controller of {@link ArtWork}
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/artwork")
@Slf4j
public class ArtWorkController {
	
	@Autowired
	ArtWorkRepository artWorkRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ArtWorkService artWorkService;
	
	@GetMapping
	public ResponseEntity<List<ArtWork>> getAll() {
		List <ArtWork> allArtWorkList = artWorkRepo.findAll();
		Collections.shuffle(allArtWorkList);
		return ResponseEntity.ok(allArtWorkList);
	}
	
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
	public ResponseEntity<Void> deleteArtWorkById(@PathVariable("id") UUID id) throws IOException {
		artWorkRepo.deleteById(id);
		Files.delete(Paths.get(System.getProperty("user.home")+"/ecom/artworks/" + id + ".jpg"));
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(path="/addMyArtwork/{id}")
	public ResponseEntity<ArtWork> createArtWork(@RequestBody CreateArtWorkDto cawDto, @PathVariable("id") UUID id) throws Exception {
		User user = userRepo.findById(id).get();
		ArtWork art = ArtWork.of(cawDto);
		art.setUser(user);
		artWorkRepo.save(art);
		user.getArtworkList().add(art);
		userRepo.save(user);
		return ResponseEntity.ok(art);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping(path="/addPhotoArtwork/{id}")
	public ResponseEntity<Void> addPhotoArtWork(@RequestParam("file") MultipartFile file, @PathVariable("id") UUID id) throws Exception {
		ArtWork art = artWorkRepo.findById(id).get();
		art.setPhotoArtwork(id + ".jpg");
		Files.write(Paths.get(System.getProperty("user.home") + "/ecom/artworks/" + art.getPhotoArtwork()), file.getBytes());
		artWorkRepo.save(art);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/myArt/{id}")
	public ResponseEntity<List<ArtWork>> getAllUserArtWorkById(@PathVariable("id") UUID id) {
		log.info("id : {}", id);
		List<ArtWork> artWorkList = artWorkService.getAllUserArtWork(id);
		return ResponseEntity.ok(artWorkList);
	}
	
	@GetMapping(path="/getPhotoArtwork/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getPhotoArtwork(@PathVariable("id") UUID id) throws Exception {
		log.info("id : {}", id);
		ArtWork art = artWorkRepo.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/artworks/" + art.getPhotoArtwork()));
	}
	
	@PostMapping(path="/getMultipleArtworkById")
	public ResponseEntity<List<ArtWork>> getMultipleArtworkById(@RequestBody List<UUID> idsArt) {
		List<ArtWork> listToReturn = new ArrayList<>();
		for (UUID id : idsArt) {
			listToReturn.add(artWorkRepo.findById(id).get());
		}
		return ResponseEntity.ok(listToReturn);
	}
	
	
}
