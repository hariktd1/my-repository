package com.data.parser.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
/**
 * 
 * @author harikrishnan
 *
 */
@Data
@Builder
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private CardType cardType;

}
