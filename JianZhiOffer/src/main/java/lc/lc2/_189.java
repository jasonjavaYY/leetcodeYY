package lc.lc2;

/*
* 轮转数组
*
* 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
示例 1:
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
示例 2:
输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释:
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]
*
* 方法三：数组翻转
我们将数组的元素向右移动 k 次后，尾部 k % n 个元素会移动至数组头部，其余元素向后移动 k % n 个位置。
可以先将所有元素翻转，这样尾部的 k % n 个元素就被移至数组头部，然后我们再翻转 [0,k%n −1] 元素和 [k%n,n−1] 区间的元素即能得到最后的答案。
我们以 n=7，k=3 为例进行如下展示：
操作	                       结果
原始数组	                   1 2 3 4 5 6 7
翻转所有元素	               7 6 5 4 3 2 1
翻转 [0,kmodn−1] 区间元素	   5 6 7 4 3 2 1
翻转 [kmodn,n−1] 区间元素	   5 6 7 1 2 3 4
* */
public class _189 {
    public void rotate(int[] nums, int k) {
        //将数组元素向右移动k次，尾部k%n元素会移动至头部，其余元素向后移动k%n位置
        k %= nums.length;
        //先整体翻转，再翻转0到k-1，再翻转k到n-1
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    //翻转nums的start到end
    public void reverse(int[] nums, int start, int end) {
        while (start < end) { //循环，每次交换start和end
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;//靠近start和end
            end -= 1;
        }
    }
}
