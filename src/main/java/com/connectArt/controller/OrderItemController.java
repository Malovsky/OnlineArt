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

import com.connectArt.dto.CustomerCommandDTO;
import com.connectArt.email.EmailOrder;
import com.connectArt.model.ArtWork;
import com.connectArt.model.Order;
import com.connectArt.model.OrderItem;
import com.connectArt.model.User;
import com.connectArt.repository.ArtWorkRepository;
import com.connectArt.repository.OrderItemRepository;
import com.connectArt.repository.OrderRepository;
import com.connectArt.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@RestController
@RequestMapping("/api/orderItem")
@Slf4j
public class OrderItemController {
	
	@Autowired
	OrderItemRepository orderItemRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ArtWorkRepository artRepo;
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<OrderItem>> getAll() {
		log.info("Pass in getAll() - OrderItemController");
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
	
	@PostMapping("/itemOrderCustomerQuantity")
	public ResponseEntity<Void> finalPoint(@RequestBody CustomerCommandDTO ccDto) {
		List<OrderItem> orderItemList = new ArrayList<>();
		User user = userRepo.findById(ccDto.getUserId()).get();
		Order order = new Order();
		List<UUID> ArtworkIds = ccDto.getListArtIds();
		List<ArtWork> artworks = new ArrayList<ArtWork>();
		
		for (UUID id : ArtworkIds) {
			ArtWork art = artRepo.findById(id).get();
			artworks.add(art);
		}
		orderRepo.save(order);
		
		int amount = 0;
		int nbItems = 0;
		for (UUID id : ArtworkIds) {
			ArtWork art = artRepo.findById(id).get();
			art.setAvailability(art.getAvailability() - 1);
			artRepo.save(art);
			OrderItem orderItem = new OrderItem(order, art, 1);
			nbItems = nbItems + 1;
			amount = amount + art.getPrice();
			orderItemRepo.save(orderItem);
			orderItemList.add(orderItem);
		}
		order.setOrderItemList(orderItemList);
		order.setUserDetail(user);
		order.setAmount(amount);
		order.setNbItem(nbItems);
		orderRepo.save(order);
		user.getOrderList().add(order);
		
		EmailOrder.orderRecap(user, artworks);
		
		return ResponseEntity.ok().build();
	}
	
	
}
