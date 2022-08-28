package jzoffer;
/*
* 数组中的逆序对
*
* 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成
* 一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
* */
//数组 数组中两个数如果前大于后则这两个数组成逆序对。输入一数组，求逆序对数
public class _53 {
    private long cnt = 0;//记录结果
    private int[] tmp;  //声明辅助数组

    public int InversePairs(int[] nums) {
        tmp = new int[nums.length];//根据输入数组大小构造辅助数组
        mergeSort(nums, 0, nums.length - 1);//归并排序
        return (int) (cnt % 1000000007);//返回结果
    }

    private void mergeSort(int[] nums, int l, int h) {
        if (h - l < 1) return; //如果高低位相同，直接返回
        int m = l + (h - l) / 2; //计算l和h的中间位置
        mergeSort(nums, l, m);  //排序l到m
        mergeSort(nums, m + 1, h);  //排序m+1到h
        merge(nums, l, m, h);//合并l m h
    }

    private void merge(int[] nums, int l, int m, int h) {
        int i = l, j = m + 1, k = l;//i和k指向l，j指向m+1
        while (i <= m || j <= h) {//i≤m或j≤h
            if (i > m) //如果i>m，tmp[k] = nums[j++]
                tmp[k] = nums[j++];
            else if (j > h) //如果j大于h，tmp[k] = nums[i++]
                tmp[k] = nums[i++];
            else if (nums[i] <= nums[j])//如果num[i]≤num[j]，tmp[k] = nums[i++]
                tmp[k] = nums[i++];
            else {  //否则tmp[k] = nums[j++]
                tmp[k] = nums[j++];
                this.cnt += m - i + 1;  //增加m-i+1
            }
            k++; //更新k
        }
        for (k = l; k <= h; k++) //遍历l到h，更新nums为tmp值
            nums[k] = tmp[k];
    }
}
