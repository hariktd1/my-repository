package com.example.mongo.audit.repository.documents;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
/**
 * 
 * @author Harikrishnan P
 * Base class for audit which can be extended to other classes
 *
 */

@Data
public class AuditDocument {
	
	@CreatedDate
	private LocalDateTime created;
	
	@LastModifiedDate
	private LocalDateTime modified;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String modifiedBy;

}
