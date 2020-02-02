package com.github.liyibo1110.coding.interviews.cp3;

import java.util.Stack;

/**
 * 题目24 反转链表
 * 给定一个链表，反转链表结构，并返回新的头节点
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class ReverseLinkedList24 {

	private static class ListNode {
		
		int value;
		ListNode next = null;
		
		ListNode(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 解法一：额外栈存储，需要额外空间O(n)，不推荐
	 */
	public ListNode reverse1(ListNode head) {
		
		if (head == null) return null;
		Stack<ListNode> stack = new Stack<>();
		ListNode curNode = head;
		while (curNode != null) {
			stack.push(curNode);
			curNode = curNode.next;
		}
		// 开始从栈里取
		head = stack.pop();
		curNode = head;
		while (!stack.isEmpty()) {
			curNode.next = stack.pop();
			curNode = curNode.next;
		}
		return head;
	}
	
	/**
	 * 解法二：三指针法，分别指向前一个、当前、后一个节点，推荐
	 */
	public ListNode reverse2(ListNode head) {
		
		if (head == null) return null;
		ListNode curNode = head;
		ListNode preNode = null;
		ListNode nextNode = null;
		while (curNode != null) {
			nextNode = curNode.next;
			curNode.next = preNode;	// 反向修改next的指向
			// 前移
			preNode = curNode;
			curNode = nextNode;
		}
		return preNode;
	}
	
	/**
	 * 解法三：递归法，进阶学习
	 */
	public ListNode reverseRecursion(ListNode head) {
		
		if (head == null || head.next == null) return null;
		
		ListNode reverseHead = reverseRecursion(head.next);
		ListNode nextNode = head.next;
		nextNode.next = head;
		head.next = null;	// head已变成尾节点，清除next字段
		return reverseHead;
	}
}
