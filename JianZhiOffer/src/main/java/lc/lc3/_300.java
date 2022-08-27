package lc.lc3;

/*
* 最长递增子序列
*
* 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
* 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
示例 1：
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
示例 2：
输入：nums = [0,1,0,3,2,3]
输出：4
示例 3：
输入：nums = [7,7,7,7,7,7,7]
输出：1
*
* 方法一：动态规划
定义 dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取。
我们从小到大计算 dp 数组的值，在计算 dp[i] 之前，我们已经计算出 dp[0…i−1] 的值，则状态转移方程为：
dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
即考虑往 dp[0…i−1] 中最长的上升子序列后面再加一个 nums[i]。由于 dp[j] 代表 nums[0…j] 中以 nums[j] 结尾的最长上升子序列，
* 所以如果能从 dp[j] 这个状态转移过来，那么 nums[i] 必然要大于 nums[j]，才能将 nums[i] 放在 nums[j] 后面以形成更长的上升子序列。
最后，整个数组的最长上升子序列即所有 dp[i] 中的最大值。
* */
//一个整数数组nums，找到其最长严格递增子序列长度，子序列不需要连续
public class _300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {//如果数组为空，返回长度0
            return 0;
        }//dp[i]为前i个元素，以第i个数字结尾的最长上升子序列长度
        int[] dp = new int[nums.length];
        dp[0] = 1;//只有第0个元素时，长度为1
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {//i从1到len
            dp[i] = 1; //最开始认为dp[i]=1，即只考虑第i个元素
            for (int j = 0; j < i; j++) {//j从0到i
                //如果num[j]<num[i]，那么可以把j元素加进来
                if (nums[i] > nums[j]) {
                    //dp[i]=max(dp[j])+1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            } //每找到一个dp[i]就更新最大长度
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
