package com.data.parser.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;
/**
 * 
 * @author harikrishnan
 *
 */
@Data
@Builder
public class CardType implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private List<TreeNode<CardDetail>> tree;

}
