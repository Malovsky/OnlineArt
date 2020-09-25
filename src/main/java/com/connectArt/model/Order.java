package com.connectArt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	int nbItem;
	
	@JsonIgnore
	@ManyToMany(targetEntity = OrderItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "orderItemList")
	List<OrderItem> orderItemList = new ArrayList<OrderItem>();
	
	public void removeIdFromOrderItem(OrderItem orderitem) {
		if (getOrderItemList() != null) {
			getOrderItemList().remove(orderitem);
		}
	}
	
}


//public void removeIncredianToBaseProduct(ProductOfIngrediant productOfIngrediant) {
//    if (getProductOfIngrediants() != null) {
//        getProductOfIngrediants().remove(productOfIngrediant);
//    }
//}
