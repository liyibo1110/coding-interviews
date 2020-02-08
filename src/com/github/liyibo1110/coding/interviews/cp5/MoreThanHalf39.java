package com.github.liyibo1110.coding.interviews.cp5;

/**
 * 题目39 数组中出现次数超过一半的数字
 * 给定一个二叉搜索树，直接修改成双向链表结构
 * 额外特征：无
 * 额外要求：不能创建新节点，只能修改树中原有的指向
 * @author liyibo
 */
public class MoreThanHalf39 {

	/**
	 * 切分法：要借助快速排序的partition方法，比较复杂
	 */
	public int moreThanHalf(int[] array) {
		
		if (array == null || array.length == 0) return 0;
		int mid = select(array, array.length/2);
		return check(array, mid);
	}
	
	/**
	 * 多数投票法：利用了题目给定的，数字超过长度一半的特点
	 */
	public int moreThanHalf2(int[] array) {
		
		if (array == null || array.length == 0) return 0;
		int result = array[0];
		int count = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] == result) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				result = array[i];
				count = 1;
			}
		}
		return check(array, result);
	}
	
	/**
	 * 检查传入的值，是不是数组中出现次数超过一半
	 */
	private int check(int[] array, int val) {
		
		int count = 0;
		for (int v : array) {
			if (v == val) count++;
		}
		return count > array.length / 2 ? val : 0;
	}
	
	/**
	 * 找到数组中排名为k的元素（部分排序即可，时间复杂度为O(n)）
	 */
	private int select(int[] array, int k) {
		
		int low = 0;
		int high = array.length - 1;
		// low和high不相遇就循环
		while (low < high) {
			int pos = partition(array, low, high);
			if (pos == k) return array[pos];
			// 如果切分后的中间元素不是中位数，尝试减小范围
			if (pos > k) {
				// 说明要找的数字位于左半
				high = pos - 1;
			} else {
				// 说明要找的数字位于右半
				low = pos + 1;
			}
		}
		return array[k];	// 刚好在中间
	}
	
	/**
	 * 和快速排序的切分方法完全一样，把首元素当做切分元素
	 */
	private int partition(int[] array, int low, int high) {
		
		int i = low;
		int j = high + 1;
		int val = array[low];
		while (true) {
			// i指针向右遍历直到定位于大于val的第一个值
			while (array[++i] < val) {
				// 到头了也要跳出来
				if (i == high) break;
			}
			// j指针向左遍历直到定位与小于val的第一个值
			while (array[--j] > val) {
				// 到头了也要跳出来
				if (j == low) break;
			}
			// 检查i和j是否相遇，相遇则切分完成
			if (i >= j) break;
			// 交换i和j
			swap(array, i, j);
		}
		// 最后将切分元素放到中间区域
		swap(array, low, j);
		return j;
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
