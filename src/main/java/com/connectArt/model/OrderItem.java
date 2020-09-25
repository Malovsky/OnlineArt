package com.connectArt.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
	
	@Id
	@GeneratedValue
	@NotFound(action = NotFoundAction.IGNORE)
	UUID id;
	
	@ManyToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", nullable = false)
	Order order;
	
	@ManyToOne(targetEntity = ArtWork.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "artWorkId", nullable = false)
	ArtWork artWork;
	
	int quantity;
	
	public OrderItem(Order order, ArtWork artWork, int quantity) {
		super();
		this.order = order;
		this.artWork = artWork;
		this.quantity = quantity;
	}
	
}
