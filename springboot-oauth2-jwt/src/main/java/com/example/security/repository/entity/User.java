package com.example.security.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	public enum Role {
		USER, ADMIN, USER_MANAGER
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String email;

	private String password;

	private Role role;

}
