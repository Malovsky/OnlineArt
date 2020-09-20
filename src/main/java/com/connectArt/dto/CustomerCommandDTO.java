package com.connectArt.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCommandDTO {

	public UUID userId;
	public List<UUID> orderItemIds;
	
}
