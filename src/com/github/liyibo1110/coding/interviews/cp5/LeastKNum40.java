package com.github.liyibo1110.coding.interviews.cp5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 题目40 最小的k个数
 * 给定一个数组和k，返回最小的k个数
 * 额外特征：无
 * 额外要求：时间复杂度为O(n)
 * @author liyibo
 */
public class LeastKNum40 {

	/**
	 * 解法一：基于快速排序的切分方法
	 */
	public List<Integer> getLeastNumbers(int[] array, int k) {
		List<Integer> list = new ArrayList<>();
		if (array == null || array.length == 0 || k <= 0) return list;
		// 会修改array数组本身
		select(array, k - 1);
		for (int i = 0; i < k; i++) {
			list.add(array[i]);
		}
		return list;
	}
	
	/**
	 * 解法二：基于最小堆，比较复杂
	 */
	public List<Integer> getLeastNumbers2(int[] array, int k) {
		List<Integer> list = new ArrayList<>();
		if (array == null || array.length == 0 || k <= 0) return list;
		
		int N = array.length;
		// 先构造最小堆
		for (int i = N / 2; i >= 1; i++) {
			sink(array, i, N);
		}
		// 重复k次，每次取出堆顶元素（当前最小值），然后和最后元素（最大值）交换，然后减小数组（相当于移除了最小值）
		for (int i = 0; i < k; i++) {
			list.add(array[0]);
			swapForHeap(array, 1, N);	// 注意是1，是按堆的下标走的（首元素下标就是1）
			N--;
			sink(array, 1, N);	// 将新的首元素（其实是最大值）下沉到正确问题
		}
		
		return list;
	}
	
	/**
	 * 解法三：基于内置优先队列
	 */
	public List<Integer> getLeastNumbers3(int[] array, int k) {
		List<Integer> list = new ArrayList<>();
		if (array == null || array.length == 0 || k <= 0) return list;
		// 构建一个优先队列（优先策略为保留小值剔除大值）
		Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
		for (int val : array) {
			maxHeap.offer(val);
			// 如果已有元素超过k个，就把里面最大的剔除出来，使得永远保持k个最小值
			if (maxHeap.size() > k) maxHeap.poll();
		}
		list.addAll(maxHeap);
		return list;
	}
	
	private int select(int[] array, int k) {
		int low = 0;
		int high = array.length - 1;
		
		while (low < high) {
			int pos = partition(array, low, high);
			if (pos == k) {
				return array[pos];
			} else if (pos > k) {
				high = pos - 1;
			} else {
				low = pos + 1;
			}
		}
		return array[k];
	}
	
	private int partition(int[] array, int low, int high) {
		int i = low;
		int j = high + 1;
		int value = array[low];
		while (true) {
			while (array[++i] < value) {
				if (i == high) break;
			}
			while (array[--j] > value) {
				if (j == low) break;
			}
			if (i >= j) break;
			swap(array, i, j);
		}
		swap(array, low, j);
		return j;
	}
	
	private void swap(int[] array, int i, int j) {
		
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/**
	 * 最小堆排序的原版方法
	 */
	private void sink(int[] array, int k, int N) {
		
		// 不到最后元素就尝试一直循环
		while (2 * k <= N) {
			// 先定位到左节点
			int j = 2 * k;
			// 如果左右都有节点则要选择更小的
			if (j < N && greater(array, j, j + 1)) j++;
			if (!greater(array, k, j)) break;
			swapForHeap(array, k, j);
			k = j;
		}
	}
	
	private void swapForHeap(int[] array, int i, int j) {
		
		int temp = array[i - 1];
		array[i - 1] = array[j - 1];
		array[j - 1] = temp;
	}
	
	private boolean greater(int[] array, int i , int j) {
		return array[i - 1] > array[j - 1];
	}
}
