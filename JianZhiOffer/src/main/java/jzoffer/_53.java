package jzoffer;
/*
* 数组中的逆序对
*
* 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成
* 一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
* */
public class _53 {
    private long cnt = 0;
    private int[] tmp;  // 在这里声明辅助数组，而不是在 merge() 递归函数中声明

    public int InversePairs(int[] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return (int) (cnt % 1000000007);
    }

    private void mergeSort(int[] nums, int l, int h) {
        if (h - l < 1) //如果高低位相同，直接返回
            return;
        int m = l + (h - l) / 2; //计算l和h的中间位置
        mergeSort(nums, l, m);  //排序l到m
        mergeSort(nums, m + 1, h);  //排序m+1到h
        merge(nums, l, m, h);
    }

    private void merge(int[] nums, int l, int m, int h) {
        int i = l, j = m + 1, k = l;
        while (i <= m || j <= h) {
            if (i > m)
                tmp[k] = nums[j++];
            else if (j > h)
                tmp[k] = nums[i++];
            else if (nums[i] <= nums[j])
                tmp[k] = nums[i++];
            else {
                tmp[k] = nums[j++];
                this.cnt += m - i + 1;  // nums[i] > nums[j]，说明 nums[i...mid] 都大于 nums[j]
            }
            k++;
        }
        for (k = l; k <= h; k++)
            nums[k] = tmp[k];
    }
}
