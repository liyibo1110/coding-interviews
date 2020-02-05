package com.github.liyibo1110.coding.interviews.cp4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 题目32 从上到下打印二叉树
 * 层序遍历二叉树（包含各种变体）
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 */
public class BinaryTreeLevelOrder32 {

	private static class TreeNode {
		
		private int value;
		TreeNode left = null;
		TreeNode right = null;
		
		public TreeNode(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 解法一：辅助队列
	 */
	public List<Integer> printFromTopToBottom(TreeNode root) {
		
		List<Integer> list = new ArrayList<>();
		if (root == null) return list;
		// 利用辅助队列开始遍历
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			list.add(node.value);
			// 放入子节点
			if (node.left != null) queue.offer(node.left);
			if (node.right != null) queue.offer(node.right);
		}
		return list;
	}
	
	/**
	 * 变种，每层节点要用换行区别出来
	 */
	public void printFromTopToBottomWithLine(TreeNode root) {
		
		if (root == null) return;
		// 利用辅助队列开始遍历
		Queue<TreeNode> queue = new LinkedList<>();
		int nextLevelNodes = 0;	// 下层节点总数
		int thisLevelRemains = 1;	// 本层剩余打印节点数
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			System.out.print(node.value + " ");
			thisLevelRemains--;
			// 放入子节点
			if (node.left != null) {
				queue.offer(node.left);
				nextLevelNodes++;
			}
			if (node.right != null) {
				queue.offer(node.right);
				nextLevelNodes++;
			}
			
			if (thisLevelRemains == 0) {
				System.out.println();
				thisLevelRemains = nextLevelNodes;
				nextLevelNodes = 0;
			}
		}
		return;
	}
	
	/**
	 * 变种，之字形换行并打印节点
	 */
	public void printFromTopToBottomWithZ(TreeNode root) {
		
		if (root == null) return;
		// 利用2个辅助栈开始遍历
		Stack<TreeNode> oddStack = new Stack<>();
		Stack<TreeNode> evenStack = new Stack<>();
		oddStack.push(root);
		while (!oddStack.isEmpty() || !evenStack.isEmpty()) {
			
			if (!oddStack.isEmpty()) {
				while (!oddStack.isEmpty()) {
					TreeNode node = oddStack.pop();
					System.out.print(node.value + " ");
					// 偶数栈放入子节点
					if (node.left != null) {
						evenStack.push(node.left);
					}
					if (node.right != null) {
						evenStack.push(node.right);
					}
				}
			} else {
				while (!evenStack.isEmpty()) {
					TreeNode node = evenStack.pop();
					System.out.print(node.value + " ");
					// 奇数栈放入子节点，注意先右后左
					if (node.right != null) {
						oddStack.push(node.right);
					}
					if (node.left != null) {
						oddStack.push(node.left);
					}
				}
			}
			
			System.out.println();
		}
		return;
	}
}
