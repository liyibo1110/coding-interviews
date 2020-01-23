package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目8 二叉树的下一个节点
 * 给定一个二叉树和其中一个节点，要求给出中序遍历情况下的下一个节点
 * 额外特征：树结构带左右节点引用，还附带父节点引用
 * 额外要求：无
 * @author liyibo
 *
 */
public class FindNextTreeNodeInMid8 {

	private static class TreeNode {
		
		int value;
		TreeNode parent;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int value) {
			this.value = value;
		}
	}
	
	public TreeNode getNext(TreeNode node) {
		
		if (node == null) return null;
		
		// 如果节点有右子节点，则下一节点为右子节点的最终左节点
		if (node.right != null) {
			TreeNode result = node.right;
			while (result.left != null) result = result.left;
			return result;
		} 
			
		// 如果节点没有右子节点，则向上找父节点，有2种情况，自己是父的右节点，则还要向上，直接为父节点的左节点，这时这个父节点就是下一个遍历节点
		TreeNode result = node.parent;
		while (result != null && result.right == node) result = result.parent;
		return result;
	}
}
