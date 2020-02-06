package com.github.liyibo1110.coding.interviews.cp4;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目34 二叉树中和为某一值的路径
 * 给定一个二叉树和一个数值，判断从根节点到某一叶子节点值总和，是否等于这个数值，要返回所有符合要求的路径
 * 额外特征：注意是根节点到叶节点才算一条路径，中间节点计算满足了不能算数
 * 额外要求：无
 * @author liyibo
 */
public class FindPathInBT34 {

	private static class TreeNode {
		
		private int value;
		TreeNode left = null;
		TreeNode right = null;
		
		public TreeNode(int value) {
			this.value = value;
		}
	}
	
	public List<List<Integer>> findPath(TreeNode root, int value) {
		
		List<List<Integer>> list = new ArrayList<>();
		if (root == null) return list;
		List<Integer> path = new ArrayList<>();
		findPath(root, value, path, list);
		return list;
	}
	
	private void findPath(TreeNode node, int value, List<Integer> path, List<List<Integer>> list) {
		
		if (node == null) return;
		// 进入当前路径
		path.add(node.value);
		value -= node.value;
		if (node.left == null && node.right == null && value == 0) {
			// 找到一条路径了
			list.add(new ArrayList<>(path));
		}
		findPath(node.left, value, path, list);
		findPath(node.right, value, path, list);
		// 到这里说明node处理完左右子节点了，还要从path中取消掉当前节点和值
		path.remove(path.size() - 1);
		value += node.value;
	}
}
