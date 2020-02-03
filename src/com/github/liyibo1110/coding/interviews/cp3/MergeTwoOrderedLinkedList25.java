package com.github.liyibo1110.coding.interviews.cp3;

/**
 * 题目25 合并两个排序的链表
 * 给定2个已排序递增的链表，合并成一个新的递增链表
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class MergeTwoOrderedLinkedList25 {

	private static class ListNode {
		
		int value;
		ListNode next = null;
		
		ListNode(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 解法一：遍历非递归法
	 */
	public ListNode merge1(ListNode list1, ListNode list2) {
		
		if (list1 == null) return list2;
		if (list2 == null) return list1;
		
		ListNode curNode1 = list1;
		ListNode curNode2 = list2;
		ListNode head = null;
		
		// 先找出一个头节点
		if (curNode1.value <= curNode2.value) {
			head = curNode1;
			curNode1 = list1.next;
		} else {
			head = curNode2;
			curNode2 = list2.next;
		}
		
		// 新的头节点要留下来最终返回
		ListNode curNode = head;
		
		// 开始遍历2个子链表，按各种条件进行合并
		while (list1 != null || list2 != null) {
			
			// 子链表1到头了，把2剩余的直接附上去
			if (list1 == null) {
				curNode.next = list2;
				break;
			} else if (list2 == null) {
				curNode.next = list1;
				break;
			} else if (list1.value < list2.value) {
				// 从list1分离1个节点
				curNode.next = list1;
				curNode = curNode.next;
				list1 = list1.next;
			} else {
				// 从list2分离1个节点
				curNode.next = list2;
				curNode = curNode.next;
				list2 = list2.next;
			}
		}
		
		return head;
	}
	
	/**
	 * 解法二：递归法，简洁直观
	 */
	public ListNode merge2(ListNode list1, ListNode list2) {
		
		if (list1 == null) return list2;
		if (list2 == null) return list1;
		
		ListNode head = null;
		if (list1.value < list2.value) {
			head = list1;
			head.next = merge2(list1.next, list2);
		} else {
			head = list2;
			head.next = merge2(list1, list2.next);
		}
		return head;
	}
}
