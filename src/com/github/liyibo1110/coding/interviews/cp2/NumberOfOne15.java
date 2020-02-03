package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目15 二进制中1的个数
 * 给定一个整数，返回其二进制数字中1的个数
 * 额外特征：注意整数包含负数
 * 额外要求：无
 * @author liyibo
 *
 */
public class NumberOfOne15 {

	/**
	 * 解法一：与运算+无符号右移统计法（注意必须是无符号右移操作符>>>，否则负数输入将死循环）
	 */
	public int countingBinaryOne1(int number) {
		
		int count = 0;
		while (number != 0) {
			if ((number & 1) == 1) count++;
			number = number >>> 1;
		}
		return count;
	}
	
	/**
	 * 解法二：与运算+左移统计法
	 */
	public int countingBinaryOne2(int number) {
		
		int count = 0;
		// 即31个0然后最后一个1
		int flag = 1;
		// 应该是循环32次
		while (flag != 0) {
			// 注意要用!=0来判断，而不能是==1，因为与运算结果会被转换成10进制，不会都为1
			if ((number & flag) != 0) count++;
			// 将1左移一个2进制
			flag = flag << 1;
		}
		return count;
	}
	
	/**
	 * 解法一：减1+与运算统计法（比较巧妙，但是难懂）
	 */
	public int countingBinaryOne3(int number) {
		
		int count = 0;
		// 只要不为0，number对应二进制必然还有1
		while (number != 0) {
			number = (number - 1) & number;
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		NumberOfOne15 obj = new NumberOfOne15();
		System.out.println(obj.countingBinaryOne3(9));
	}
}
