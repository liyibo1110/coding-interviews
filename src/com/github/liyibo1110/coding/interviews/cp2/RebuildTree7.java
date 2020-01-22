package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目7 重建二叉树
 * 给定一个二叉树的前序遍历结果和中序遍历结果，要反向生成该二叉树原结构
 * 额外特征：各个节点不含相同的值
 * 额外要求：无
 * @author liyibo
 *
 */
public class RebuildTree7 {

	private static class TreeNode {
		
		int value;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int value) {
			this.value = value;
		}
	}
	
	public TreeNode rebuildBinaryTree(Integer[] pre, Integer[] mid) {
		TreeNode root = rebuildBinaryTree(pre, 0, pre.length-1, mid, 0, mid.length-1);
		return root;
	}
	
	private TreeNode rebuildBinaryTree(Integer[] pre, int preStart, int preEnd, 
									   Integer[] mid, int midStart, int midEnd) {
		
		// 递归终止条件
		if (preStart > preEnd || midStart > midEnd) return null;
		int rootValue = pre[preStart];
		TreeNode root = new TreeNode(rootValue);
		
		// 递归构造根节点的左右节点
		for (int i = midStart; i <= midEnd; i++) {
			if (mid[i] == rootValue) {
				int leftTreeSize = i - midStart;
				root.left = rebuildBinaryTree(pre, preStart + 1, preStart + leftTreeSize,
											  mid, midStart, i - 1);
				root.right = rebuildBinaryTree(pre, preStart + leftTreeSize + 1, preEnd, 
											   mid, i + 1, midEnd);
			}
		}
		return root;
	}
	
	public static void main(String[] args) {
		
		// 例子前序和中序数组
		Integer[] pre = {1,2,4,7,3,5,6,8};
		Integer[] mid = {4,7,2,1,5,3,8,6};
		
		RebuildTree7 obj = new RebuildTree7();
		obj.rebuildBinaryTree(pre, mid);
	}
}
