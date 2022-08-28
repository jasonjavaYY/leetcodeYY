package lc.lc3;

/*
* 除自身以外数组的乘积
*
* 给一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
请不要使用除法，且在 O(n) 时间复杂度内完成此题。
示例 1:
输入: nums = [1,2,3,4]
输出: [24,12,8,6]
示例 2:
输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]
*
* 解题思路：
因为空间复杂度要求 O(1)，不能使用 除法，因此一定需要在 乘法 过程中得到所有答案；可以将 res 数组列成乘积形式，
* 不同的 n 组成每行内容，形成一个矩阵，可以发现矩阵 主对角线 全部为 1 （当前数字不相乘，等价为乘 1）；
因此，我们分别计算矩阵的 下三角 和 上三角，并且在计算过程中储存过程值，最终可以在遍历 2 遍 nums 下完成结果计算。
res
res[0] =	1	      num[1]	...	    num[n-2]	num[n-1]
res[1] =	num[0]	  1	        ...	    num[n-2]	num[n-1]
...	        ...	      ...	    ...	    num[n-2]	num[n-1]
res[n-2] =	num[0]	  num[1]	...	    1	        num[n-1]
res[n-1] =	num[0]	  num[1]	...	    num[n-2]	1
* */
//数组 整数数组nums，返回数组answer，answer[i]等于nums中除nums[i]外其余元素乘积
public class _238 {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length]; //构造res结果数组
        int p = 1, q = 1;//初始化p和q都是1，后面累乘
        //先把下三角每行非1数字都乘到res中
        for (int i = 0; i < nums.length; i++) {//i从0到len
            res[i] = p;//res[i] = p
            p *= nums[i];//p*= nums[i]
        }
        //再把上三角每行乘机乘到res中
        for (int i = nums.length - 1; i > 0; i--) {//i从len到0
            q *= nums[i];//q *= nums[i]
            res[i - 1] *= q;//res[i - 1] *= q
        }
        return res;//返回res
    }
}
