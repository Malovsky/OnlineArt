package com.connectArt.enumeration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumSubcategory {
	
	ABSTRAIT("Abstrait"),
	ACRYLIQUE("Acrylique"),
	BOIS("Bois"),
	BOMBE("Bombe"),
	CIMENT("Ciment"),
	COULEURS("Couleurs"),
	CRAIE("Craie"),
	CRAYON("Crayon"),
	FEUTRE("Feutre"),
	GLACE("Glace"),
	GOUACHE("Gouache"),
	HUILE("Huile"),
	MARBRE("Marbre"),
	METAUX("Métaux"),
	NOIR_ET_BLANC("Noir et blanc"),
	PAPIER("Papier"),
	PAYSAGE("Paysage"),
	PHOTO_MANIPULEE("Photo manipulée"),
	PHOTO_NON_MANIPULEE("Photo non manipulée"),
	PLASTIQUE("Plastique"),
	PORTRAIT("Portrait"),
	REALISTE("Réaliste");
	
	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	private String subcategory;
	
}
