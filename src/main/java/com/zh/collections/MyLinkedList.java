package com.zh.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {
	private int theSize;
	private int modCount;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;
	
	private static class Node<AnyType>{
		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
		
		public Node(AnyType d,Node<AnyType> p,Node<AnyType> n){
			data = d;
			prev = p;
			next = n;
		}
	}
	
	public MyLinkedList(){
		doClear();
	}
	
	public void clear(){
		doClear();
	}
	
	private void doClear(){
		this.theSize = 0;
		modCount++;
		beginMarker = new Node<AnyType>(null,null,null);
		endMarker = new Node<AnyType>(null,beginMarker,null);
		beginMarker.next = endMarker;
	}
	
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public boolean add(AnyType x){
		add(size(),x);
		return true;
	}
	
	public void add(int idx,AnyType x){
		addBefore(getNode(idx,0,size()),x);
	}
	
	public AnyType get(int idx){
		return getNode(idx).data;
	}
	
	public AnyType set(int idx,AnyType newVal){
		Node<AnyType> n = getNode(idx);
		AnyType oldVal = n.data;
		n.data = newVal;
		return oldVal;
	}
	
	public AnyType remove(int idx){
		return remove(getNode(idx));
	}
	
	private void addBefore(Node<AnyType> p,AnyType x){
		Node<AnyType> pre = p.prev;
		Node<AnyType> newNode = new Node<>(x,pre,p);
		pre.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}
	
	private AnyType remove(Node<AnyType> p){
		AnyType oldVal = p.data;
		p.prev.next = p.next;
		p.next.prev = p.prev;
		p = null;
		theSize--;
		modCount++;
		return oldVal;
	}
	
	private Node<AnyType> getNode(int idx){
		return getNode(idx,0,size()-1);
	}
	
	private Node<AnyType> getNode(int idx,int lower,int upper){
		Node<AnyType> p;
		if(idx > upper || idx < lower){
			throw new IndexOutOfBoundsException();
		}
		if(idx < size()/2){
			p = beginMarker.next;
			for(int i = 0;i<idx;i++){
				p = p.next;
			}
		}else{
			p = endMarker;
			for(int i = size();i>idx;i--){
				p = p.prev;
			}
		}
		return p;
	}
	

	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<AnyType>{
		
		private Node<AnyType> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		public boolean hasNext(){
			return current != endMarker;
		}
		
		public AnyType next(){
			if(modCount != expectedModCount){
				throw new ConcurrentModificationException();
			}
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			AnyType data = current.data;
			current = current.next;
			okToRemove = true;
			return data;
		}

		@Override
		public void remove() {
			if(modCount != expectedModCount){
				throw new ConcurrentModificationException();
			}
			if(!okToRemove){
				throw new IllegalStateException();
			}
			MyLinkedList.this.remove(current.prev);
			expectedModCount++;
			okToRemove = false;
		}

		
	}

}
