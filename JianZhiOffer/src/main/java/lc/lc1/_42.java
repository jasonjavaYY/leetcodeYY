package lc.lc1;

/*
* 接雨水
*
* 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
*
* 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
示例 2：
输入：height = [4,2,0,3,2,5]
输出：9
*
* 方法 1：暴力
直接按问题描述进行。对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。
初始化 ans=0,从左向右扫描数组：初始化 max_left=0 和 max_right=0,从当前元素向左扫描并更新：max_left=max(max_left,height[j])
从当前元素向右扫描并更新：max_right=max(max_right,height[j]),将min(max_left,max_right)−height[i] 累加到 ans
* */
//数组 n个非负整数表示宽度为1的柱子高度图，计算此排列柱子能接多少雨水
public class _42 {
    public int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) { //从1遍历到size-1
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                //j从i向左搜索找到左边最大高度
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                //j从i向右搜索找到右边最大高度
                max_right = Math.max(max_right, height[j]);
            } //左右最小值-当前高度不断加到ans里
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans; //返回ans
    }
}
