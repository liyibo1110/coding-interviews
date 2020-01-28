package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目11 旋转数组中的最小数字
 * 给定一个旋转数组（即类似3,4,5,1,2这种2部分均有序的数组），返回最小数字
 * 额外特征：旋转数组有2部分是有序的
 * 额外要求：效率不能是O(n)
 * @author liyibo
 *
 */
public class RotateArray11 {

	/**
	 * 解法一：直接顺序遍历（但时间复杂度为O(n)）
	 */
	public Integer findMinValue1(Integer[] array) {
		
		if (array == null || array.length == 0) return null;
		
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i+1]) {
				return array[i+1];
			}
		}
		
		// 说明本来就是整体有序，第1个元素本身为最小
		return array[0];
	}
	
	/**
	 * 解法二：二分查找法
	 */
	public Integer findMinValue2(Integer[] array) {
		
		if (array == null || array.length == 0) return null;
		
		int low = 0;
		int high = array.length - 1;
		while (low < high) {
			int mid = low + (high - low) / 2;
			
			if (array[mid] > array[high]) {
				// mid位于左半侧
				low = mid + 1;
			} else if(array[mid] < array[high]) {
				// mid位于右半侧
				high = mid;
			} else {
				// 此轮放弃二分查找，high左移（因为左移后high肯定不大于上一个high，另外当指针相遇，下一轮肯定要进来，然后再下一轮退出循环）
				high--;
			}
		}
		
		return array[low];
	}
	
	public static void main(String[] args) {
		
		// 例子数组
		Integer[] arr = {5,6,7,8,9,1,2,3,4};
		RotateArray11 obj = new RotateArray11();
		System.out.println(obj.findMinValue2(arr));
	}
}
