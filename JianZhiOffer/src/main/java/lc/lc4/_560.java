package lc.lc4;

/*
* 和为 K 的子数组
*
* 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数 。
示例 1：
输入：nums = [1,1,1], k = 2
输出：2
示例 2：
输入：nums = [1,2,3], k = 3
输出：2
*
* 考虑以 i 结尾和为 k 的连续子数组个数，我们需要统计符合条件的下标 j 的个数，其中 0≤j≤i 且 [j..i] 这个子数组的和恰好为 k 。
我们可以枚举 [0..i] 里所有的下标 j 来判断是否符合条件，可能有读者会认为假定我们确定了子数组的开头和结尾，还需要 O(n) 复杂度遍历子数组求和，
* 复杂度就将达到 O(n^3)。但如果我们知道 [j,i] 子数组和，就能 O(1) 推出 [j−1,i] 的和，因此这部分的遍历求和是不需要的，
* 我们在枚举下标 j 的时候已经能 O(1) 求出 [j,i] 的子数组之和。
* */
public class _560 {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
