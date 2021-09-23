package com.data.parser.dto;

import java.util.LinkedList;
import java.util.List;
/**
 * TreeNode for parent child relation
 * @author harikrishnan
 *
 * @param <T>
 */
public class TreeNode<T> {

	public T data;
	public TreeNode<T> parent;
	public List<TreeNode<T>> children;
	private List<TreeNode<T>> elementsIndex;

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	public TreeNode(T data) {
		this.data = data;
		this.children = new LinkedList<TreeNode<T>>();
		this.elementsIndex = new LinkedList<TreeNode<T>>();
		this.elementsIndex.add(this);
	}

	public TreeNode() {
		this.children = new LinkedList<TreeNode<T>>();
		this.elementsIndex = new LinkedList<TreeNode<T>>();
		this.elementsIndex.add(this);
	}

	public TreeNode<T> addChild(T child) {
		TreeNode<T> childNode = new TreeNode<T>(child);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public List<TreeNode<T>> getChildren() {
		return this.children;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	private void registerChildForSearch(TreeNode<T> node) {
		elementsIndex.add(node);
		if (parent != null) {
			parent.registerChildForSearch(node);
		}
	}

	
	@Override
	public String toString() {
		return data != null ? data.toString() : "[data null]";
	}

}
