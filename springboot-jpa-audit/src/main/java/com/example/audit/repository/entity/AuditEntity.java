package com.example.audit.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
/**
 * 
 * @author Harikrishnan P
 * Base class for audit which can be extended to other classes
 *
 */

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditEntity {
	
	@CreatedDate
	private LocalDateTime created;
	
	@LastModifiedDate
	private LocalDateTime modified;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String modifiedBy;

}
