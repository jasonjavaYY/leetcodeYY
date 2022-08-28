package lc.lc1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 四数之和
*
* 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组
* [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
0 <= a, b, c, d < n  a、b、c 和 d 互不相同,nums[a] + nums[b] + nums[c] + nums[d] == target
你可以按 任意顺序 返回答案 。
示例 1：
输入：nums = [1,0,-1,0,-2,2], target = 0
输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
示例 2：
输入：nums = [2,2,2,2,2], target = 8
输出：[[2,2,2,2]]
*
* 使用两重循环分别枚举前两个数，然后在两重循环枚举到的数之后使用双指针枚举剩下的两个数。
* 假设两重循环枚举到的前两个数分别位于下标 i和 j，其中 i<j。初始时，左右指针分别指向下标 j+1 和下标 n−1。每次计算四个数的和，并进行如下操作：
* 如果和等于 target，则将枚举到的四个数加到答案中，然后将左指针右移直到遇到不同的数，将右指针左移直到遇到不同的数；
* 如果和小于 target，则将左指针右移一位；如果和大于 target，则将右指针左移一位。
* 使用双指针枚举剩下的两个数的时间复杂度是 O(n)，因此总时间复杂度是 O(n^3)，低于 O(n^4)。
* 具体实现时，还可以进行一些剪枝操作：
在确定第一个数之后，如果 nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target，说明此时剩下的三个数无论取什么值，四数之和一定大于 target，因此退出第一重循环；
在确定第一个数之后，如果 nums[i]+nums[n−3]+nums[n−2]+nums[n−1]<target，说明此时剩下的三个数无论取什么值，四数之和一定小于 target，
* 因此第一重循环直接进入下一轮，枚举 nums[i+1]；
* 在确定前两个数之后，如果 nums[i]+nums[j]+nums[j+1]+nums[j+2]>target，说明此时剩下的两个数无论取什么值，四数之和一定大于target，
* 因此退出第二重循环；
* 在确定前两个数之后，如果 \nums[i]+nums[j]+nums[n−2]+nums[n−1]<target，说明此时剩下的两个数无论取什么值，四数之和一定小于target，
* 因此第二重循环直接进入下一轮，枚举 nums[j+1]。
* */
//数组 n个整数数组nums和目标值target，找出和位target的所有不重复的四元组
public class _18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>(); //存放结果数组
        if (nums == null || nums.length < 4) { //如果数组长度小于4，返回空数组
            return quadruplets;
        }
        Arrays.sort(nums); //传入数组排序
        int length = nums.length; //获取传入数组长度
        for (int i = 0; i < length - 3; i++) { //i从0到length-3防止越界，因为i后面还要有3个数
            if (i > 0 && nums[i] == nums[i - 1]) continue; //去重
            //如果从i开始四个最小数和大于target，直接退出循环
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            //如果从i加上其余最大3个数和小于target，说明当前i值太小，进行下一轮循环
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) continue;
            for (int j = i + 1; j < length - 2; j++) { //j从i+1到length-2
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; //去重
                //如果i+从j开始3个最小数和大于target，直接退出循环
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                //如果i+j再+最大2数和小于target，说明当前j值太小，进入下一轮循环
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) continue;
                int left = j + 1, right = length - 1; //初始化左右下标
                while (left < right) { //循环判断
                    int sum = nums[i] + nums[j] + nums[left] + nums[right]; //求四个数和
                    if (sum == target) { //如果等于target，将四个数放入结果集
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++; //去重left
                        left++; //更新left
                        while (left < right && nums[right] == nums[right - 1]) right--; //去重right
                        right--; //更新right
                    } else if (sum < target) left++;  //如果和太小，增大left
                    else right--; //如果和太大，减小right
                }
            }
        }
        return quadruplets; //返回结果数组
    }
}
