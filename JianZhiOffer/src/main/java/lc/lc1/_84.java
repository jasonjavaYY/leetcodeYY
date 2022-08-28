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
//数组 n个非负整数表示柱状图各柱子高度。柱子相邻且宽为1。求能勾勒出来的矩形最大面积
public class _84 {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length; //计算一共多少矩形
        if (len == 0) return 0; //如果为空，返回0
        if (len == 1) return heights[0]; //如果只有一个矩形，最大矩形就是该值
        int res = 0; //记录结果
        Deque<Integer> stack = new ArrayDeque<>(len); //构造len长度栈
        for (int i = 0; i < len; i++) { //遍历所有矩形
            //循环判断栈不为空且当前高度小于栈底元素下标对应高度
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                //弹出栈底元素，获取对应下标高度
                int curHeight = heights[stack.pollLast()];
                //循环判断栈不为空，如果栈底元素下标对应高度等于当前高度，就弹出栈底元素
                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                    stack.pollLast();
                }
                int curWidth;
                if (stack.isEmpty()) { //如果栈为空，宽度为i
                    curWidth = i;
                } else { //否则宽度是i-栈底下标-1
                    curWidth = i - stack.peekLast() - 1;
                } //计算当前矩形面积并和res中取较大值更新res
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i); //将下标i加入栈底
        }
        //遍历完上面外循环，再来一次循环，和上面内循环基本一样，区别①：判断条件没后半部分；②宽度为len不是i
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
        return res; //最后返回res
    }
}
