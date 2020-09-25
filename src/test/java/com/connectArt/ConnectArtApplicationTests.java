package com.connectArt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.connectArt.model.User;
import com.connectArt.repository.UserRepository;

@SpringBootTest
class ConnectArtApplicationTests {
	
	@Autowired
	UserRepository userRepo;
	
	@Test
	public void testgetUserById() {
		User user = new User (UUID.randomUUID(), "KEVIN", "KEVIN1", "email@gmail.com", null);
		userRepo.save(user);
		User user2 = userRepo.getOne(user.getId());
		assertNotNull(user2);
		assertEquals(user2.getId(), user.getId());
	}
	
	@Test
	public void testFindAll() {
		User user = new User (UUID.randomUUID(), "KEVIN", "KEVIN1", "email@gmail.com", null);
		userRepo.save(user);
		assertNotNull(userRepo.findAll());
	}

}
