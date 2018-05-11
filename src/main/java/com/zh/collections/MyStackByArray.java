package com.zh.collections;

import java.util.Arrays;

/**
 * 用数组实现的栈
 * @author Administrator
 *
 */
public class MyStackByArray<AnyType> {
	
	private Object[] elementData;
	private int topOfStack;
	private int theSize;
	private static final int DEFAULT_CAPACITY = 10;
	private static final Object[] EMPTY_ELEMENTDATA = {};
	
	public MyStackByArray(){
		this.theSize = 0;
		this.topOfStack = -1;
		this.elementData = EMPTY_ELEMENTDATA;
	}
	
	public int size(){
		return this.theSize;
	}
	
	public boolean isEmpty(){
		return this.theSize == 0;
	}
	
	public void clear(){
		for(int i = 0;i<theSize;i++){
			elementData[i] = null;
		}
		theSize = 0;
		topOfStack = -1;
	}
	
	public void add(AnyType e){
		ensureCapacity(theSize+1);
		elementData[++topOfStack] = e;
		theSize++;
	}
	
	@SuppressWarnings("unchecked")
	public AnyType top(){
		if(isEmpty()){
			return null;
		}
		theSize--;
		AnyType val = (AnyType) elementData[topOfStack];
		elementData[topOfStack] = null;
		topOfStack--;
		return val;
	}
	
	public AnyType pop(){
		if(isEmpty()){
			return null;
		}
		return (AnyType) elementData[topOfStack];
	}
	
	private void ensureCapacity(int minCapacity){
		if(elementData == EMPTY_ELEMENTDATA){
			minCapacity = Math.max(minCapacity, DEFAULT_CAPACITY);
		}
		if(minCapacity > elementData.length){
			grow(minCapacity);
		}
	}
	
	private void grow(int minCapacity){
		int oldLength = elementData.length;
		int newCapacity = oldLength + (oldLength >> 1);
		if(minCapacity > newCapacity)
			newCapacity = minCapacity;
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

}
