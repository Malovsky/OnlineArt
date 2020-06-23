package com.connectArt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connectArt.model.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
	
	
	
}
