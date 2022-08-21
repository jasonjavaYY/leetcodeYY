package jzoffer;
/*
* 调整数组顺序使奇数位于偶数前面
*
* 需要保证奇数和奇数，偶数和偶数之间的相对位置不变，这和书本不太一样
* 方法一：创建一个新数组，时间复杂度 O(N)，空间复杂度 O(N)。
* */
public class _22 {
    public void reOrderArray(int[] nums) {
        // 先计算出奇数个数
        int oddCnt = 0;
        for (int x : nums)
            if (!isEven(x))
                oddCnt++;
            //复制出临时数组
        int[] copy = nums.clone();
        int i = 0, j = oddCnt;
        //遍历原数组，如果是奇数，就放前面，偶数就从j++位置开始放，放后面
        //因为奇数个数是j
        for (int num : copy) {
            if (num % 2 == 1)
                nums[i++] = num;
            else
                nums[j++] = num;
        }
    }

    private boolean isEven(int x) {
        return x % 2 == 0;
    }
}
