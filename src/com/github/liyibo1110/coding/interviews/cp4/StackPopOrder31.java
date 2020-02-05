package com.github.liyibo1110.coding.interviews.cp4;

import java.util.Stack;

/**
 * 题目31 栈的压入、弹出序列
 * 给定2个数字序列，判断后者的序列能不能由前者的压入顺序弹出来
 * 额外特征：序列数字都不一样，2个序列长度相等
 * 额外要求：无
 * @author liyibo
 */
public class StackPopOrder31 {

	public boolean isPopOrder(int[] pushArray, int[] popArray) {
		
		// 辅助栈
		Stack<Integer> stack = new Stack<>();
		int pushArrayIndex = 0;
		for (int i = 0; i < popArray.length; i++) {
			// 如果辅助栈为空，或者栈顶不是当前pop元素，则继续压入一个pushArray元素
			while (stack.isEmpty() || stack.peek() != popArray[i]) {
				// 说明没了，匹配失败
				if (pushArrayIndex >= pushArray.length) {
					return false;
				}
				stack.push(pushArray[pushArrayIndex]);
				pushArrayIndex++;
			}
			
			if (stack.peek() == popArray[i]) {
				stack.pop();
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		StackPopOrder31 obj = new StackPopOrder31();
		int[] pushArray = {1,2,3,4,5};
		int[] popArray = {4,5,3,2,1};
		System.out.println(obj.isPopOrder(pushArray, popArray));
	}
}
