package lc.lc1;

import java.util.ArrayDeque;
import java.util.Deque;

/*
* 柱状图中最大的矩形
*
* 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积。
*
* 示例 1:
输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10
*
* 我们需要在柱状图中找出最大的矩形，因此我们可以考虑枚举矩形的宽和高，其中「宽」表示矩形贴着柱状图底边的宽度，「高」表示矩形在柱状图上的高度。
如果我们枚举「宽」，我们可以使用两重循环枚举矩形的左右边界以固定宽度 w，此时矩形的高度 h，就是所有包含在内的柱子的「最小高度」，对应的面积为w×h。
* */
//n个非负整数表示柱状图各柱子高度。柱子相邻且宽为1。求能勾勒出来的矩形最大面积
public class _84 {

    public int largestRectangleArea(int[] heights) {
        int len = heights.length; //计算一共多少矩形
        if (len == 0) { //如果为空，返回0
            return 0;
        }
        if (len == 1) { //如果只有一个值，最大矩形就是该值
            return heights[0];
        }
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            // 这个 while 很关键，因为有可能不止一个柱形的最大宽度可以被计算出来
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                    stack.pollLast();
                }

                int curWidth;
                if (stack.isEmpty()) {
                    curWidth = i;
                } else {
                    curWidth = i - stack.peekLast() - 1;
                }
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pollLast()];
            while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                stack.pollLast();
            }
            int curWidth;
            if (stack.isEmpty()) {
                curWidth = len;
            } else {
                curWidth = len - stack.peekLast() - 1;
            }
            res = Math.max(res, curHeight * curWidth);
        }
        return res;
    }
}
