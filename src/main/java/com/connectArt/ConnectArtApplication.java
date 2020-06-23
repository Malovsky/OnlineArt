package com.connectArt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.connectArt.config.SwaggerConfig;

@SpringBootApplication
public class ConnectArtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectArtApplication.class, args);
	}

}
