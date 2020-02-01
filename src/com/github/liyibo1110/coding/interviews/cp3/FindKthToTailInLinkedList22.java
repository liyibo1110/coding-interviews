package com.github.liyibo1110.coding.interviews.cp3;

/**
 * 题目22 链表中倒数第k个节点
 * 给定一个链表，输出倒数第k个节点
 * 额外特征：无
 * 额外要求：只能遍历一次
 * @author liyibo
 *
 */
public class FindKthToTailInLinkedList22 {

	private static class ListNode {
		
		int value;
		ListNode next = null;
		
		ListNode(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 双指针法，但要处理好特殊情况，如链表总数小于k
	 */
	public ListNode findKthToTail(ListNode head, int k) {
		
		if (head == null || k <= 0) return null;
		ListNode fastNode = head;
		ListNode slowNode = head;
		// fast节点先移动k-1步
		for (int i = 0; i < k - 1; i++) {
			// 可能链表过短，或者k过大（一个意思）
			if (fastNode.next == null) return null;
			fastNode = fastNode.next;
		}
		// 同时移动直到fast节点到尾节点
		while (fastNode.next != null) {
			fastNode = fastNode.next;
			slowNode = slowNode.next;
		}
		return slowNode;
	}
}
