package com.github.liyibo1110.coding.interviews.cp3;

/**
 * 题目19 正则表达式的匹配
 * 提供一个方法，匹配.和*通配符，前者匹配任意一个字符，后者匹配任意多个前字符（例如ab*c，可以匹配ac、abc、abbc）
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class RegexMatch19 {

	public boolean match(char[] str, char[] pattern) {
		
		if (str == null || pattern == null) return false;
	
		return match(str, pattern, 0, 0);
	}
	
	
	public boolean match(char[] str, char[] pattern, int s, int p) {
		
		// 说明匹配成功
		if (s == str.length && p == pattern.length) return true;
		// 说明匹配失败
		if (s < str.length && p == pattern.length) return false;
		
		// 模式字符串当前位置的下一个是*
		if (p < pattern.length - 1 && pattern[p + 1] == '*') {
			if ((s < str.length && str[s] == pattern[p]) || (s < str.length && pattern[p] == '.')) {
				// 如果下一字符为*同时前一字符已成功匹配，则尝试依次匹配0个、1个、多个模式，有1个可以都算匹配成功
				return match(str, pattern, s, p + 2) ||
					   match(str, pattern, s + 1, p + 2) ||
					   match(str, pattern, s + 1, p);
			} else {
				// 下一字符为*但是前一字符没有匹配到，则模式串后移2位
				return match(str, pattern, s, p + 2);
			}
		}
		
		// 下一个不是*，同时精确匹配了一个字符或者.通配符，字符串和模式串一起后移1位
		if ((s < str.length && str[s] == pattern[p]) || (s < str.length && pattern[p] == '.')) {
			return match(str,pattern, s + 1, p + 2);
		}
		return false;
	}
}
