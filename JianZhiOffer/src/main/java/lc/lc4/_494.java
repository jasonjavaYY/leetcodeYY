package lc.lc4;

/*
* 目标和
*
* 给你一个整数数组 nums 和一个整数 target 。向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个表达式 ：
例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
示例 1：
输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
示例 2：
输入：nums = [1], target = 1
输出：1
*
* 方法一：回溯
数组 nums 每个元素都可以添加符号 + 或 -，因此每个元素有 2 种添加符号的方法，n 个数共有 2^n种添加符号的方法，对应 2^n种不同的表达式。
* 当 n 个元素都添加符号之后，即得到一种表达式，如果表达式的结果等于目标数 target，则该表达式即为符合要求的表达式。
可以使用回溯的方法遍历所有的表达式，回溯过程中维护一个计数器 count，当遇到一种表达式的结果等于目标数 target 时，将 count 的值加 1。
* 遍历完所有的表达式之后，即可得到结果等于目标数 target 的表达式的数目。
* */
public class _494 {
    int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }
}
