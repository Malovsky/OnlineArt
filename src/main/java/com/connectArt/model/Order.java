package com.connectArt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderz")
public class Order {
	
	@Id
	@GeneratedValue
	UUID id;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userId")
	User userDetail;
	
	int amount;
	
	@ManyToMany(targetEntity = OrderItem.class)
	@JoinColumn(name = "orderItemList")
	List<OrderItem> orderItemList = new ArrayList<OrderItem>();
	
}
