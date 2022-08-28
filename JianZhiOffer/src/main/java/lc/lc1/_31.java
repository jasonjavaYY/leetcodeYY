package lc.lc1;

/*
* 下一个排列
*
* 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
* 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列
* 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。必须 原地 修改，只允许使用额外常数空间。
*
* 先找出最大的索引 k 满足 nums[k] < nums[k+1]，如果不存在，就翻转整个数组；
再找出另一个最大索引 l 满足 nums[l] > nums[k]；交换 nums[l] 和 nums[k]；最后翻转 nums[k+1:]。
举个例子：
比如 nums = [1,2,7,4,3,1]，下一个排列是什么？我们找到第一个最大索引是 nums[1] = 2
再找到第二个最大索引是 nums[4] = 3,交换，nums = [1,3,7,4,2,1];翻转，nums = [1,3,1,2,4,7]完毕!
所以,时间复杂度：O(n),空间复杂度：O(1)
* */
//数组 返回整数数组下一个排列，如arr=[1,2,3]下一个排列是 [1,3,2]
public class _31 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return; //如果传入数组为空，直接返回
        int firstIndex = -1;
        //找到最大索引firstIndex满足nums[firstIndex]<nums[firstIndex+1]，从后往前
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstIndex = i;
                break;
            }
        }
        if (firstIndex == -1) { //如果firstIndex是-1表示整个数组是逆序，要翻转整个数组
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int secondIndex = -1;
        //再找出另一个最大索引l满足 nums[l]>nums[k]，从后往前找
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[firstIndex]) {
                secondIndex = i;
                break;
            }
        }
        swap(nums, firstIndex, secondIndex);//交换nums[l]和nums[k]
        //翻转k+1到末尾nums[k+1:]，最后更新后的nums就是要找的数组，如果要返回，就返回nums
        reverse(nums, firstIndex + 1, nums.length - 1);
        return;
    }

    //翻转nums的i到j位
    private void reverse(int[] nums, int i, int j) {
        while (i < j) { //依次翻转首尾元素，然后首尾不断靠近
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }
}
