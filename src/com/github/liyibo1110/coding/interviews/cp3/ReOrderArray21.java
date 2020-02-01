package com.github.liyibo1110.coding.interviews.cp3;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * 题目21 调整数组的顺序使奇数位于偶数前面
 * 给定一个数组，提供一个方法使奇数值在左侧，偶数值在右侧
 * 额外特征：不需要保持原来的数值顺序
 * 额外要求：左右侧的条件应可以灵活切换（如将奇左偶右条件，改成奇右偶左）
 * @author liyibo
 *
 */
public class ReOrderArray21 {

	/**
	 * 解法一：额外空间法，用额外的数组存放奇偶数，然后再覆盖原来的数组（时间空间复杂度均为O(n)）
	 * @param array
	 */
	public void reOrderArray1(int[] array) {
		
		if (array == null || array.length == 0) return;
		

		int len = array.length;
		int[] temp = new int[len];
		int p = 0;	// temp的动态下标
		// 第一次扫描，只扫奇数
		for (int i = 0; i < len; i++) {
			if (isOdd(array[i])) {
				temp[p++] = array[i];
			}
		}
		// 第二次扫描，只扫偶数
		for (int i = 0; i < len; i++) {
			if (!isOdd(array[i])) {
				temp[p++] = array[i];
			}
		}
		// 覆盖原数组
		for (int i = 0; i < len; i++) {
			array[i] = temp[i];
		}
	}
	
	/**
	 * 解法一：额外空间法，用额外的数组存放奇偶数，然后再覆盖原来的数组（时间空间复杂度均为O(n)）
	 * @param array
	 */
	public void reOrderArray2(int[] array, Predicate<Integer> p) {
	
		if (array == null || array.length == 0) return;
		int begin = 0;
		int end = array.length - 1;
		while (begin < end) {
			// 从左向右找第一个偶数
			while (begin < end && p.test(array[begin])) {
				begin++;
			}
			// 从右向左找第一个奇数
			while (begin < end && !p.test(array[end])) {
				end--;
			}
			if (begin < end) {
				int temp = array[begin];
				array[begin] = array[end];
				array[end] = temp;
			}
		}
	}
	
	private boolean isOdd(int value) {
		
		return (value & 1) == 1;
	}
	
	public static void main(String[] args) {
		
		ReOrderArray21 obj = new ReOrderArray21();
		int[] array = {3,2,1,9,8,7,4,5,6};
		obj.reOrderArray2(array, p -> (p & 1) == 1);
		System.out.println(Arrays.toString(array));
	}
}
