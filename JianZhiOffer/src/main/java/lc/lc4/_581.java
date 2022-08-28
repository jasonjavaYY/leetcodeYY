package lc.lc4;

/*
* 最短无序连续子数组
*
* 一个整数数组 nums ，需要找出一个连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
请你找出符合题意的 最短 子数组，并输出它的长度。
示例 1：
输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
示例 2：
输入：nums = [1,2,3,4]
输出：0
示例 3：
输入：nums = [1]
输出：0
*
* 分析
假设把数组分成三段，左段和右段是标准的升序数组，中段数组虽是无序的，但满足最小值大于左段的最大值，最大值小于右段的最小值。
* 目标就很明确了，找中段的左右边界，我们分别定义为begin 和 end;从左到右维护一个最大值max,在进入右段之前，那么遍历到的nums[i]都是小于max的，
* 我们要求的end就是遍历中最后一个小于max元素的位置；同理，从右到左维护一个最小值min，在进入左段之前，那么遍历到的nums[i]也都大于min，
* 要求的begin也就是最后一个大于min元素的位置。
* */
//一整数数组nums，找一个连续子数组 ，如果对这个子数组升序那么整个数组都变为升序，找出最短子数组输出长度
public class _581 {
    public int findUnsortedSubarray(int[] nums) {
        //把数组分三段，左和右段升序，中段虽无序但最小值大于左段最大值，最大值小于右段最小值。
        //找中段左右边界begin和end;从左到右维护max,进入右段前，遍历到的nums[i]都小于max，
        //end就是最后一个小于max元素的位置；从右到左维护min，进入左段前nums[i]都大于min，
        //begin是最后一个大于min元素的位置
        int len = nums.length;//计算初始数组长度
        int min = nums[len - 1]; //从右到左找min
        int max = nums[0];//从左到右找max
        int begin = 0, end = -1;//初始化中段左右边界
        for (int i = 0; i < len; i++) { //遍历
            if (nums[i] < max) {//从左到右，如果nums[i]小于max，更新end为i
                end = i;
            } else {//否则更新最大值
                max = nums[i];
            }
            if (nums[len - i - 1] > min) {//从右到左，如果nums[i]大于min，更新begin为i
                begin = len - i - 1;
            } else {//否则更新min值
                min = nums[len - i - 1];
            }
        }
        return end - begin + 1;//返回end-begin+1就是数组长度
    }
}
