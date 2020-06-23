package com.connectArt.dto;

import java.util.UUID;

import javax.persistence.Lob;

import com.connectArt.enumeration.EnumCategory;
import com.connectArt.enumeration.EnumColor;
import com.connectArt.enumeration.EnumSubcategory;
import com.connectArt.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArtWorkDto {

	UUID userId;
	
	String name;
	
	int price;
	
	int availability;
	
	String description;
	
	@Lob
	byte[] photoArtwork;
	
	User userDetail;

	EnumCategory category;

	EnumSubcategory subcategory;

	EnumColor majorColor;
	
	boolean isSigned;
	
	boolean hasFrame;
	
	String size;
	
}
