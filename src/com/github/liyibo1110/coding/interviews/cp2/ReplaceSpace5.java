package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目5 替换字符串中的空格
 * 给定一个字符串，将每个空格替换成%20
 * 额外要求：时间复杂度不能是O(n^2)
 * @author liyibo
 *
 */
public class ReplaceSpace5 {

	/**
	 * 方案一：从左开始遍历，遇到空格就要将后面所有字符后移2位（但时间复杂度为 O(n^2)）
	 */
	public String replaceSpace1(StringBuilder sb) {
		
		if (sb == null) return null;
		if (sb.length() == 0) return sb.toString();
		// 因为Java的特殊性，要先追加适量的空白填充
		int length = sb.length();
		for (int i = 0; i < length; i++) {
			if (sb.charAt(i) == ' ') sb.append("  ");
		}
		
		// 字符指针
		int p = 0;
		while (p <= length - 1) {
			char c = sb.charAt(p);
			if (c == ' ') {
				// 空格后面每个字符整体右移2位
				for (int i = length - 1; i >= p ; i--) {
					char cc = sb.charAt(i);
					sb.setCharAt(i + 2, cc);
				}
				// 替换空格
				sb.setCharAt(p, '%');
				sb.setCharAt(p+1, '2');
				sb.setCharAt(p+2, '0');
				length += 2;
			}
			p++;
		}
		
		return sb.toString();
	}
	
	/**
	 * 方案二：从右开始遍历，利用双指针，一个指向原来的尾部，一个指向填充后的尾部，往左遍历
	 */
	public String replaceSpace2(StringBuilder sb) {
	
		if (sb == null) return null;
		if (sb.length() == 0) return sb.toString();
		// 因为Java的特殊性，要先追加适量的空白填充
		int p1 = sb.length() - 1;
		for (int i = 0; i <= p1; i++) {
			if (sb.charAt(i) == ' ') sb.append("  ");
		}
		int p2 = sb.length() - 1;
		
		while (p1 >= 0 && p2 > p1) {
			char c = sb.charAt(p1);
			if (c == ' ') {
				sb.setCharAt(p2--, '0');
				sb.setCharAt(p2--, '2');
				sb.setCharAt(p2--, '%');
			} else {
				sb.setCharAt(p2--, c);
			}
			p1--;
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		// 例子数组
		StringBuilder sb = new StringBuilder("We are happy.");
		ReplaceSpace5 obj = new ReplaceSpace5();
		System.out.println(obj.replaceSpace2(sb));
	}
}
