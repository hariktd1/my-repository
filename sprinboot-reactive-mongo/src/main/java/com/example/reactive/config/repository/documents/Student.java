package com.example.reactive.config.repository.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Harikrishnan P 
 * Class Student
 */
@Getter
@Setter
@Document
public class Student {

	@Id
	private String id;

	private String name;

	private int mark;

}
