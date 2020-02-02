package com.github.liyibo1110.coding.interviews.cp3;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目23 链表中环的入口节点
 * 给定一个链表，检测是否有环，如果有环，环的入口在哪儿，返回这个入口节点
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class EntryNodeOfLoop23 {

	private static class ListNode {
		
		int value;
		ListNode next = null;
		
		ListNode(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 双指针法，比较经典（这里分了2步，流程更清晰）
	 */
	public ListNode entryNodeOfLoop1(ListNode head) {
		
		if (head == null) return null;
		
		// 先检测有没有环，并接收相遇点
		ListNode meetingNode = hasLoop(head);
		if (meetingNode == null) return null;
		
		// 这时将头结点和相遇节点一起移动，当再次相遇即为环的入口
		ListNode curNode = head;
		while (curNode != meetingNode) {
			curNode = curNode.next;
			meetingNode = meetingNode.next;
		}
		return curNode;
	}
	
	/**
	 * 判断该链表是否有环，有则返回相遇的节点
	 */
	private ListNode hasLoop(ListNode head) {
		
		ListNode fastNode = head;
		ListNode slowNode = head;
		// fast每次走2格，所以要判断当前和下个节点
		while (fastNode != null && fastNode.next != null) {
			// 先移动
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
			if (fastNode == slowNode) return slowNode;
		}
		return null;
	}
	
	/**
	 * 哈希法，因为引入了额外的现成结构，不一定能满足面试要求
	 */
	public ListNode entryNodeOfLoop2(ListNode head) {
		
		if (head == null) return null;
		
		Set<ListNode> set = new HashSet<>();
		ListNode curNode = head;
		while (curNode != null) {
			if (set.contains(curNode)) {
				return curNode;
			}
			set.add(curNode);
			curNode = curNode.next;
		}
		return null;
	}
}
