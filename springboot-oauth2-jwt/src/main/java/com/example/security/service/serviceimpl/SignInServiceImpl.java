package com.example.security.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.model.UserModel;
import com.example.security.repository.UserRepository;
import com.example.security.repository.entity.User;
import com.example.security.service.SignInService;

@Service
public class SignInServiceImpl implements SignInService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserModel createUser(UserModel userModel) {
		
		User user = new User(null, userModel.getEmail(), passwordEncoder.encode(userModel.getPassword()),
				User.Role.USER);
		userRepository.save(user);
		userModel.setRole(user.getRole().name());
		return userModel;
	}

}
