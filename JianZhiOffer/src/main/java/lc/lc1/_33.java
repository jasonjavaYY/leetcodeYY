package lc.lc1;

/*
* 搜索旋转排序数组
*
* 整数数组 nums 按升序排列，数组中的值互不相同 。在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，
* 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
* 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。给你 旋转后 的数组 nums 和一个整数 target ，
* 如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
示例 1：
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
示例 2：
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
示例 3：
输入：nums = [1], target = 0
输出：-1
*
* 方法一：二分查找
对于有序数组，可以使用二分查找的方法查找元素。但是这道题中，数组本身不是有序的，进行旋转后只保证了数组的局部有序
* 我们将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。我们从 6 这个位置分开以后数组变成了 [4, 5, 6] 和 [7, 0, 1, 2] 两个部分，
* 左边 [4, 5, 6] 部分数组是有序的，这启示我们可以在常规二分查找的时候查看当前 mid 为分割位置分割出来的两个部分[l, mid] 和 [mid + 1, r]
* 哪个部分是有序的，并根据有序的那个部分确定我们该如何改变二分查找的上下界，因为我们能够根据有序的那部分判断出 target 在不在这个部分：
* 如果 [l, mid - 1] 是有序数组，且 target 的大小满足 [nums[l],nums[mid])，则我们应该将搜索范围缩小至 [l, mid - 1]，否则在[mid + 1, r]中。
如果 [mid, r] 是有序数组，且 target 满足 (nums[mid+1],nums[r]]，则我们应该将搜索范围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。
* */
public class _33 {
    public int search(int[] nums, int target) {
        int n = nums.length; //如果数组为空，返回-1
        if (n == 0) {
            return -1;
        }//如果数组只有1个元素，通过比较该值是否为target决定返回下标0还是-1
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        } //初始化左右指针位置
        int l = 0, r = n - 1;
        while (l <= r) { //二分循环
            int mid = (l + r) / 2; //计算中间位置
            if (nums[mid] == target) { //如果mid位置元素=target，返回mid下标
                return mid;
            } //如果0小于mid位置值，说明0到mid有序
            if (nums[0] <= nums[mid]) { //如果target在0到mid范围，就要更新r为mid-1
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else { //否则l是mid+1
                    l = mid + 1;
                }
            } else { //如果mid到n-1有序且target在这个范围内，就把l更新为mid+1
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else { //否则r更新为mid-1
                    r = mid - 1;
                }
            }
        }
        return -1; //最后如果没找到就返回-1
    }
}
