package lc.lc1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 三数之和
*
* 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。
示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
示例 2：
输入：nums = []
输出：[]
示例 3：
输入：nums = [0]
输出：[]
*
* 思路
标签：数组遍历
首先对数组排序，排序后固定一个数nums[i]，再使用左右指针指向 nums[i]后面的两端，
* 数字分别为 nums[L] 和 nums[R]，计算三个数的和 sum 判断是否满足为 0，满足则添加进结果集
如果 nums[i]大于 0，则三数之和必然无法等于 0，结束循环
如果 nums[i] == nums[i−1]，则说明该数字重复，会导致结果重复，所以应该跳过
当 sums = 0 时，nums[L] == nnums[L+1] 则会导致结果重复，应该跳过，L++
当 sum == 0 时，nums[R] == nums[R−1] 则会导致结果重复，应该跳过，R--
时间复杂度：O(n^2)，n 为数组长
* */
//数组 n个整数的数组nums，找出其中所有和为 0 且不重复的三元组。
public class _15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList(); //存放结果，结果是一个双层数组
        int len = nums.length; //获取传入数组长度
        if (nums == null || len < 3) return ans; //如果数组不足3个元素，直接返回空结果
        Arrays.sort(nums); // 对数组排序
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int L = i + 1;  //从i+1到len-1找另外两个数
            int R = len - 1;
            while (L < R) { //循环判断条件
                int sum = nums[i] + nums[L] + nums[R]; //求三个数的和
                if (sum == 0) { //如果和为0，将三个数字加入结果集并且移动L和R去重
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                    while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                    //去重后更新L和R继续找
                    L++;
                    R--;
                } else if (sum < 0) L++; //如果和小于0需要增大，就移动L

                else R--;  //如果和大于0需要减小，就移动R
            }
        }
        return ans; //返回结果集
    }
}
