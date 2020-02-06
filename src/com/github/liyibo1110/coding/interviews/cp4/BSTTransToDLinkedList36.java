package com.github.liyibo1110.coding.interviews.cp4;

/**
 * 题目36 二叉搜索树与双向链表
 * 给定一个二叉搜索树，直接修改成双向链表结构
 * 额外特征：无
 * 额外要求：不能创建新节点，只能修改树中原有的指向
 * @author liyibo
 */
public class BSTTransToDLinkedList36 {

	private static class TreeNode {
		
		private int value;
		TreeNode left = null;
		TreeNode right = null;
		
		public TreeNode(int value) {
			this.value = value;
		}
	}
	
	public TreeNode convert(TreeNode root) {
		
		if (root == null) return null;
		// 全局使用，指向中序遍历的前一个节点
		TreeNode globalPreNode = null;
		TreeNode curRoot = root;
		// 用中序遍历转换成双向链表
		inOrder(curRoot, globalPreNode);
		// 找到最左侧子节点
		while (curRoot.left != null) {
			curRoot = curRoot.left;
		}
		return curRoot;
	}
	
	/**
	 * 将当前根节点往下变成双向链表指向
	 */
	private void inOrder(TreeNode node, TreeNode preNode) {
		
		if (node == null) return;
		
		inOrder(node.left, preNode);
		node.left = preNode;
		if (preNode != null) {
			preNode.right = node;
		}
		preNode = node;
		inOrder(node.right, preNode);
	}
}
