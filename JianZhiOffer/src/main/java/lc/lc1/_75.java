package lc.lc1;

/*
* 颜色分类
*
* 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。必须在不使用库的sort函数的情况下解决这个问题。
示例 1：
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
示例 2：
输入：nums = [2,0,1]
输出：[0,1,2]
*
* 本质就是三个指针，头指针和中指针负责0和1的交换，中指针和尾指针负责把2移到末尾
* */
public class _75 {
    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) { //如果0或1个元素，直接返回
            return;
        }
        //all in [0, zero) = 0，all in [zero, i) = 1，all in [two, len - 1] = 2
        int zero = 0; //初始zero指向0，two指向末尾
        int two = len;
        int i = 0;
        // 当i==two三个区间正好覆盖全部数组，因此循环继续的条件是i<two
        while (i < two) {
            if (nums[i] == 0) { //如果i位是0，就交换nums的i和zero位置并zero指针++
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) { //如果i位是1不需要动，直接++
                i++;
            } else { //如果数字是2，two指针--，交换i和two位置元素
                two--;
                swap(nums, i, two);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
