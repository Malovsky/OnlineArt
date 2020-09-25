package com.connectArt.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.connectArt.model.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
	
	@Query(value = "SELECT * FROM orderz WHERE user_id = ?1", nativeQuery = true)
	List<Order> findUsersOrders(UUID userId);
	
	@Query(value = "DELETE FROM orderz WHERE user_id = ?1", nativeQuery = true)
	public void deleteUsersOrders(UUID userId);
	
	@Query(value = "DELETE FROM orderz_order_item_list WHERE order_id = ?1", nativeQuery = true)
	public void deleteOrderzOrderItemsList(UUID orderId);
	
}
