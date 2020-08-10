package com.example.mongo.audit.repository.documents;

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
public class Student extends AuditDocument {

	@Id
	private String id;

	private String name;

	private int mark;

}
