package jzoffer;
/*
* 数字在排序数组中出现的次数
*
Input:
nums = 1, 2, 3, 3, 3, 3, 4, 6
K = 3

Output:
4
* */
public class _55 {
    public int GetNumberOfK(int[] nums, int K) {
        //因为是有序的，所以用二分查找找到≤K的第一个下标
        int first = binarySearch(nums, K);
        //二分查找到≤K+1的第一个下标，这两个下标之差就是K出现的次数
        int last = binarySearch(nums, K + 1);
        return (first == nums.length || nums[first] != K) ? 0 : last - first;
    }

    private int binarySearch(int[] nums, int K) {
        int l = 0, h = nums.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= K)
                h = m;
            else
                l = m + 1;
        }
        return l;
    }
}
