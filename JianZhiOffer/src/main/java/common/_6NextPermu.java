package common;

public class _6NextPermu {
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
