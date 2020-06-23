package com.connectArt.dto;

import java.util.UUID;

import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArtWorkPhotoDto {
	
	UUID id;
	
	@Lob
	byte[] photo;
	
}
