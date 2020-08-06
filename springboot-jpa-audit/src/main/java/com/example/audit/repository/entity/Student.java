package com.example.audit.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Harikrishnan P
 * Class Student
 */
@Getter
@Setter
@Entity
public class Student extends AuditEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;

	private int  mark;
	
	
}
