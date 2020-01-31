package com.github.liyibo1110.coding.interviews.cp3;

/**
 * 题目18 删除链表的节点
 * 提供一个方法，参数为头节点和指定节点，要求删除指定节点
 * 额外特征：无
 * 额外要求：时间复杂度要为O(1)
 * @author liyibo
 *
 */
public class DeleteNode18 {

	private static class ListNode {
		
		int value;
		ListNode next = null;
		
		ListNode(int value) {
			this.value = value;
		}
	}
	
	public void deleteNode(ListNode head, ListNode deletedNode) {
		
		if (head == null || deletedNode == null) return;
	
		if (deletedNode.next != null) {
			// 将删除节点下一个节点覆盖删除节点，然后断开下一个节点
			ListNode node = deletedNode.next;
			deletedNode.value = node.value;
			deletedNode.next = node.next;
		} else if (head == deletedNode){
			// 要删除的节点如果就是头节点
			head = head.next;
		} else {
			// 要删除的节点是尾节点，只能从头遍历
			ListNode node = head;
			while(node.next != deletedNode) {
				node = node.next;
			}
			node.next = null; //因为是最后一个，直接置空即可
		}
	}
	
	public ListNode deleteDuplicate(ListNode head) {
		
		if (head == null || head.next == null) return head;
		
		ListNode preNode = null;
		ListNode curNode = head;
		while(curNode != null && curNode.next != null) {
			
			if (curNode.value == curNode.next.value) {
				int value = curNode.value;
				// 可能重复值大于2个节点，需要一直遍历跳过
				while (curNode != null && curNode.value == value) {
					curNode = curNode.next;
				}
				// 判断头节点是否即为重复
				if (preNode == null) {
					head = curNode;
				} else {
					preNode.next = curNode;
				}
			} else {
				preNode = curNode;
				curNode = curNode.next;
			}
		}
		
		return head;
	}
}
