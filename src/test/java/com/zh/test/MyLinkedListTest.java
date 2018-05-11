package com.zh.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.zh.collections.MyLinkedList;

public class MyLinkedListTest {
	
	@Test
	public void testAdd(){
		MyLinkedList<String> list = new MyLinkedList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			String val = it.next();
			if("b".equals(val)){
				it.remove();
			}
		}
		Iterator<String> it2 = list.iterator();
		while(it2.hasNext()){
			System.out.println(it2.next());
		}
	}
	
	@Test
	public void test01(){
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		printList(list.iterator());
		
	}
	
	private void printList(Iterator<String> it){
		if(!it.hasNext()){
			return;
		}
		System.out.println(it.next());
		printList(it);
	}

}
