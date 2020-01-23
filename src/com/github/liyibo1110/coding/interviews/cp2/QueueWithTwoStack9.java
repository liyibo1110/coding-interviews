package com.github.liyibo1110.coding.interviews.cp2;

import java.util.Stack;

/**
 * 题目9 用两个栈实现队列
 * 用两个栈实现一个列表的尾部插入和头部提取的方法
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class QueueWithTwoStack9 {

	/**
	 * 方法比较多，总之是可让stack1用来添加，stack2用来返回，来回倒腾一下
	 */
	private static class QueueWithTwoStack {
		
		private Stack<Integer> stack1 = new Stack<>();
		private Stack<Integer> stack2 = new Stack<>();

		public void addToTail(int value) {
			
			stack1.push(value);
		}
		
		public Integer takeFromHead() {
			
			while(!stack1.empty()) {
				stack2.push(stack1.pop());
			}
			Integer result = stack2.pop();
			while(!stack2.empty()) {
				stack1.push(stack2.pop());
			}
			return result;
		}
	}
	
	public static void main(String[] args) {
		
		QueueWithTwoStack queue = new QueueWithTwoStack();
		queue.addToTail(1);
		queue.addToTail(2);
		queue.addToTail(3);
		System.out.println(queue.takeFromHead());
		System.out.println(queue.takeFromHead());
		System.out.println(queue.takeFromHead());
	}
}
