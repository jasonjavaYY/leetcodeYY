package jzoffer;

/*
 * 旋转数组的最小数字
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素
 *
 * 将旋转数组对半分可以得到一个包含最小元素的新旋转数组，以及一个非递减排序的数组。
 * 新的旋转数组的数组元素是原数组的一半，这种折半性质的算法的时间复杂度为 O(logN)
 *
 * 此时问题的关键在于确定对半分得到的两个数组哪一个是旋转数组，哪一个是非递减数组。
 * 我们很容易知道非递减数组的第一个元素一定小于等于最后一个元素。
 * 当 nums[m] <= nums[h] 时，表示 [m, h] 区间内的数组是非递减数组，[l, m] 区间内的数组是旋转数组，
 * 此时令 h = m；否则 [m + 1, h] 区间内的数组是旋转数组，令 l = m + 1。
 *
 * 如果数组元素允许重复，会出现一个特殊的情况：nums[l] == nums[m] == nums[h]，
 * 此时无法确定解在哪个区间，需要切换到顺序查找。例如对于数组 {1,1,1,0,1}，
 * l、m 和 h 指向的数都为 1，此时无法知道最小数字 0 在哪个区间
 * */
public class _12 {
    public int minNumberInRotateArray(int[] nums) {
        if (nums.length == 0) //如果数组为空，返回0
            return 0;
        int l = 0, h = nums.length - 1; //左右边界为数组边界
        while (l < h) { //二分查找
            int m = l + (h - l) / 2; //计算中间下标
            //如果左中右三点都相等，返回l到h的最小值
            if (nums[l] == nums[m] && nums[m] == nums[h])
                return minNumber(nums, l, h);
            //如果m值≤右边值，更新h为m
            else if (nums[m] <= nums[h])
                h = m;
            //否则更新l为m+1
            else
                l = m + 1;
        }
        return nums[l];
    }

    //ok  返回nums从l到h范围最小值，因为旋转升序数组一次，l到h或者升序，或者只有一个降序节点
    private int minNumber(int[] nums, int l, int h) {
        for (int i = l; i < h; i++)
            //如果i比i+1大，说明降序节点就在这，后面的数都比i+1大，返回i+1数
            if (nums[i] > nums[i + 1])
                return nums[i + 1];
            //否则返回最左侧数
        return nums[l];
    }
}
