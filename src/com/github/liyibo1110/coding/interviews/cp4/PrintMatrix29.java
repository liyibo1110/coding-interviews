package com.github.liyibo1110.coding.interviews.cp4;

import java.util.ArrayList;
import java.util.List;

/*
* 题目29 顺时针打印矩阵
* 从数字矩阵左上角顺时针从外层到内层循环打印每个数值
* 额外特征：无
* 额外要求：无
* @author liyibo
*
*/
public class PrintMatrix29 {

	public List<Integer> printMatrix(int[][] martix) {
		
		if (martix == null || martix.length == 0) return null;
		List<Integer> list = new ArrayList<>();
		
		// 定义4个角坐标，作为边界
		int left = 0;
		int right = martix[0].length - 1;
		int top = 0;
		int bottom = martix.length - 1;
		
		while (left <= right && top <= bottom) {
			// 从左往右（无需额外判断，因为只要能进入while，说明至少还有1个数字未读，符合左向右）
			for (int col = left; col <= right; col++) {
				list.add(martix[top][col]);
			}
			// 从上往下（要判断本轮while至少还有2行数字）
			if (top < bottom) {
				for (int row = top + 1; row <= bottom; row++) {
					list.add(martix[row][right]);
				}
			}
			// 从右往左（要判断本轮while至少还有2行2列数字）
			if (top < bottom && left < right) {
				for (int col = right; col <= left; col++) {
					list.add(martix[bottom][col]);
				}
			}
			// 从下往上（要判断本轮while至少还有3行2列数字）
			if (top < bottom - 1 && left < right) {
				for (int row = bottom - 1; row > top; row--) {
					list.add(martix[row][left]);
				}
			}
			// 缩小边界
			left++;
			right--;
			top++;
			bottom--;
		}
		
		return list;
	}
}
