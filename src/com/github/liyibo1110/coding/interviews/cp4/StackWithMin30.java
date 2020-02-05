package com.github.liyibo1110.coding.interviews.cp4;

import java.util.Stack;

/**
 * 题目30 包含min函数的栈
 * 设计一个栈，带min方法，返回栈内的最小值
 * 额外特征：无
 * 额外要求：min、push、pop方法时间复杂度必须O(1)
 * @author liyibo
 */
public class StackWithMin30 {

	/**
	 * 本方式不是最简写法，而且没有经过测试
	 */
	private static class StackWithMin<T extends Comparable<T>> {
		
		private Stack<T> stack = new Stack<>();
		
		// 辅助栈，用来存储比当前更小的值
		private Stack<T> minStack = new Stack<>();
		
		public void push(T obj) {
			
			// 尝试从minStack中获取当前最小值，用来比较
			if (!minStack.isEmpty()) {
				T minObj = minStack.peek();
				if (obj.compareTo(minObj) <= 0) {
					minStack.push(obj);
				}
			} else {
				minStack.push(obj);
			}
			stack.push(obj);
		}
		
		public T pop() {
			
			if (stack.isEmpty()) return null;
			
			// 尝试从minStack中移除，前提是最小值（不用判断一定有值）
			T minObj = minStack.peek();
			T obj = stack.pop();
			if (minObj.compareTo(obj) == 0) {
				minStack.pop();
			}
			return obj;
		}
	}
}
