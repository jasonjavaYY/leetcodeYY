package lc.lc4;

/*
* 二分查找
*
* 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1
示例 1:
输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4
示例 2:
输入: nums = [-1,0,3,5,9,12], target = 2
输出: -1
解释: 2 不存在 nums 中因此返回 -1
*
* 方法一：二分查找
对于特定下标 i，比较 nums[i] 和 target 大小：
如果 nums[i]=target，下标 i 即为要寻找下标；如果 nums[i]>target，则 target 只能在下标 i 左侧；否则 target 只能在下标 i 右侧。
* */
//二分查找 n个元素升序数组nums和目标值target，搜索nums中的target下标，不存在就返回-1
public class _704 {
    //标准二分查找
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;//得到l和r
        while (left <= right) {//循环判断
            int mid = (right - left) / 2 + left;//计算mid
            int num = nums[mid];//获取mid处数值
            if (num == target) {//如果等于target，返回下标
                return mid;
            } else if (num > target) {//如果mid处值大于target，更新r=mid-1
                right = mid - 1;
            } else {//否则l=mid+1
                left = mid + 1;
            }
        }
        return -1;//循环结束没找到就返回-1
    }
}
