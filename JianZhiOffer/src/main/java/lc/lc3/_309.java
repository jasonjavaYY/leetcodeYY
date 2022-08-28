package lc.lc3;

/*
* 区域和检索 - 数组可修改
*
* 给你一个数组 nums ，请你完成两类查询。其中一类查询要求更新数组 nums 下标对应的值,另一类查询要求返回数组 nums 中索引 left 和索引 right 之间
* （ 包含 ）的nums元素的和 ，其中 left <= right
实现 NumArray 类：
NumArray(int[] nums) 用整数数组 nums 初始化对象
void update(int index, int val) 将 nums[index] 的值 更新 为 val
int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和
示例 1：
输入：
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
输出：
[null, 9, null, 8]
解释：
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1,2,5]
numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
*
* 方法一：分块处理
设数组大小为 n，我们将数组 nums 分成多个块，每个块大小 size，最后一个块的大小为剩余的不超过 size 的元素数目，那么块的总数为 ⌈ n/size ⌉，
* 用一个数组 sum 保存每个块的元素和。
构造函数:
计算块大小 size，初始化 sum。
update 函数:
下标 index 对应的块下标为 ⌊ index/size ⌋，更新 nums 和 sum。
sumRange 函数:
设 left 位于第 b_1个块内的第 i_1个元素，right 位于第 b2个块内的第 i2个元素。如果 b1 = b2，那么直接返回第 b1个块位于区间[i1,i2]的元素之和；
* 否则计算第 b1个块位于区间 [i1,size−1)的元素之和 sum1，第 b_2个块位于区间 [0, i_2]的元素之和 sum2，第 b1+1 个块到第 b2−1 个块的元素
* 和的总和 sum3，返回 sum1+sum2+sum3。对于块大小 size 的取值，我们从各个函数的时间复杂度入手。构造函数的时间复杂度为 O(n)，
* update 函数的时间复杂度为 O(1)，而 sumRange 函数的时间复杂度为 O(size + n/size)，仅当 size= sqrt(n)时等号成立。
* 因此 size 取 ⌊ sqrt(n) ⌋，此时 sumRange 函数的时间复杂度为 O( sqrt(n) )。
* */
//数组 给一个数组nums，请完成两类查询。一类要更新数组下标的值,另一类要返回数组中索引left和right之间元素的和
public class _309 {
    //设数组大小n，将数组分成多块，每块大小size，最后一块大小为剩余的不超过size元素数
    class NumArray {
        private int[] sum; // sum[i]表示第i个块的元素和
        private int size; // 块的大小
        private int[] nums;//存放所有数字的数组

        public NumArray(int[] nums) {
            this.nums = nums;//初始化nums
            int n = nums.length;
            size = (int) Math.sqrt(n);//size等于√n
            //n先+size-1再除以size就是n/size 向上取整，初始化sum数组
            sum = new int[(n + size - 1) / size];
            for (int i = 0; i < n; i++) {
                //遍历num[i]，更新sum数组
                sum[i / size] += nums[i];
            }
        }
        //更新方法
        public void update(int index, int val) {
            //同时更新sum和nums
            sum[index / size] += val - nums[index];
            nums[index] = val;
        }
        //求和方法
        public int sumRange(int left, int right) {
            //计算出left落在哪块哪个位置，right落在哪块哪个位置：b1  i1  b2  i2
            int b1 = left / size, i1 = left % size, b2 = right / size, i2 = right % size;
            if (b1 == b2) { //如果[left, right] 在同一块
                int sum = 0;
                for (int j = i1; j <= i2; j++) {
                    //就从b1块的i1遍历到i2求和返回
                    sum += nums[b1 * size + j];
                }
                return sum;
            }//否则说明包含多个块
            int sum1 = 0;//计算b1块中需要求的和，从i1开始到b1块末尾求和
            for (int j = i1; j < size; j++) {
                sum1 += nums[b1 * size + j];
            }//计算b2块中需要求的和，从0开始到i2位置求和
            int sum2 = 0;
            for (int j = 0; j <= i2; j++) {
                sum2 += nums[b2 * size + j];
            }//计算b1和b2之间的整块和
            int sum3 = 0;//从b1+1到b2-1
            for (int j = b1 + 1; j < b2; j++) {
                sum3 += sum[j];
            }//上面三次求的sum再求和返回
            return sum1 + sum2 + sum3;
        }
    }
}
