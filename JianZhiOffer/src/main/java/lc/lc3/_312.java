package lc.lc3;

/*
* 戳气球
*
* 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 
* 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
求所能获得硬币的最大数量。
示例 1：
输入：nums = [3,1,5,8]
输出：167
解释：
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
示例 2：
输入：nums = [1,5]
输出：10
*
* 方法二：动态规划
「自底向上」的动态规划。令 dp[i][j] 表示填满开区间 (i,j) 能得到的最多硬币数，那么边界条件是 i≥j−1，此时有 dp[i][j]=0。
可以写出状态转移方程：
dp[i][j]=  [j−1 | max | k=i+1]  val[i]×val[k]×val[j] + dp[i][k] + dp[k][j],   i<j−1
           0,                                                                 i≥j−1
最终答案即为 dp[0][n+1]。实现时要注意到动态规划的次序。
* */
//动态规划 n个气球都有数存在nums。戳破所有气球。戳破i个气球可获得nums[i-1]*nums[i]*nums[i+1]值。如果i-1或i+1越界就是1。求最大值
public class _312 {
    public int maxCoins(int[] nums) {
        int n = nums.length;//计算数组长度
        //dp[i][j]表示填满开区间 (i,j) 得到的最多硬币数
        int[][] rec = new int[n + 2][n + 2];
        int[] val = new int[n + 2];//扩展原数组两边，各增加一个1
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {//通过nums[i-1]给val[i]赋值
            val[i] = nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {//i从n-1开始
            //如果i≥j−1代表i到j的区间没有数字，dp[i][j]不合法就是0，因此j从i+2开始
            for (int j = i + 2; j <= n + 1; j++) {
                //dp[i][j]= val[i]×val[k]×val[j]+dp[i][k]+dp[k][j]这个表达式的k从i+1到j-1的最大值
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += rec[i][k] + rec[k][j];
                    rec[i][j] = Math.max(rec[i][j], sum);
                }
            }
        }
        return rec[0][n + 1];//返回[0][n-1]就是最大值
    }
}
