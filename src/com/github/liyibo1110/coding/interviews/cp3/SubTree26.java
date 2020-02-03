package com.github.liyibo1110.coding.interviews.cp3;

/**
 * 题目26 树的子结构
 * 给定2个树，判断后者是不是前者的子结构
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class SubTree26 {

	private static class TreeNode {
		
		private int value;
		TreeNode left = null;
		TreeNode right = null;
		
		public TreeNode(int value) {
			this.value = value;
		}
	}
	
	public boolean hasSubTree(TreeNode root1, TreeNode root2) {
		
		boolean result = false;
		if (root1 != null && root2 != null) {
			
			// 从A树中成功找到B树的根节点
			if (root1.value == root2.value) {
				result = doesTree1HaveTree2(root1, root2);
			}
			
			// 如果上面的递归最终没找到完整子结构，则分别递归左右重复寻找
			if (!result) {
				result = hasSubTree(root1.left, root2);
			}
			if (!result) {
				result = hasSubTree(root1.right, root2);
			}
		}
		return result;
	}
	
	private boolean doesTree1HaveTree2(TreeNode node1, TreeNode node2) {
		
		// 如果B树遍历到头了，说明是匹配的
		if (node2 == null) return true;
		// 如果B树没到头，A树到头了，说明不匹配
		if (node1 == null) return false;
		// 节点值对应不相等，说明不匹配
		if (node1.value != node2.value) return false;
		// 到这里说明节点值相等，需要遍历左右子节点都要继续相等，直到遍历完
		return doesTree1HaveTree2(node1.left, node2.left) &&
				doesTree1HaveTree2(node1.right, node2.right);
	}
}
