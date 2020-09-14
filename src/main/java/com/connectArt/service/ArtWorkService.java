package com.connectArt.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectArt.controller.ArtWorkController;
import com.connectArt.dto.CreateArtWorkDto;
import com.connectArt.model.ArtWork;
import com.connectArt.model.User;
import com.connectArt.repository.ArtWorkRepository;
import com.connectArt.repository.UserRepository;

/**
 * @author Kevin Maslowski
 * 
 * @apiNote Service for the {@link ArtWorkController}
 *
 */
@Service
public class ArtWorkService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ArtWorkRepository artWorkRepo;
	
	/**
	 * Creates an {@link ArtWork} and add it to the list of the {@link User}
	 * that we found with the id in {@link CreateArtWorkDto}
	 * 
	 * @param cawDto
	 */
	public void createArtWork(CreateArtWorkDto cawDto) {
		User user = userRepo.getOne(cawDto.getUserId());
		ArtWork artWork = ArtWork.of(cawDto);
		artWork.setUser(user);
		artWorkRepo.save(artWork);
		user.getArtworkList().add(artWork);
	}
	
	/**
	 * @param id
	 * @return all {@link @ArtWork} of the {@link User}
	 */
	public List<ArtWork> getAllUserArtWork(UUID id) {
		List<ArtWork> artWorkList = artWorkRepo.findMyArtworks(id);
		return artWorkList;
	}
	
}
