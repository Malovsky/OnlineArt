package com.connectArt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.connectArt.model.User;
import com.connectArt.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
public class UserServiceTest {
	
	@Autowired
	UserRepository userRepo;
	
	@Test
	public void testSaveUser() {
		User user = new User (null, "KEVIN", "KEVIN1", "email@gmail.com", null);
		userRepo.save(user);
		User user2 = userRepo.findByUsername("KEVIN").get();
		assertNotNull(user);
		assertEquals(user.getUsername(), user2.getUsername());
		assertEquals(user.getEmail(), user2.getEmail());
	}
	
	@Test
	public void testFindAll() {
		User user = new User (null, "KEVIN", "KEVIN1", "email@gmail.com", null);
		userRepo.save(user);
		assertNotNull(userRepo.findAll());
	}
	
	@Test
	public void testGetUserByUsername() {
		User user = new User (null, "KEVIN", "KEVIN1", "email@gmail.com", null);
		userRepo.save(user);
		User user2 = userRepo.findByUsername("KEVIN").get();
		assertNotNull(user);
		assertEquals(user.getUsername(), user2.getUsername());
		assertEquals(user.getEmail(), user2.getEmail());
	}
	
	@Test
	public void testDeleteUser() {
		User user = new User (null, "KEVIN", "KEVIN1", "email@gmail.com", null);
		userRepo.save(user);
		userRepo.delete(user);
	}
	
}
