package com.connectArt.enumeration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumCategory {
	
	DRAWING("Dessin"),
	PAINTING("Peinture"),
	PHOTO("Photo"),
	SCULPTURE("Sculpture");
	
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
