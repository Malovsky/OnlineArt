package com.connectArt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
	
	@Lob
	byte[] photoProfile;
	
	@OneToMany(targetEntity = OrderItem.class)
	@JoinColumn(name = "orderList")
	List<Order> orderList = new ArrayList<Order>();
	
	@OneToMany(targetEntity = ArtWork.class) // vérifier la classe target
	@JoinColumn(name = "artWorkList")
	List<ArtWork> artworkList = new ArrayList<ArtWork>();

	public User(UUID id, String pseudo, String password, String email, Set<Role> roles, List<Order> orderList, List<ArtWork> artworkList) {
		super(id, pseudo, password, email, roles);
		this.orderList = orderList;
		this.artworkList = artworkList;
	}
	
	
	
}
