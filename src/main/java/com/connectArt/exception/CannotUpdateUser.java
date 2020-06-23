package com.connectArt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CannotUpdateUser extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;
	
}
