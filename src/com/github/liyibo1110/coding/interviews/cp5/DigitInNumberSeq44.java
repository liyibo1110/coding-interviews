package com.github.liyibo1110.coding.interviews.cp5;

/**
 * 题目44 数字序列中某一位的数字
 * 给定一个序列形如0123456789101112131415...要求给定一个位置，返回这个位置上面的数字，例如给定19返回4
 * 额外特征：无
 * 额外要求：高效
 * @author liyibo
 */
public class DigitInNumberSeq44 {

	/**
	 * 解法一：列举法（效率较低）
	 */
	public int numAtSeq(int n) {
		
		if (n < 0) return 0;
		int i = 0;	// 当前生成的序列数字，每次加1
		int sum = 0; // 序列的长度
		while (true) {
			sum += countDigits(i);
			if (sum > n) break; // 给定的n是相对于0的唯一
			i++;
		}
		return digitAt(i, sum - n - 1);
	}
	
	/**
	 * 返回一个数字，共有多少位
	 */
	private int countDigits(int number) {
		
		if (number == 0) return 1;
		int count = 0;
		while (number != 0) {
			number = number / 10;
			count++;
		}
		return count;
	}
	
	/**
	 * 返回某数字的第d位，传入0代表要取个位，传入1是要取十位
	 */
	private int digitAt(int val, int d) {
		return (val / (int)Math.pow(10, d)) % 10;
	}
	
	public static void main(String[] args) {
		new DigitInNumberSeq44().numAtSeq(5);
	}
}
