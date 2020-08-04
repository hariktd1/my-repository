package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class SpringbootOauth2JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOauth2JwtApplication.class, args);
	}
	@GetMapping("/data")
	public String getData() {
		return "hello";
	}
}
