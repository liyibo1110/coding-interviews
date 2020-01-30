package com.github.liyibo1110.coding.interviews.cp3;



/**
 * 题目17 打印从1到最大n位十进制数
 * 实现一个pow方法
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class PrintFrom1ToMaxOfDigit17 {

	public void print1(int n) {
		
		if (n <= 0 ) return;
		StringBuilder sb = new StringBuilder();
		// 初始化字符串，填充0
		for (int i = 0; i < n; i++) {
			sb.append("0");
		}
		// 不停模拟增加数字，最后完事打印
		while (increase(sb, n)) {
			print(sb);
		}
	}
	
	private boolean increase(StringBuilder sb, int len) {
		
		int toTen = 0;	//进位标志
		// 从个位开始
		for (int i = len - 1; i >= 0; i--) {
			// 获取当前位上的数字，如果是后面进位上来的，需要再加1
			int sum = sb.charAt(i) - '0' + toTen;
			if (i == len - 1) sum++;	// 如果是个位，就自增，只有这一处增值操作
			
			// 后面是自增后判断是否要进位
			if (sum == 10) {
				if (i == 0) {
					// 已到最高位，溢出直接退出
					return false;
				} else {
					// 当前位直接置0
					sb.setCharAt(i, '0');
					toTen = 1;
				}
			} else {
				// 不进位直接赋值退出方法
				sb.setCharAt(i, (char)(sum + '0'));
				break;
			}
		}
		return true;
	}
	
	private void print(StringBuilder sb) {
		
		int start = sb.length();
		// 跳过0开头的字符，指向第一个不为0的字符
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) != '0') {
				start = i;
				break;
			}
		}
		if (start < sb.length()) {
			System.out.print(sb.substring(start) + " ");
		}
	}
	
	public static void main(String[] args) {
		System.out.println((int)'0');
	}
}
