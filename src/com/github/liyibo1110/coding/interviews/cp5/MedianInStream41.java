package com.github.liyibo1110.coding.interviews.cp5;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 题目41 数据流中的中位数
 * 返回一个数据流的中位数，如果为奇数则是排序后中间位置的值，如果是偶尔则是排序后中间2个位置数字的平均值
 * 额外特征：数据流会动态增加数据，每次变化意味着中位数也会变化
 * 额外要求：无
 * @author liyibo
 */
public class MedianInStream41 {

	private Queue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
	private Queue<Integer> minPQ = new PriorityQueue<>();
	private int count; // 数据流的总数
	
	/**
	 * 解法众多，直接用最简单且效率最高（插入复杂度为对数级别）的内置双优先队列法
	 */
	public void insert(Integer val) {
		
		if (count == 0) {
			// 对于第一个数字，规定放到最大堆
			maxPQ.offer(val);
		} else if ((count & 1) == 1) { // 当前如果已有奇数个数字，则尝试放到最小堆
			// 如果数字比最小堆最小值还要小，则要取最大堆最大值，放入最小堆，新值则放到最大堆
			if (val < minPQ.peek()) {
				minPQ.offer(maxPQ.poll());
				maxPQ.offer(val);
			} else {
				minPQ.offer(val);
			}
		} else { // 当前如果已有偶数个数字，则尝试放到最大堆
			// 如果数字比最大堆最大值还要大，则要取最小堆最小值，放入最大堆，新值则放到最小堆
			if (val > maxPQ.peek()) {
				maxPQ.offer(minPQ.poll());
				minPQ.offer(val);
			} else {
				maxPQ.offer(val);
			}
		}
		count++;
	}
	
	public double getMedian() {
		// 如果共有奇数个节点，中位数即为最大堆的最大值
		if ((count & 1) == 1) {
			return Double.valueOf(maxPQ.peek());
		}
		// 如果共有偶数个节点，中位数为最大堆最大值和最小堆最小值的平均值
		return Double.valueOf(maxPQ.peek() + minPQ.peek()) / 2;
	}
}
