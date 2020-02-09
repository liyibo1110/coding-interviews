package com.github.liyibo1110.coding.interviews.cp5;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目45 把数组排成最小的数
 * 给定一个正整数数组，打印出其所有可能拼接总和值最小的那个值，如{3,32,321}要返回321323，而不是332321
 * 额外特征：无
 * 额外要求：高效
 * @author liyibo
 */
public class PrintMinNumber45 {

	public String printMinNumber(int[] numbers) {
		
		if (numbers == null || numbers.length == 0) return "";
		List<Integer> list = new ArrayList<>();
		for (int v : numbers) {
			list.add(v);
		}
		list.sort((a, b) -> (a + "" + b).compareTo(b + "" + a));
		StringBuilder sb = new StringBuilder();
		for (int v : list) {
			sb.append(v);
		}
		return sb.toString();
	}
}
