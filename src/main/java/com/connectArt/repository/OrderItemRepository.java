package com.connectArt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.connectArt.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID>{
	
	@Query(value = "DELETE FROM order_item WHERE order_id = ?1", nativeQuery = true)
	public void deleteOrderzOrderItems(UUID orderId);
	
	
	
}
