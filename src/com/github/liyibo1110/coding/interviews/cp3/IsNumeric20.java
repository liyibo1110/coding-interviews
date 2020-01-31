package com.github.liyibo1110.coding.interviews.cp3;

/**
 * 题目20 表示数值的字符串
 * 提供一个方法，判断是不是数值
 * 额外特征：无
 * 额外要求：要能识别各种数值格式，如正负号开头、小数、指数格式
 * @author liyibo
 *
 */
public class IsNumeric20 {

	public boolean isNumeric(char[] str) {
		
		// 为空或长度为0
		if (str == null || str.length == 0) return false;
		
		// 长度为1，则必须是0-9
		if (str.length == 1) {
			return str[0] >= '0' && str[0] <= '9';
		}
		
		boolean hasDot = false;	// 已出现过小数点
		boolean hasE = false;	// 已出现过指数e
		boolean hasSign = false;	// 已出现过正负号
		
		for (int i = 0; i < str.length; i++) {
			
			if (str[i] == '+' || str[i] == '-') {
				// 第二次出现正负号，前一个字符必须是e或者E
				if (hasSign && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
				// 第一次出现正负号并且不在开头，前一个字符必须也是e或者E
				if (!hasSign && i > 0 && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
				hasSign = true;
			} else if (str[i] == '.') {
				// 如果出现了指数或者出现过小数点，则不能有小数点
				if (hasDot || hasE) return false;
				hasDot = true;
			} else if (str[i] == 'e' || str[i] == 'E') {
				// e或者E后面必须有数字
				if (i == str.length - 1) return false;
				if (hasE) return false;
				hasE = true;
			} else if (str[i] < '0' || str[i] > '9') {
				return false;
			}
		}
		
		return true;
	}
}
