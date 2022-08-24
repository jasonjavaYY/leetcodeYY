package lc.lc1;

/*
* 搜索插入位置
*
* 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
请必须使用时间复杂度为 O(log n) 的算法。
示例 1:
输入: nums = [1,3,5,6], target = 5
输出: 2
示例 2:
输入: nums = [1,3,5,6], target = 2
输出: 1
示例 3:
输入: nums = [1,3,5,6], target = 7
输出: 4
*
* 方法一：二分查找
考虑这个插入的位置pos，它成立的条件为：nums[pos−1]<target≤nums[pos],其中nums 代表排序数组。由于如果存在这个目标值，
* 我们返回的索引也是pos，因此我们可以将两个条件合并得出最后的目标：「在一个有序数组中找第一个大于等于target 的下标」。
* 问题转化到这里，直接套用二分法即可，即不断用二分法逼近查找第一个大于等于target 的下标 。
* */
public class _35 {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n; //初始化左右下标
        while (left <= right) { //二分循环
            int mid = ((right - left) >> 1) + left; //计算mid，和(right + left) >> 1一样
            if (target <= nums[mid]) { //如果mid值大于等于target，更新右边界且ans=mid
                ans = mid;
                right = mid - 1;
            } else { //否则更新左边界
                left = mid + 1;
            }
        }
        return ans; //返回找到的下标或者需要插入的下标
    }
}
