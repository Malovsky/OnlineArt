package com.connectArt.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.connectArt.dto.CreateArtWorkDto;
import com.connectArt.enumeration.EnumCategory;
import com.connectArt.enumeration.EnumColor;
import com.connectArt.enumeration.EnumSubcategory;
import com.connectArt.exception.BadEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.EnumUtils;

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
	
	@Lob
	byte[] photoArtwork;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userId")
	User userDetail;
	
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
		artWork.setPhotoArtwork(cawDto.getPhotoArtwork());
		artWork.setUserDetail(null);
		if (!EnumUtils.isValidEnum(EnumCategory.class, cawDto.getCategory().toString())) {
			throw new BadEnum("Error : cette catégorie ne fait pas partie de notre liste !");
		}
		artWork.setCategory(cawDto.getCategory());
		if (!EnumUtils.isValidEnum(EnumSubcategory.class, cawDto.getSubcategory().toString())) {
			throw new BadEnum("Error : cette sous catégorie ne fait pas partie de notre liste !");
		}
		artWork.setSubcategory(cawDto.getSubcategory());
		if (!EnumUtils.isValidEnum(EnumColor.class, cawDto.getMajorColor().toString())) {
			throw new BadEnum("Error : cette couleur ne fait pas partie de notre liste !");
		}
		artWork.setMajorColor(cawDto.getMajorColor());
		return artWork;
	}
	
}
