package com.connectArt.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.EnumUtils;

import com.connectArt.dto.CreateArtWorkDto;
import com.connectArt.enumeration.EnumCategory;
import com.connectArt.enumeration.EnumColor;
import com.connectArt.enumeration.EnumSubcategory;
import com.connectArt.exception.BadEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtWork {
	
	@Id
	@GeneratedValue
	UUID id;
	
	String name;
	
	int price;
	
	int availability;
	
	String description;
	
	String photoArtwork;
	
	@ManyToOne(targetEntity = User.class)
	User user;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	EnumCategory category;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	EnumSubcategory subcategory;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	EnumColor majorColor;
	
	boolean isSigned;
	
	boolean hasFrame;
	
	String size;
	
	public static ArtWork of(CreateArtWorkDto cawDto) {
		ArtWork artWork = new ArtWork();
		artWork.setName(cawDto.getName());
		artWork.setPrice(cawDto.getPrice());
		artWork.setAvailability(cawDto.getAvailability());
		artWork.setDescription(cawDto.getDescription());
		artWork.setUser(null);
		if (!EnumUtils.isValidEnum(EnumCategory.class, cawDto.getCategory())) {
			throw new BadEnum("Error : cette catégorie ne fait pas partie de notre liste !");
		}
		artWork.setCategory(EnumUtils.getEnum(EnumCategory.class, cawDto.getCategory()));
		if (!EnumUtils.isValidEnum(EnumSubcategory.class, cawDto.getSubcategory())) {
			throw new BadEnum("Error : cette sous catégorie ne fait pas partie de notre liste !");
		}
		artWork.setSubcategory(EnumUtils.getEnum(EnumSubcategory.class, cawDto.getSubcategory()));
		if (!EnumUtils.isValidEnum(EnumColor.class, cawDto.getMajorColor())) {
			throw new BadEnum("Error : cette couleur ne fait pas partie de notre liste !");
		}
		artWork.setMajorColor(EnumUtils.getEnum(EnumColor.class, cawDto.getMajorColor()));
		if (cawDto.getIsSigned().equals("OUI")) {
			artWork.setSigned(true);
		} else if (cawDto.getIsSigned().equals("NON")) {			
			artWork.setSigned(false);
		}
		if (cawDto.getHasFrame().equals("OUI")) {
			artWork.setHasFrame(true); 
		} else if (cawDto.getHasFrame().equals("NON")) {			
			artWork.setHasFrame(false); 
		}
		artWork.setSize(cawDto.getSize());
		return artWork;
	}
	
}
