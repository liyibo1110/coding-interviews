package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目4 特殊二维数组中重复的数字
 * m行n列的二维数组里，每一行由小到大，每一列也是由小到大，给定一个数，判断是否在这个二维数组里
 * 额外要求：时间复杂度O(m+n)，空间复杂度O(1)
 * @author liyibo
 *
 */
public class DuplicateNumber4 {

	/**
	 * 方案一：从右上角开始找，每次可以缩小一批范围
	 */
	public boolean findNumber1(Integer[][] martix, int target) {
		
		if (martix == null || martix.length == 0 || martix[0].length == 0) {
			return false;
		}
		
		int rows = martix.length;
		int cols = martix[0].length;
		// 定位右上角
		int r = 0;
		int c = cols - 1;
		
		// 不出界就一直遍历
		while (r <= rows - 1 && c >= 0) {
			if (martix[r][c] == target) {
				return true;
			} else if (target > martix[r][c]) {
				r++;
			} else {
				c--;
			}
		}
		
		return false;
	}
	
	/**
	 * 方案二：从左下角开始找，每次可以缩小一批范围
	 */
	public boolean findNumber2(Integer[][] martix, int target) {
		
		if (martix == null || martix.length == 0 || martix[0].length == 0) {
			return false;
		}
		
		int rows = martix.length;
		int cols = martix[0].length;
		
		// 定位左下角
		int r = rows - 1;
		int c = 0;
		
		// 不出界就一直遍历
		while (r >= 0 && c <= cols - 1) {
			if (martix[r][c] == target) {
				return true;
			} else if (target > martix[r][c]) {
				c++;
			} else {
				r--;
			}
		}
		
		return false;
	}
	
	/**
	 * 方案三：因为每行是有序的，所以可以逐行进行二分查找，找到即可（但时间复杂度会变成O(mlogn)）
	 */
	public boolean findNumber3(Integer[][] martix, int target) {
		
		if (martix == null || martix.length == 0 || martix[0].length == 0) {
			return false;
		}
		
		for (int i = 0; i < martix.length; i++) {
			Integer[] arr = martix[i];
			int low = 0;
			int high = arr.length - 1;
			while (low <= high) {
				int mid = low + (high - low) / 2;
				if (target > arr[mid]) {
					low = mid + 1;
				} else if (target < arr[mid]) {
					high = mid - 1;
				} else {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		
		// 例子数组
		Integer[][] martix = {
				{1,4,7,11,15},
				{2,5,8,12,19},
				{3,6,9,16,22},
				{10,13,14,17,24},
				{18,21,23,26,30}
		};
		DuplicateNumber4 obj = new DuplicateNumber4();
		System.out.println(obj.findNumber3(martix, 77));
	}
}
