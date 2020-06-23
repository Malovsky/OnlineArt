package com.connectArt.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
	
	UUID id;
	
	String userName;
	
	String password;
	
	String email;
	
	String firstName;
	
	String lastName;
	
	String address;
	
	String biography;
	
}
