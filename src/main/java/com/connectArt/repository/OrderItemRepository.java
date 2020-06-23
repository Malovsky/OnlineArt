package com.connectArt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connectArt.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID>{
	
	
	
}
