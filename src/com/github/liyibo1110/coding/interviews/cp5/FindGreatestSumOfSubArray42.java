package com.github.liyibo1110.coding.interviews.cp5;

/**
 * 题目42 连续子数组的最大和
 * 给定一个可存在负数的数组，返回其所有可能连续子数组的最大和
 * 额外特征：重点在于子数组必须是连续的，这样就可以一次遍历了
 * 额外要求：时间复杂度不能是O(n^2)
 * @author liyibo
 */
public class FindGreatestSumOfSubArray42 {

	/**
	 * 解法一：一次遍历法，比较简单
	 */
	public int find1(int[] array) {
		
		if (array == null || array.length == 0) return 0;
		
		int maxSum = array[0];	// 历史最大值
		int curSum = array[0];	// 当前遍历时最大值
		for (int i = 1; i < array.length; i++) {
			// 如果之前累加和为负数，则新一轮累加和直接为当前遍历数值，因为还不如不加
			if (curSum < 0) {
				curSum = array[i];
			} else {
				curSum += array[i];
			}
			if (curSum > maxSum) maxSum = curSum;
		}
		
		return maxSum;
	}
	
	/**
	 * 解法二：动态规划法，和解法一其实差不多
	 */
	public int find2(int[] array) {
		
		if (array == null || array.length == 0) return 0;
		int maxSum = array[0];	// 历史最大值
		int curSum = array[0];	// 当前遍历时最大值
		for (int i = 1; i < array.length; i++) {
			curSum = Math.max(curSum + array[i], array[i]);
			maxSum = Math.max(curSum, maxSum);
		}
		return maxSum;
	}
}
