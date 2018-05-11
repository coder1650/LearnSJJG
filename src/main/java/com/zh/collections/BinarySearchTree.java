package com.zh.collections;

import com.zh.source.UnderflowException;

/**
 * 二叉查找树的简单实现
 * 二叉查找树的特点：左子节点的值小于父节点，右子节点的值大于父节点
 * @author Administrator
 *
 * @param <AnyType>
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

	private static class BinaryNode<AnyType>{
		private AnyType element;
		private BinaryNode<AnyType> left;
		private BinaryNode<AnyType> right;
		
		public BinaryNode(AnyType element){
			this(element,null,null);
		}
		
		public BinaryNode(AnyType element,BinaryNode<AnyType> left,BinaryNode<AnyType> right){
			this.element = element;
			this.left = left;
			this.right = right;
		}
	}
	//根节点
	private BinaryNode<AnyType> root;
	
	//构造一个空树
	public BinarySearchTree(){
		this.root = null;
	}
	
	public void makeEmpty(){
		this.root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	/**
	 * 判断树中是否包含指定元素
	 * @param x
	 * @return
	 */
	public boolean contains(AnyType x){
		return contains(root,x);
	}
	
	//从指定节点开始往后搜索指定元素
	private boolean contains(BinaryNode<AnyType> root,AnyType x){
		if(root == null){
			return false;
		}
		int compareResult = x.compareTo(root.element);
		if(compareResult > 0){
			return contains(root.right, x);
		}else if(compareResult < 0){
			return contains(root.left, x);
		}else{
			return true;
		}
		
	}
	
	/**
	 * 查找树中的最小值
	 * @return
	 */
	public AnyType findMin(){
		if(isEmpty()){
			throw new UnderflowException();
		}
		return findMin(root).element;
	}
	
	/**
	 * 查找拥有最小值的节点
	 * @param root
	 * @return
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> root){
		if(root == null){
			return null;
		}
		if(root.left == null){
			return root;
		}
		return findMin(root.left);//未递归，可以使用while循环代替
	}
	
	/**
	 * 查找树中的最大值
	 * @return
	 */
	public AnyType findMax(){
		if(isEmpty()){
			throw new UnderflowException();
		}
		return findMax(root).element;
	}
	
	/**
	 * 查找树中最大值所在的节点
	 * @param root
	 * @return
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> root){
		if(root != null){
			while(root.right != null){
				root = root.right;
			}
		}
		return root;
	}
	
	public void insert(AnyType x){
		root = insert(x,root);
	}
	
	
	private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> root){
		if(root == null){
			root = new BinaryNode(x,null,null);
		}
		int compareResult = x.compareTo(root.element);
		if(compareResult > 0){
			root.right = insert(x,root.right);
		}else if(compareResult < 0){
			root.left = insert(x,root.left);
		}else{
			;
		}
		return root;
	}
	
	public void remove(AnyType x){
		root = remove(x,root);
	}
	
	public void printTree(){
		
	}
}
