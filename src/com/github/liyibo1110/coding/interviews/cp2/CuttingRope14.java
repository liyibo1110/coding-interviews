package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目14 剪绳子
 * 长度为n的绳子，要求剪为m段，求怎么剪使得各段长度的乘积为最大
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class CuttingRope14 {

	/**
	 * 解法一：动态规划法，效率极低，时间复杂度为O(n^2)
	 */
	public int maxProductCutting1(int length) {
		
		// 长度为1没法切
		if (length < 2) return 0;
		// 长度为2只有1种切法，乘积为1
		if (length == 2) return 1;
		// 长度为3有3种切法，最大乘积为2
		if (length == 3) return 2;
		
		// 从下标1开始存
		int[] products = new int[length + 1];
		// 前3个存的不是长度对应的最大乘积，如果length为1到3，上面直接就返回结果了
		products[1] = 1;
		products[2] = 2;
		products[3] = 3;
		for (int i = 4; i <= length; i++) {
			// 计算出长度为i的最优解
			int max = 0;
			// 计算特定length的max乘积，i除以2是为了跳过重复计算，如计算了f(1)*f(4)则不需要再计算f(4)*f(1)了
			for (int j = 1; j <= i/2; j++) {
				int product = products[j] * products[i - j];
				if (product > max) {
					max = product;
				}
			}
			products[i] = max;
		}
		// 都算完了直接返回参数长度对应最大乘积
		return products[length];
	}
	
	/**
	 * 解法二：贪婪算法，即直接使用定义好的规则，需要额外数学证明，但效率最高，时间空间复杂度均为O(1)，
	 */
	public int maxProductCutting2(int length) {
		
		// 长度为1没法切
		if (length < 2) return 0;
		// 长度为2只有1种切法，乘积为1
		if (length == 2) return 1;
		// 长度为3有3种切法，最大乘积为2
		if (length == 3) return 2;
		
		// 尽可能分成长度为3的绳子
		int timesOf3 = length / 3;
		// 如果最终长度只有1，则需要少切1次，把最后长度为4的绳子分成2*2
		int lastLength = length - timesOf3 * 3;
		if (lastLength == 1) {
			timesOf3--; 
		}
		// 重置为长度4
		lastLength = length - timesOf3 * 3;
		int timesOf2 = lastLength / 2;
		return (int)Math.pow(3, timesOf3) * (int)Math.pow(2, timesOf2);
	}
}
