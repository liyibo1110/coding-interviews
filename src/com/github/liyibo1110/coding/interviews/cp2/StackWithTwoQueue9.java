package com.github.liyibo1110.coding.interviews.cp2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目9-2 用两个队列实现栈
 * 用两个队列实现一个栈的压入和弹出的方法
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class StackWithTwoQueue9 {

	private static class StackWithTwoQueue {
		
		private Queue<Integer> queue1 = new LinkedList<>();
		private Queue<Integer> queue2 = new LinkedList<>();
	
		public void push(int value) {
			
			// 哪个队列不为空，就给谁追加，都为空就塞队列1里面
			if (queue1.isEmpty() && queue2.isEmpty()) {
				queue1.add(value);
			} else if (!queue1.isEmpty()) {
				queue1.add(value);
			} else {
				queue2.add(value);
			}
		}
		
		public Integer pop() {
			
			if (queue1.isEmpty() && queue2.isEmpty()) {
				return null;
			}
			
			if (!queue1.isEmpty()) {
				int size = queue1.size();
				for (int i = 0; i < size - 1; i++) {
					queue2.add(queue1.poll());
				}
				return queue1.poll();
			} else {
				int size = queue2.size();
				for (int i = 0; i < size - 1; i++) {
					queue1.add(queue2.poll());
				}
				return queue2.poll();
			}
		}
	}
	
	public static void main(String[] args) {
		
		StackWithTwoQueue stack = new StackWithTwoQueue();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
