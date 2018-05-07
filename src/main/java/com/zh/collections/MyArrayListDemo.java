package com.zh.collections;

import java.util.Iterator;

public class MyArrayListDemo<AnyType> implements Iterable<AnyType>{
	private static final int DEFAULT_CAPACITY = 10;
	private AnyType[] theItems;
	private int theSize;
	
	public MyArrayListDemo(){
		clear();
	}
	
	/**
	 * 当前容器大小
	 * @return
	 */
	public int size(){
		return theSize;
	}
	
	/**
	 * 扩容
	 * @param newCapacity
	 */
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int newCapacity){
		if(newCapacity < theSize){
			return;
		}
		AnyType[] oldItems = theItems;
		theItems = (AnyType[]) new Object[newCapacity];
		for(int i=0;i<size();i++){
			theItems[i] = oldItems[i];
		}
	}
	
	/**
	 * 返回指定索引上的值
	 * @param index
	 * @return
	 */
	public AnyType get(int index){
		if(index < 0 || index >= size()){
			throw new ArrayIndexOutOfBoundsException("Index " + index + "; size " + size( ));
		}
		return theItems[index];
	}
	
	/**
	 * 在数组末尾插入
	 * @param x
	 */
	public void add(AnyType x){
		add(size(),x);
	}
	
	/**
	 * 在指定位置插入
	 * @param index
	 * @param x
	 */
	public void add(int index,AnyType x){
		if(theItems.length == size()){
			ensureCapacity(size() * 2 + 1);
		}
		for(int i=theSize;i>index;i--){
			theItems[i] = theItems[i-1];
		}
		theItems[index] = x;
		theSize++;
	}
	
	/**
	 * 删除指定索引上的值，并返回
	 * @param index
	 * @return
	 */
	public AnyType remove(int index){
		if(index < 0 || index >= size()){
			throw new ArrayIndexOutOfBoundsException("Index " + index + "; size " + size( ));
		}
		AnyType isRemoved = theItems[index];
		for(int i=index;i<size()-1;i++){
			theItems[index] = theItems[index+1];
		}
		theItems[theSize--] = null;
		return isRemoved;
	}
	
	
	public void clear(){
		doClear();
	}
	
	public void doClear(){
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	

	public String toString( )
    {
            StringBuilder sb = new StringBuilder( "[ " );

            for( AnyType x : this.theItems )
                sb.append( x + " " );
            sb.append( "]" );

            return new String( sb );
    }

	@Override
	public Iterator<AnyType> iterator() {
		return null;
	}

}
