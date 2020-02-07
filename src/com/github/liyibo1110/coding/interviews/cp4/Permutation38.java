package com.github.liyibo1110.coding.interviews.cp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 题目38 字符串的排列
 * 给定一个字符串，输出全排列
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 */
public class Permutation38 {

	public List<String> permutation(String str) {
		List<String> list = new ArrayList<>();
		if (str == null || str.length() == 0) return list;
		collect(str.toCharArray(), 0, list);
		Collections.sort(list);
		return list;
	}
	
	private void collect(char[] chars, int begin, List<String> list) {
		
		if (begin == chars.length - 1) {
			String s = String.valueOf(chars);
			if (!list.contains(s)) list.add(s);
			return;
		}
		
		for (int i = begin; i < chars.length; i++) {
			swap(chars, i, begin);
			collect(chars, begin + 1, list);
			// 要还原回去
			swap(chars, i, begin);
		}
	}
	
	private void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}
}
