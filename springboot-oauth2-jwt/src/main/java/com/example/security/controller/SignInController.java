package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.UserModel;
import com.example.security.service.SignInService;

@RestController
public class SignInController {
	
	@Autowired
	private SignInService signInService;

	@PostMapping("/api/signin")
	public UserModel signIn(@RequestBody UserModel userModel) {
		
		 return signInService.createUser(userModel);
		
	}
}
