package com.connectArt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadEnum extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;
	
}
