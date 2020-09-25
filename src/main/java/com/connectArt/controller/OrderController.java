package com.connectArt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectArt.model.Order;
import com.connectArt.model.OrderItem;
import com.connectArt.repository.OrderItemRepository;
import com.connectArt.repository.OrderRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	OrderItemRepository orderItemRepo;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Order>> getAll() {
		List<Order> orderList = orderRepo.findAll();
		return ResponseEntity.ok(orderList);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOneById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(orderRepo.getOne(id));
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Order order) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		order.setOrderItemList(orderItemList);
		orderRepo.save(order);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody Order order) {
		orderRepo.save(order);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
		orderRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/myOrders/{id}")
	public ResponseEntity<List<Order>> getMyOrders(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(orderRepo.findUsersOrders(id));
	}
	
}
