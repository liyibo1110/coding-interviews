package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目12 机器人的运动范围
 * 给定一个二维矩阵，机器人从{0,0}开始每次可以上下左右走1步，但不能走到坐标总值大于参数threshold的格子，求机器人最多能占多少格子
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class RobotMove13 {

	public int movingCount(int threshold, int rows, int cols) {
		
		if (rows <= 0 || cols <= 0 || threshold < 0) {
			return 0;
		}
		
		boolean[] marked = new boolean[rows * cols];
		return move(0, 0, threshold, rows, cols, marked);
	}
	
	private int move(int row, int col, int threshold,
					 int rows, int cols, boolean[] marked) {
		int count = 0;
		if (checked(row, col, threshold, rows, cols, marked)) {
			marked[row * cols + col] = true;
			count = move(row - 1, col, threshold, rows, cols, marked) +
					move(row + 1, col, threshold, rows, cols, marked) +
					move(row, col - 1, threshold, rows, cols, marked) +
					move(row, col + 1, threshold, rows, cols, marked) + 1;
					
		}
		return count;
	}
	
	private boolean checked(int row, int col, int threshold, 
							int rows, int cols, boolean[] marked) {
		boolean result = row > 0 &&
						 row < rows &&
						 col > 0 &&
						 col < cols &&
						 !marked[row * cols + col] &&
						 digitSum(row) + digitSum(col) <= threshold;
		return result;
	}
	
	/**
	 * 将一个数字的每位相加并返回结果
	 */
	private int digitSum(int value) {
		
		int sum = 0;
		while(value > 0) {
			sum += value % 10;
			value /= 10;
		}
		return sum;
	}
}
