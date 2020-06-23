package com.connectArt.enumeration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumColor {
	
	BEIGE("Beige"),
	WHITE("Blanc"),
	BLUE("Bleu"),
	YELLOW("Jaune"),
	BROWN("Marron"),
	BLACK("Noir"),
	ORANGE("Orange"),
	PINK("Rose"),
	RED("Rouge"),
	GREEN("Vert"),
	VIOLET("Violet");
	
	String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
