package com.github.liyibo1110.coding.interviews.cp2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 题目3 数组中重复的数字
 * 长度为n的数组里，所有数字都在0到n-1范围里，某些数字是重复的，找出至少重复1次的数字
 * 额外要求：时间复杂度O(n)，空间复杂度O(1)
 * @author liyibo
 *
 */
public class DuplicateNumber3 {

	/**
	 * 方案一：先排序，再挨个遍历
	 * 时间复杂度为O(nlogn)
	 */
	public void findNumber1(Integer[] arr) {
		
		if (arr == null || arr.length == 0) return;
		
		// 先排序
		Arrays.sort(arr);
		// 再遍历
		Set<Integer> result = new LinkedHashSet<>();
		for (int i = 0; i < arr.length - 1; i++) {
			if (Objects.equals(arr[i], arr[i+1])) {
				result.add(arr[i]);
			}
		}
		for (Integer v : result) {
			System.out.println(v);
		}
	}
	
	/**
	 * 方案二：哈希法，先遍历，额外用一个哈希表记录值是否已存在
	 * 时间复杂度为O(n)，但空间复杂度也为O(n)
	 */
	public void findNumber2(Integer[] arr) {
		
		if (arr == null || arr.length == 0) return;
		
		Set<Integer> set = new HashSet<>();
		Set<Integer> result = new LinkedHashSet<>();
		for (int i = 0; i < arr.length; i++) {
			if (set.contains(arr[i])) {
				result.add(arr[i]);
			} else {
				set.add(arr[i]);
			}
		}
		for (Integer v : result) {
			System.out.println(v);
		}
	}
	
	/**
	 * 方案三：利用题目的额外条件：即所有数值都在0到n-1内，暗示了值和下标可以建立等同关系
	 */
	public void findNumber3(Integer[] arr) {
	
		if (arr == null || arr.length == 0) return;
		
		Set<Integer> result = new LinkedHashSet<>();
		for (int i = 0; i < arr.length; i++) {
			while (arr[i] != i) {
				if (arr[i] == arr[arr[i]]) {
					result.add(arr[i]);
					break;
				}
				swap(arr, i, arr[i]);
			}
		}
		for (Integer v : result) {
			System.out.println(v);
		}
	}
	
	private void swap(Integer[] arr, int p, int q) {
		int temp = arr[p];
		arr[p] = arr[q];
		arr[q] = temp;
	}
	
	public static void main(String[] args) {
		
		// 例子数组
		Integer[] arr = {5,3,1,2,2,2,3};
		// Integer[] arr = {0,0,0,0,0,0,0};
		DuplicateNumber3 obj = new DuplicateNumber3();
		obj.findNumber3(arr);
	}
}
