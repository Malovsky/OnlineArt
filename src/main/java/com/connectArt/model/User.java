package com.connectArt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Admin {
	
	String firstName;
	
	String lastName;
	
	String address;
	
	String biography;
	
	String photoProfile;
	
	@OneToMany(targetEntity = OrderItem.class)
	@JoinColumn(name = "orderList")
	List<Order> orderList = new ArrayList<Order>();
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	List<ArtWork> artworkList = new ArrayList<ArtWork>();

	public User(UUID id, String pseudo, String password, String email, Set<Role> roles, List<Order> orderList, List<ArtWork> artworkList) {
		super(id, pseudo, password, email, roles);
		this.orderList = orderList;
		this.artworkList = artworkList;
	}
	
	public User(UUID id, String pseudo, String password, String email, Set<Role> roles) {
		super(id, pseudo, password, email, roles);
	}
	
	
	
}
