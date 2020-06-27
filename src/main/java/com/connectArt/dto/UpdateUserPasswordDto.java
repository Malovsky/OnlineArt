package com.connectArt.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordDto {
	
	UUID id;
	
	String previousPassword;
	
	String newPassword;
	
}
