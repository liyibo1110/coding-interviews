package com.github.liyibo1110.coding.interviews.cp5;

/**
 * 题目43 1-n整数中1出现的次数
 * 给定一个数字，返回其从1开始到这个数，出现数字1个总次数（答案给的解法暂不实现，比较偏门）
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 */
public class NumOfOne43 {

	/**
	 * 解法一：StringBuilder法（属于投机取巧，应该通不过）
	 */
	public int countOfOne1(int n) {
		
		if (n < 0) n = Math.abs(n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= n; i++) {
			sb.append(i);
		}
		int count = 0;
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '1') count++;
		}
		return count;
	}
	
	/**
	 * 解法二：挨个统计法（时间复杂度为O(nlogn)，效率较低）
	 */
	public int countOfOne2(int n) {
		
		if (n < 0) n = Math.abs(n);
		int count = 0;
		for (int i = 1; i <= n; i++) {
			count += numOfOne(i);
		}
		return count;
	}
	
	/**
	 * 返回一个数字包含多少个1（核心方法）
	 */
	private int numOfOne(int n) {
		int count = 0;
		while (n != 0) {
			if (n % 10 == 1) count++;
			n = n / 10;
		}
		return count;
		
	}
}
