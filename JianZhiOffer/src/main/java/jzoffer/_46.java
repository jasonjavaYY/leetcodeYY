package jzoffer;
/*
* 连续子数组的最大和
*
* {6, -3, -2, 7, -15, 1, 2, 2}，连续子数组的最大和为 8（从第 0 个开始，到第 3 个为止）
* */
public class _46 {
    public static void main(String[] args) {
        int[] nums = new int[7];
        nums[0] = -3;
        nums[1] = 6;
        nums[2] = -2;
        nums[3] = 7;
        nums[4] = -15;
        nums[5] = 1;
        nums[6] = 2;
        System.out.println(FindGreatestSumOfSubArray(nums));
    }
    public static int FindGreatestSumOfSubArray(int[] nums) {
        //如果数组为空，返回0
        if (nums == null || nums.length == 0)
            return 0;
        //将最大和初始值设置为最小值
        int greatestSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : nums) {
            //遍历数组，如果sum小于0，就说明前面总和为负，并且前面这些数的最大子数组也已经保存在greatestSum中了
            //就让sum设置为当前值
            sum = sum <= 0 ? val : sum + val;
            greatestSum = Math.max(greatestSum, sum);
        }
        return greatestSum;
    }
}
