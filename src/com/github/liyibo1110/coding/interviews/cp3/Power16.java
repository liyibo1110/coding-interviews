package com.github.liyibo1110.coding.interviews.cp3;

/**
 * 题目16 数值的整数次方
 * 实现一个pow方法
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class Power16 {

	/**
	 * 解法一：连乘法
	 */
	public double power1(double base, int exponent) {
		
		if (base == 0) return 0;
		
		// 将指数统一变成正数
		int positiveExponent = Math.abs(exponent);
		double result = 1.0d;
		for (int i = 0; i < positiveExponent; i++) {
			result *= base;
		}
		
		return exponent > 0 ? result : 1.0 / result;
	}
	
	/**
	 * 解法二：公式法（非递归）
	 */
	public double power2(double base, int exponent) {
		
		if (base == 0) return 0;
		
		// 将指数统一变成正数
		int positiveExponent = Math.abs(exponent);
		double result = 1.0d;
		
		while (positiveExponent != 0) {
			
			// 反着乘，先看指数是不是奇数，如果是，先乘以1个底数
			if ((positiveExponent & 1) == 1) {
				result *= base;
			}
			base *= base;
			// 相当于除以2，如果指数为奇数，会自动减1，所以不用特殊处理
			positiveExponent = positiveExponent >> 1;
		}
		
		return exponent > 0 ? result : 1.0 / result;
	}
	
	/**
	 * 解法三：公式法（递归，不建议）
	 */
	public double power3(double base, int exponent) {
		
		if (base == 0) return 0;
		// 将指数统一变成正数
		int positiveExponent = Math.abs(exponent);
		double result = powerUnsigned(base, positiveExponent);
		return exponent > 0 ? result : 1.0 / result;
	}
	
	private double powerUnsigned(double base, int positiveExponent) {
		
		if (positiveExponent == 0) return 1;
		if (positiveExponent == 1) return base;
		double result = powerUnsigned(base, positiveExponent >> 1);
		result *= result;
		if ((positiveExponent & 1) == 1) {
			result *= base;
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		Power16 obj = new Power16();
		System.out.println(obj.power3(2, -3));
	}
}
