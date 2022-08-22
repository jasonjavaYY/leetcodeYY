package jzoffer;

/*
数组中重复的数字

* 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
* 数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。
* 请找出数组中任意一个重复的数字。
*
要求时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。
对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解
* */
public class _1 {
    public boolean duplicate(int[] nums, int length, int[] duplication) {
        if (nums == null || length <= 0) //如果数组为空，返回false
            return false;
        for (int i = 0; i < length; i++) { //遍历数组
            while (nums[i] != i) {
                //如果nums[i] == nums[nums[i]] 说明找到了重复元素，
                if (nums[i] == nums[nums[i]]) {
                    //将重复数字放入duplication，返回true
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums, i, nums[i]); //将num[i]放在第i位置
            }
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
