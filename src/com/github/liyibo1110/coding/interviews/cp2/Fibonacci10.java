package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目10 斐波那契数列
 * 给定n，返回前n项斐波那契数列
 * 额外特征：无
 * 额外要求：效率不能过低
 * @author liyibo
 *
 */
public class Fibonacci10 {

	/**
	 * 教科书递归法，效率极低，不推荐
	 */
	public int fib1(int n) {
		
		if (n <= 0) return 0;
		if (n == 1) return 1;
		return fib1(n - 1) + fib1(n - 2);
	}
	
	/**
	 * 循环法，推荐
	 */
	public int fib2(int n) {
		
		if (n <= 0) return 0;
		int a = 0;	// f(0)的结果
		int b = 1;	// f(1)的结果
		int total = 0;
		for (int i = 2; i <= n; i++) {
			total = a + b;	// f(i)的结果
			a = b;
			b = total;
		}
		return total;
	}
	
	public static void main(String[] args) {
		
		Fibonacci10 obj = new Fibonacci10();
		System.out.println(obj.fib2(5));
	}
}
