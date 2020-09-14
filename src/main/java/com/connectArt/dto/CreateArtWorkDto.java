package com.connectArt.dto;

import java.util.UUID;

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

	String category;

	String subcategory;

	String majorColor;
	
	String isSigned;
	
	String hasFrame;
	
	String size;
	
}
