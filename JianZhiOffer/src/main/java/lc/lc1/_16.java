package lc.lc1;

import java.util.Arrays;

/*
* 最接近的三数之和
*
* 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
返回这三个数的和。假定每组输入只存在恰好一个解。
示例 1：
输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
示例 2：
输入：nums = [0,0,0], target = 1
输出：0
*
* 标签：排序和双指针
首先进行数组排序，时间复杂度O(nlogn),在数组 nums 中，进行遍历，每遍历一个值利用其下标i，形成一个固定值 nums[i]
再使用前指针指向 start = i + 1 处，后指针指向 end = nums.length - 1 处，也就是结尾处
根据 sum = nums[i] + nums[start] + nums[end] 的结果，判断 sum 与目标 target 的距离，如果更近则更新结果 ans
同时判断 sum 与 target 的大小关系，因为数组有序，如果 sum > target 则 end--，如果 sum < target 则 start++，
* 如果 sum == target 则说明距离为 0 直接返回结果,整个遍历过程，固定值为 n 次，双指针为 n 次，时间复杂度为O(n2)
总时间复杂度：O(nlogn)+O(n 2)=O(n 2)
* */
public class _16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if (sum > target)
                    end--;
                else if (sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }
}
