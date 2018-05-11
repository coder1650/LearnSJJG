package com.zh.collections;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 基于栈实现的算法
 * 包括：中缀表达式转后缀表达式
 * @author Administrator
 *
 */
public class AppByStack {
	private static MyStackByArray<String> stack = new MyStackByArray<>();
	private static final String[] DEFAULT_OPERATORS = new String[]{"+","-","*","(",")"};
	
	/**
	 * 中缀表达式转后缀表达式
	 * @param infix
	 * @return
	 */
	public static String infixToPostfix(String infix){
		StringBuffer postfix = new StringBuffer();
		char[] chars = infix.toCharArray();
		for (int i = 0;i<chars.length;i++) {
			String element = String.valueOf(chars[i]);
			if(i==chars.length-1){
				postfix.append(chars[i]);
				String ele = stack.top();
				postfix.append(ele);
				while((ele=stack.top()) != null){
					postfix.append(ele);
				}
				break;
			}
			if(!element.isEmpty() && ArrayUtils.contains(DEFAULT_OPERATORS, element)){
				String top = stack.pop();
				if(top == null){
					stack.add(element);
					continue;
				}
				int elementNum = operatorToInt(element);
				int topNum = operatorToInt(top);
				if(elementNum == 0){
					top = stack.top();
					while(topNum != 100){
						postfix.append(top);
						topNum = operatorToInt(stack.top());
						if(topNum < 0){
							break;
						}
					}
					continue;
				}
				if(elementNum > topNum){
					stack.add(element);
					continue;
				}
				while(elementNum <= topNum){
					if(topNum < 0 || topNum == 100){
						break;
					}
					postfix.append(stack.top());
					topNum = operatorToInt(stack.pop());
				}
				stack.add(element);
				continue;
			}
			if(!element.trim().isEmpty()){
				postfix.append(element);
			}
		}
		return postfix.toString();
	}
	
	private static int operatorToInt(String operator){
		if(StringUtils.isBlank(operator)){
			return -1;
		}
		int num;
		switch(operator){
		case "+":
			num = 10;break;
		case "-":
			num = 10;break;
		case "*":
			num = 20;break;
		case ")":
			num = 0;break;
		case "(":
			num = 100;break;
		default:num = -1;
		}
		return num;
	}
	
	
	public static void main(String[] args) {
		String operator = "a + b * c + (d*e+f) * g";
		System.out.println(infixToPostfix(operator));
//		System.out.println(operatorToInt("*"));
	}

}
