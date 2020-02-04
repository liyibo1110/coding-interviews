package com.github.liyibo1110.coding.interviews.cp4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目28 对称的二叉树
 * 判断1个树是不是完全对称的
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class SymmetricalTree28 {

	private static class TreeNode {
		
		private int value;
		TreeNode left = null;
		TreeNode right = null;
		
		public TreeNode(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 解法一：递归法
	 */
	public boolean isSymmetricalTree1(TreeNode root) {
		
		if (root == null) return true;
		return isSymmetricalTree1(root.left, root.right);
	}
	
	private boolean isSymmetricalTree1(TreeNode root1, TreeNode root2) {
		
		// 都为空可以
		if (root1 == null && root2 == null) return true;
		// 只有1个为空不可以
		if (root1 == null || root2 == null) return false;
		// 值不一样当然也不可以
		if (root1.value != root2.value) return false;
		
		// 2个结点各自的左右结点也要都对称
		return isSymmetricalTree1(root1.left, root2.right) &&
				isSymmetricalTree1(root1.right, root2.left);
	}
	
	/**
	 * 解法二：非递归法（要引入队列来辅助）
	 */
	public boolean isSymmetricalTree2(TreeNode root) {
		
		if (root == null) return true;
		Queue<TreeNode> queueA = new LinkedList<>();
		Queue<TreeNode> queueB = new LinkedList<>();
		queueA.offer(root.left);
		queueB.offer(root.right);
		TreeNode leftNode = null;
		TreeNode rightNode = null;
		
		while(!queueA.isEmpty() && !queueB.isEmpty()) {
			leftNode = queueA.poll();
			rightNode = queueB.poll();
			// 都为空可以
			if (leftNode == null && rightNode == null) continue;
			// 只有1个为空不可以
			if (leftNode == null || rightNode == null) return false;
			// 值不一样当然也不可以
			if (leftNode.value != rightNode.value) return false;
			// 继续将2个结点各自的左右结点丢到队列里等待下轮循环
			queueA.offer(leftNode.left);
			queueB.offer(rightNode.right);
			queueA.offer(leftNode.right);
			queueB.offer(rightNode.left);
		}
		// 最终出来了，说明是对称的
		return true;
	}
}
