package com.example.audit.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentModel {
	
	private Long id;
	
	private String name;

	private int  mark;
	
	private LocalDateTime created;
	
	private LocalDateTime modified;
	
	private String createdBy;
	
	private String modifiedBy;

}
