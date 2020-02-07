package com.github.liyibo1110.coding.interviews.cp4;

/**
 * 题目37 序列化二叉树
 * 给定2个方法，用来序列化/反序列化一棵二叉树
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 */
public class SerializeBT37 {

	private static class TreeNode {
		
		private int value;
		TreeNode left = null;
		TreeNode right = null;
		
		public TreeNode(int value) {
			this.value = value;
		}
	}
	
	public String serialize(TreeNode root) {
		
		if (root == null) return null;
		StringBuilder sb = new StringBuilder();
		preOrder(root, sb);
		return sb.toString();
	}
	
	private void preOrder(TreeNode node, StringBuilder sb) {
		
		if (node == null) {
			sb.append("#").append(" ");
			return;
		}
		sb.append(node.value).append(" ");
		preOrder(node.left, sb);
		preOrder(node.right, sb);
	}
	
	// 反序列化要用来当索引
	private int index = -1;
	
	public TreeNode deserialize(String str) {
		
		if (str == null) return null;
		String[] seq = str.split("\\s");
		
		return reconstructBT(seq);
	}
	
	private TreeNode reconstructBT(String[] seq) {
		index++;
		if (seq[index].equals("#")) {
			return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(seq[index]));
		root.left = reconstructBT(seq); 
		root.right = reconstructBT(seq);
		return root;
	}
}
