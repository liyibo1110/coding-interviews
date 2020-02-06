package com.github.liyibo1110.coding.interviews.cp4;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目35 复杂链表的复制
 * 给定一个复杂链表，生成一个克隆链表
 * 额外特征：复杂链表和单向链表相比，还增加了一个随机节点指向
 * 额外要求：时间复杂度为O(n)，不要使用额外的空间
 * @author liyibo
 */
public class CloneLinkedList35 {

	private static class RandomListNode {
		
		int value;
		RandomListNode next;
		RandomListNode random;
		
		RandomListNode(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 解法一：计数法（时间复杂度是O(n^2)）
	 */
	public RandomListNode clone1(RandomListNode originHead) {
		
		if (originHead == null) return null;
		
		// 第一步：按原始链表的顺序，生成新的链表，并前后链接
		RandomListNode newHead = copy(originHead);
		// 第二步，遍历旧链表，找到每个节点的random节点的位置，然后在新链表中循环找到并复制
		RandomListNode curOriginNode = originHead;
		RandomListNode curNewNode = newHead;
		// 2条链表长度是一样的，可以一起后移
		while (curOriginNode != null) {
			// 找到旧链表当前节点的random节点位置
			int index = findRandom(originHead, curOriginNode);
			setRandomNode(newHead, curNewNode, index);
			curOriginNode = curOriginNode.next;
			curNewNode = curNewNode.next;
		}
		
		return newHead;
	}
	
	/**
	 * 解法二：哈希辅助法（时间空间复杂度都是O(n)）
	 */
	public RandomListNode clone2(RandomListNode originHead) {
		
		if (originHead == null) return null;
		
		// 第一步：按原始链表的顺序，生成新的链表，并前后链接
		RandomListNode newHead = copy(originHead);
		// 第二步生成原节点和新节点的hash表
		Map<RandomListNode, RandomListNode> nodeMap = fillMap(originHead, newHead);
		// 第三步，给新链表寻找并附加random节点
		RandomListNode curOriginNode = originHead;
		while (curOriginNode != null) {
			RandomListNode curNewNode = nodeMap.get(curOriginNode);
			if (curOriginNode.random != null) {
				curNewNode.random = nodeMap.get(curOriginNode.random);
			}
			curOriginNode = curOriginNode.next;
		}
		return newHead;
	}
	
	/**
	 * 解法二：追加+random赋值+奇偶分离三步法（时间复杂度为O(n)，且不需要额外hash结构）
	 */
	public RandomListNode clone3(RandomListNode originHead) {
		
		// 第一步：将原始链表每个节点增加一个一样的新节点，追加在后面
		copyNode(originHead);
		// 第二步：给倍化链表的冗余节点赋值random
		setRandomNode(originHead);
		// 第三步：将倍化完成链表拆分
		return splitList(originHead);
	}
	
	private void copyNode(RandomListNode head) {
		
		RandomListNode curNode = head;
		while (curNode != null) {
			RandomListNode newNode = new RandomListNode(curNode.value);
			newNode.next = curNode.next;
			curNode.next = newNode;
			curNode = curNode.next;
		}
	}
	
	private void setRandomNode(RandomListNode head) {
		RandomListNode curNode = head;
		while (curNode != null) {
			RandomListNode newNode = curNode.next;
			if (curNode.random != null) {
				newNode.random = curNode.random.next;
			}
			curNode = newNode.next;
		}
	}
	
	private RandomListNode splitList(RandomListNode head) {
		
		RandomListNode curNode = head;
		RandomListNode newHead = curNode.next;
	
		while (curNode != null) {
			RandomListNode newNode = curNode.next;
			curNode.next = curNode.next.next;
			if (newNode.next != null) {
				newNode.next = newNode.next.next;
			}
			curNode = curNode.next;	// 注意这里是跳2步，上面已经修改next指向了
		}
		
		return newHead;
	}
	
	/**
	 * 复制一个新链表（只复制next字段部分，不复制random部分）
	 */
	private RandomListNode copy(final RandomListNode originHead) {
		
		RandomListNode curOriginNode = originHead;
		// 显式生成头节点
		RandomListNode newHead = new RandomListNode(curOriginNode.value);
		RandomListNode curNewNode = newHead;
		curOriginNode = curOriginNode.next;
		// 循环生成后面的节点
		while (curOriginNode != null) {
			curNewNode.next = new RandomListNode(curOriginNode.value);
			curNewNode = curNewNode.next;
			curOriginNode = curOriginNode.next;
		}
		return newHead;
	}
	
	/**
	 * 从head开始找node的random，并返回下标，head下标为0
	 */
	private int findRandom(final RandomListNode head, final RandomListNode node) {
		int index = 0;
		RandomListNode randomNode = node.random;
		if (randomNode == null) return -1;
		RandomListNode curNode = head;
		while (curNode != null && curNode != randomNode) {
			curNode = curNode.next;
			index++;
		}
		return index;
	}
	
	/**
	 * 给指定的node的random进行设置，设置的节点为从head开始走index步
	 */
	private void setRandomNode(final RandomListNode head, final RandomListNode node, int index) {
		
		RandomListNode curNode = head;
		for (int i = 0; i < index; i++) {
			curNode = curNode.next;
		}
		// 循环完事的curNode就是要配置的random节点
		node.random = curNode;
	}
	
	/**
	 * 将2条链表节点对应生成HashMap
	 */
	private Map<RandomListNode, RandomListNode> fillMap(RandomListNode originHead, RandomListNode newHead) {
		
		Map<RandomListNode, RandomListNode> nodeMap = new HashMap<>();
		RandomListNode curOriginNode = originHead;
		RandomListNode curNewNode = newHead;
		// 2条链表长度是一样的，可以一起后移
		while (curOriginNode != null) {
			nodeMap.put(curOriginNode, curNewNode);
			curOriginNode = curOriginNode.next;
			curNewNode = curNewNode.next;
		}
		return nodeMap;
	}
}
