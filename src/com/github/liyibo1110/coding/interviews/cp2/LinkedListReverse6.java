package com.github.liyibo1110.coding.interviews.cp2;

import java.util.Stack;

/**
 * 题目6 从尾到头打印链表
 * 给定一个已有链表头，从尾到头打印每个节点的值
 * 额外要求：无
 * @author liyibo
 *
 */
public class LinkedListReverse6 {

	private static class ListNode {
		
		int value;
		ListNode next = null;
		
		ListNode(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 方案一：引入栈，从头遍历，将value放入栈中，再遍历栈（最为简单，但是要用额外的栈结构）
	 */
	public void printFromTailToHead1(ListNode head) {
		
		if (head == null) return;
		
		Stack<Integer> stack = new Stack<>();
		for (ListNode node = head; node != null; node = node.next) {
			stack.push(node.value);
		}
		
		while (!stack.empty()) {
			System.out.println(stack.pop());
		}
	}
	
	/**
	 * 方案二：递归法，从头遍历，将子节点递归调用，递归自身特性之一就是从后往前结束的（缺点是递归层数是有实际限制的，因此不实用）
	 */
	public void printFromTailToHead2(ListNode head) {
		
		if (head == null) return;
		// 注意顺序
		printFromTailToHead2(head.next);
		System.out.println(head.value);
	}
	
	/**
	 * 方案三：头插法，引入一个新的链表（还要有一个虚拟的头节点），从头遍历，将每个节点反向插入新链表（缺点也是需要额外引入新链表，而且实现啰嗦）
	 */
	public void printFromTailToHead3(ListNode head) {
		
		if (head == null) return;
		
		// 虚拟头节点
		ListNode newHead = new ListNode(-1);
		while (head != null) {
			// 额外标记下一个
			ListNode next = head.next;
			head.next = newHead.next;
			newHead.next = head;
			head = next;
		}
		
		// 遍历打印
		newHead = newHead.next;
		for (ListNode node = newHead; node != null; node = node.next) {
			System.out.println(node.value);
		}
	}
	
	public static void main(String[] args) {
		
		// 例子链表
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		
		LinkedListReverse6 obj = new LinkedListReverse6();
		obj.printFromTailToHead3(node1);
	}
}
