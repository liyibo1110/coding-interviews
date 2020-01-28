package com.github.liyibo1110.coding.interviews.cp2;

/**
 * 题目12 矩阵中的路径
 * 给定一个二维字符矩阵，搜索特定的字符串路径是否存在
 * 额外特征：无
 * 额外要求：无
 * @author liyibo
 *
 */
public class PathIn2DArray12 {

	public boolean hasPath(char[] martix, int rows, int cols, char[] str) {
		
		if (martix == null || martix.length == 0 || rows <= 0 || cols <= 0 || str == null) {
			return false;
		}
		
		boolean[] marked = new boolean[martix.length];
		// 从左上角开始逐个尝试流程
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (hasPathTo(martix, rows, cols, row, cols, str, 0, marked)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean hasPathTo(char[] martix, int rows, int cols, int row, int col, 
							char[] str, int len, boolean[] marked) {
		
		// 根据行列点算出一维数组对应下标
		int index = row * cols + col;
		// 行列出界、特定字符不匹配，同一路径已匹配，最终都会返回该路径最终匹配失效
		if (row < 0 || row >= rows || col < 0 || col >= cols || martix[index] != str[len] || marked[index]) {
			return false;
		}
		
		// 到这里说明这条路径的当前字符已正确匹配
		if (len == str.length - 1) return true;
		
		// 还没有完整匹配，要继续尝试从上下左右前进1格继续匹配
		if (hasPathTo(martix, rows, cols, row, col - 1, str, len + 1, marked) ||
				hasPathTo(martix, rows, cols, row - 1, col, str, len + 1, marked) ||
				hasPathTo(martix, rows, cols, row, col + 1, str, len + 1, marked) ||
				hasPathTo(martix, rows, cols, row + 1, col, str, len + 1, marked)) {
			
			return true;
		}
		// 到这里说明本轮字符能匹配，但是后面的步骤匹配失败，需要把本轮的marked重置
		marked[index] = false;
		return false;
	}
}
