package lc.lc1;

/*
* 在排序数组中查找元素的第一个和最后一个位置
*
* 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
示例 1：
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：
输入：nums = [], target = 0
输出：[-1,-1]
*
* 方法一：二分查找
考虑 target 开始和结束位置，其实我们要找的就是数组中「第一个等于target的位置」（leftIdx）和「第一个大于target的位置减一」（记为rightIdx）。
二分查找中，寻找 leftIdx 即为在数组中寻找第一个大于等于 target 的下标，寻找 rightIdx 即为在数组中寻找第一个大于 target 的下标，然后将下标减一。
* 两者的判断条件不同，为了代码的复用，我们定义 binarySearch(nums, target, lower) 表示在nums 数组中二分查找target 的位置，
* 如果 lower 为 true，则查找第一个大于等于 target 的下标，否则查找第一个大于 target 的下标。最后，因为target 可能不存在数组中，
* 因此我们需要重新校验我们得到的两个下标 leftIdx 和 rightIdx，看是否符合条件，如果符合条件就返回 [leftIdx,rightIdx]，不符合就返回 [-1,-1][−1,−1]。
* */
//二分查找 非递减排列整数数组nums和目标值target。找目标值在数组中开始位置和结束位置
public class _34 {
    public int[] searchRange(int[] nums, int target) {
        //找到第一个≥target下标
        int leftIdx = binarySearch(nums, target, true);
        //找到第一个大于target下标再-1
        int rightIdx = binarySearch(nums, target, false) - 1;
        //如果left下标≤right下标并且right不月结并且left和right位置值确实都是target，就返回left和right
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        } //否则返回-1 -1
        return new int[]{-1, -1};
    }

    //二分查找
    public int binarySearch(int[] nums, int target, boolean lower) {
        //lower为true，则查找第一个大于等于target下标，否则找第一个大于target的下标
        int left = 0, right = nums.length - 1, ans = nums.length; //初始化左右
        while (left <= right) { //二分循环
            int mid = (left + right) / 2; //计算mid
            //如果mid值比target大或者lower是true且mid值等于target，要继续找
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1; //更新right为mid-1
                ans = mid; //下标更新为mid
            } else { //否则更新left为mid+1
                left = mid + 1;
            }
        }
        return ans; //返回找到的下标
    }
}
