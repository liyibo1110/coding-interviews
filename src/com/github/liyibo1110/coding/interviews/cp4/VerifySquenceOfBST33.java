package com.github.liyibo1110.coding.interviews.cp4;

/**
 * 题目33 二叉搜索树的后序遍历序列
 * 给定一个数值数组，判断这个序列是不是一个二叉搜索树按后序遍历出来的数值
 * 额外特征：二叉搜索树特征为：左节点值都小于根节点，右节点值都大于根节点
 * 额外要求：无
 * @author liyibo
 */
public class VerifySquenceOfBST33 {

	public boolean verify(Integer[] sequence) {
		
		if (sequence == null || sequence.length == 0) return false;
		return isBST(sequence, 0, sequence.length - 1);
	}
	
	private boolean isBST(Integer[] sequence, int begin, int end) {
		
		// 左右指针相遇，说明只有1个节点或者没有节点了
		if (begin >= end) return true;
		
		// 序列最后一个值为根节点值
		int rootValue = sequence[end];
		int i = begin;
		// 右移begin指针，最终置位于第一个比root大的元素
		while (i < end && sequence[i] < rootValue) {
			i++;
		}
		int boundary = i;
		// [begin, boundary - 1]为左子树，[boundary, end]为右子树
		while (i < end) {
			// 如果再出现比rootValue小的，说明此段序列不是二叉搜索树后序结果
			if (sequence[i] < rootValue) return false;
			i++;
		}
		return isBST(sequence, begin, boundary - 1) &&
				isBST(sequence, boundary, end);
	}
}
