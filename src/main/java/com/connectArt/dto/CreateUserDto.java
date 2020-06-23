package com.connectArt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserDto {
	
	String username;
	
	String password;
	
	String email;
	
}
