package com.connectArt.controller;

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

import com.connectArt.model.OrderItem;
import com.connectArt.repository.OrderItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {
	
	@Autowired
	OrderItemRepository orderItemRepo;
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<OrderItem>> getAll() {
		List<OrderItem> orderItemList = orderItemRepo.findAll();
		return ResponseEntity.ok(orderItemList);
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<OrderItem> getOneById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(orderItemRepo.getOne(id));
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody OrderItem orderItem) {
		orderItemRepo.save(orderItem);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody OrderItem orderItem) {
		orderItemRepo.save(orderItem);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
		orderItemRepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
