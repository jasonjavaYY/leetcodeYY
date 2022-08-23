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
        //如果first是数组长度，代表没有小于k的数，返回0，如果first位置不是K，也返回0，否则返回差值
        return (first == nums.length || nums[first] != K) ? 0 : last - first;
    }

    private int binarySearch(int[] nums, int K) {
        //初始化l和h
        int l = 0, h = nums.length;
        while (l < h) { //退出循环条件
            int m = l + (h - l) / 2; //计算l和h中点
            if (nums[m] >= K) //如果该位置数字大于等于k，都更新h
                h = m;
            else //小于k的时候更新l，所以最后得到的是小于k的最大元素下标或者第一个K的下标
                l = m + 1;
        }
        return l;
    }
}
