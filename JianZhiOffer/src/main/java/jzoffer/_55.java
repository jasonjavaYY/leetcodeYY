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
//二分查找 求某数字在排序数组中出现的次数
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
        int l = 0, h = nums.length; //初始化l和h
        while (l < h) { //退出循环条件
            int m = l + (h - l) / 2; //计算l和h中点
            if (nums[m] >= K) h = m; //如果该位置数字大于等于k，更新h
            else l = m + 1; //小于k时更新l，所以最后得到小于k的最大元素下标或第一个K下标
        }
        return l;
    }
}
