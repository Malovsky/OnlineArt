package com.connectArt.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.connectArt.enumeration.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class Role {
	
	
/*	INSERT INTO roles(id, name) VALUES('525e0662-b275-11ea-b3de-0242ac130004','ROLE_USER');
	INSERT INTO roles(id, name) VALUES('525e0e78-b275-11ea-b3de-0242ac130004','ROLE_ADMIN');
*/
	
	@Id
	@GeneratedValue
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}
