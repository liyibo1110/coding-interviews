package com.github.liyibo1110.coding.interviews.cp4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 题目27 二叉树的镜像
 * 给定1个树，对其镜像操作
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class MirrorTree27 {

	private static class TreeNode {
		
		private int value;
		TreeNode left = null;
		TreeNode right = null;
		
		public TreeNode(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 解法一：前序遍历 + 递归
	 */
	public void mirror1(TreeNode root) {
		exchangeChildren(root);
	}
	
	private void exchangeChildren(TreeNode node) {
		
		if (node == null) return;
		
		// 如果是叶子节点，则停止交换
		if (node.left == null && node.right == null) return;
	
		// 交换左右
		TreeNode tempNode = node.left;
		node.left = node.right;
		node.right = tempNode;
		
		// 继续交换左右子节点
		exchangeChildren(node.left);
		exchangeChildren(node.right);
	}
	
	/**
	 * 解法二：前序遍历 + 非递归（深度优先，要额外使用栈结构，而且很难理解）
	 */
	public void mirror2(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				// 交换并记录左侧节点（注意是交换顺序后的左侧节点）
				stack.push(root);
				if (root.left != null || root.right != null) {
					TreeNode tempNode = root.left;
					root.left = root.right;
					root.right = tempNode;
				}
				root = root.left;
			}
			// 处理完一半，开始反向处理另一半
			if (!stack.isEmpty()) {
				root = stack.pop();
				root = root.right;
			}
		}
	}
	
	/**
	 * 解法三：层序遍历（从上到下，从左到右） + 非递归（广度优先，要额外使用队列结构，比上面好理解一点）
	 */
	public void mirror3(TreeNode root) {
		
		if (root == null) return;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			// 尝试交换子节点
			if (node.left != null || node.right != null) {
				TreeNode tempNode = node.left;
				node.left = node.right;
				node.right = tempNode;
			}
			if (node.left != null) queue.offer(node.left);
			if (node.right != null) queue.offer(node.right);
		}
	}
}
