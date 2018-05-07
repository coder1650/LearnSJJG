package com.zh.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.zh.collections.MyArrayListDemo;

public class MyArrayListDemoTest {
	
	@Test
	public void test01(){
		MyArrayListDemo<Integer> list = new MyArrayListDemo<>();
//		MyArrayList<Integer> list = new MyArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}
		System.out.println(list.toString());
	}
	
	@Test
	public void test02(){
		System.out.println(getSum(3));
	}
	
	@Test
	public void test03(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(0,i);
		}
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}
	
	@Test
	public void test04(){
		List<Integer> list = new ArrayList<>();
		
		list.addAll(Arrays.asList(6,5,1,4,2));
		removeEventVer1(list);
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}
	
	@Test
	public void test05(){
		String[] s = new String[]{"a","b","c","d",null};
		System.arraycopy(s, 1, s, 2, 3);
		System.out.println(s);
	}
	
	public int getSum(int n){
		int sum = 0;
		if(n<1){
			return 1;
		}
		for(int i = 1;i<=n;i++){
			sum += i*i*i;
		}
		return sum;
	}
	
	public void removeEventVer1(List<Integer> list){
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			int tmp = it.next();
			if(tmp % 2 == 0)
				it.remove();
			
		}
	}

}
