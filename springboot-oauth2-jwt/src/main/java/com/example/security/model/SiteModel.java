package com.example.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteModel {

	private Long id;

	private String name;

	private String address;

	private String zip;
	
	private String userName;
}
